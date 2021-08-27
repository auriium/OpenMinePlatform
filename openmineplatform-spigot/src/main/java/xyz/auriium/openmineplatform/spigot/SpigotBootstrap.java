package xyz.auriium.openmineplatform.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.Bootstrap;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.PlatformLauncher;
import xyz.auriium.openmineplatform.api.plugin.PluginRepresentation;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginController;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginControllerImpl;

public abstract class SpigotBootstrap extends JavaPlugin implements Bootstrap {

    private ReloadablePluginController controller;

    @Override
    public void onEnable() {

        //0. Create platform

        PluginRepresentation representation = representation();
        Platform platform = generateLauncher().launch(representation.getIdentity());

            //a. Activate all startup hooks

        representation.getInsertionData().insert(platform, true);

        //1. Initialize plugin
        controller = new ReloadablePluginControllerImpl(platform, representation());
        controller.load();

        representation.getInsertionData().insert(platform, false);

    }

    @Override
    public void onDisable() {
        if (controller == null) throw new IllegalStateException("Warning: Plugin controller was never initialized!");

        controller.stop();
    }

    @Override
    public PlatformLauncher generateLauncher() {
        return new SpigotLauncher(this);
    }
}
