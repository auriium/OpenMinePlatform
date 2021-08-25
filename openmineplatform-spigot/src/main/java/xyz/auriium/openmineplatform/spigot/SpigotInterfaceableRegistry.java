package xyz.auriium.openmineplatform.spigot;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.HandlingRegistry;
import xyz.auriium.openmineplatform.api.interfaceable.TelescopeHandler;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserPopper;

import java.util.*;

public class SpigotInterfaceableRegistry extends HandlingRegistry {

    private final UserPopper<Player> popper;
    private final Colorer colorer;
    private final Server server;

    public SpigotInterfaceableRegistry(TelescopeHandler handler, UserPopper<Player> popper, Colorer colorer, Server server) {
        super(handler);
        this.popper = popper;
        this.colorer = colorer;
        this.server = server;
    }

    @Override
    public Optional<Interfaceable> getByUUID(UUID uuid) {

        if (!popper.present(uuid)) return Optional.empty();

        return Optional.of(unsafe(uuid));
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
        return new SpigotUser(uuid, popper, colorer, server);
    }
}
