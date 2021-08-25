package xyz.auriium.openmineplatform.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.plugin.PluginRepresentation;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginController;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginControllerImpl;

public abstract class SpigotBootstrap extends JavaPlugin {

    private ReloadablePluginController controller;

    @Override
    public void onEnable() {

        //0. Create platform

        PluginRepresentation representation = representation();
        Platform platform = new SpigotLauncher(this).launch(representation.getIdentity());

            //a. Activate all startup hooks

        representation.getInsertionData().insert(platform);

        //1. Initialize plugin
        controller = new ReloadablePluginControllerImpl(platform, representation());
        controller.load();

    }

    @Override
    public void onDisable() {
        if (controller == null) throw new IllegalStateException("Warning: Plugin controller was never initialized!");

        controller.stop();
    }

    protected abstract PluginRepresentation representation();

}
