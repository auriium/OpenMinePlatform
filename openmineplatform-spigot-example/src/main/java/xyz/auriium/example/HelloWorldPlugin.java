package xyz.auriium.example;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.TypedPlatform;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePlugin;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;

public class HelloWorldPlugin implements ReloadablePlugin {

    private final TypedPlatform<JavaPlugin> platform;
    private final ReloadablePluginState state;

    private int cum = 0;

    public HelloWorldPlugin(TypedPlatform<JavaPlugin> platform, ReloadablePluginState state) {
        this.platform = platform;
        this.state = state;
    }

    @Override
    public void onStart() {
        cum++;

        if (platform.getBoundPlatform() == null) throw new IllegalStateException("Platform's bind was not initialized");

        System.out.println("Hello World! Activating reload ensurement counter! Value of retainment is: " + cum);
        System.out.println("Registering reload command. Please execute it at your discretion.");

        platform.getBoundPlatform().getCommand("reloadexample").setExecutor(new Suicide(state)); //should sever this platform
    }

    @Override
    public void onStop() {
        System.out.println("Goodbye!");
    }


}
