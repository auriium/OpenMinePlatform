package xyz.auriium.example;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePlugin;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;

public class HelloWorldPlugin implements ReloadablePlugin {

    private final JavaPlugin plugin;
    private final ReloadablePluginState state;

    private int cum = 0;

    public HelloWorldPlugin(JavaPlugin plugin, ReloadablePluginState state) {
        this.plugin = plugin;
        this.state = state;
    }

    @Override
    public void onStart() {
        cum++;

        System.out.println("Hello World! Activating reload ensurement counter! Value of retainment is: " + cum);
        System.out.println("Registering reload command. Please execute it at your discretion.");

        plugin.getCommand("reloadexample").setExecutor(new Suicide(state)); //should sever this platform
    }

    @Override
    public void onStop() {
        System.out.println("Goodbye!");
    }


}
