package xyz.auriium.openmineplatform.spigot;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserPopper;

import java.util.Optional;
import java.util.UUID;

public class SpigotPopper implements UserPopper<Player> {

    private final Server server;

    public SpigotPopper(Server server) {
        this.server = server;
    }

    @Override
    public Optional<Player> pop(UUID uuid) {
        return Optional.ofNullable(server.getPlayer(uuid));
    }

    @Override
    public boolean present(UUID uuid) {
        return server.getPlayer(uuid) != null;
    }
}
