package xyz.auriium.openmineplatform.example;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.TypedPlatform;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePlugin;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;

public class HelloWorldPlugin implements ReloadablePlugin {

    private final TypedPlatform<JavaPlugin> platform;
    private final ReloadablePluginState state;

    public HelloWorldPlugin(TypedPlatform<JavaPlugin> platform, ReloadablePluginState state) {
        this.platform = platform;
        this.state = state;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }


}
