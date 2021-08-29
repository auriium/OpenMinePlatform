package xyz.auriium.openmineplatform.spigot;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;

public class SpigotCommander implements Commander {

    private final Server server;
    private final Scheduler scheduler;

    public SpigotCommander(Server server, Scheduler scheduler) {
        this.server = server;
        this.scheduler = scheduler;
    }

    @Override
    public void runAsPlayer(Player player, String cmd) {

        if (server.isPrimaryThread()) {
            server.dispatchCommand(player, cmd);
            return;
        }

        scheduler.run(() -> server.dispatchCommand(player, cmd));


    }

    @Override
    public void runAsServer(String cmd) {
        if (server.isPrimaryThread()) {
            server.dispatchCommand(server.getConsoleSender(), cmd);
        }

        scheduler.run(() -> server.dispatchCommand(server.getConsoleSender(), cmd));
    }
}
