package xyz.auriium.openmineplatform.spigot;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.interfaceable.InterfaceableRegistry;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserPopper;
import xyz.auriium.openmineplatform.api.telescope.TelescopeMapping;
import xyz.auriium.openmineplatform.spigot.user.SpigotUser;

import java.util.*;

public class SpigotInterfaceableRegistry implements InterfaceableRegistry {

    private final BukkitAudiences audiences;
    private final UserPopper<Player> popper;
    private final Colorer colorer;
    private final Server server;

    public SpigotInterfaceableRegistry(BukkitAudiences audiences, UserPopper<Player> popper, Colorer colorer, Server server) {
        this.audiences = audiences;
        this.popper = popper;
        this.colorer = colorer;
        this.server = server;
    }

    @Override
    public Optional<Interfaceable> getCurrent(UUID uuid) {

        if (!popper.present(uuid)) return Optional.empty();

        return Optional.of(unsafe(uuid));
    }

    @Override
    public Interfaceable get(UUID uuid) {
        return unsafe(uuid);
    }

    @Override
    public Collection<Interfaceable> getAll() {

        Set<Interfaceable> interfaceables = new HashSet<>();

        for (Player player : server.getOnlinePlayers()) {
            interfaceables.add(unsafe(player.getUniqueId()));
        }

        return interfaceables;
    }

    public Interfaceable unsafe(UUID uuid) {
        return new SpigotUser(uuid, popper, colorer, server, audiences);
    }


    @Override
    public <O> Optional<O> getTelescopingCurrent(UUID uuid, TelescopeMapping<O, Interfaceable> telescope) {
        if (popper.present(uuid)) return Optional.empty();

        O o = telescope.calculate(unsafe(uuid));

        return Optional.of(o);
    }

    @Override
    public <O> O getTelescoping(UUID uuid, TelescopeMapping<O, Interfaceable> telescope) {
        return telescope.calculate(unsafe(uuid));
    }
}
