package xyz.auriium.openmineplatform.spigot;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.auriium.openmineplatform.api.*;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.InterfaceableRegistry;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserPopper;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;
import xyz.auriium.openmineplatform.api.service.CommonPlatformServiceRegistry;
import xyz.auriium.openmineplatform.api.service.ServiceRegistry;
import xyz.auriium.openmineplatform.spigot.scheduler.SpigotScheduler;
import xyz.auriium.openmineplatform.spigot.user.SpigotPopper;

public class SpigotLauncher implements PlatformLauncher {

    private final JavaPlugin plugin;

    public SpigotLauncher(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Platform launch(PlatformProjectIdentity identity) {

        BukkitAudiences audiences = BukkitAudiences.create(plugin);


        Scheduler scheduler = new SpigotScheduler(plugin);
        Commander commander = new SpigotCommander(plugin.getServer(), scheduler);
        Server server = plugin.getServer();
        UserPopper<Player> popper = new SpigotPopper(server);
        Colorer colorer = new SpigotColorer();
        InterfaceableRegistry registry = new SpigotInterfaceableRegistry(commander, audiences, popper, colorer, server);
        PlatformLocation location = new PlatformLocation(plugin.getDataFolder().toPath());
        Logger logger = LoggerFactory.getLogger(identity.getProjectName());
        ServiceRegistry serviceRegistry = new CommonPlatformServiceRegistry();

        PlatformPartA partA = new PlatformPartA(identity, location, registry, serviceRegistry, scheduler, colorer, logger);

        return new SpigotPlatform(partA, plugin);
    }
}
