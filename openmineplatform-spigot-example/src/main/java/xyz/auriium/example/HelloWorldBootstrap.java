package xyz.auriium.openmineplatform.example;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.*;
import xyz.auriium.openmineplatform.api.plugin.PluginRepresentation;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePlugin;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;
import xyz.auriium.openmineplatform.spigot.SpigotBootstrap;

public class HelloWorldBootstrap extends SpigotBootstrap {

    @Override
    protected PluginRepresentation representation() {
        return new Representation();
    }

    public static class Representation implements PluginRepresentation {

        @Override
        public PlatformProjectIdentity getIdentity() {
            return new PlatformProjectIdentity("HelloWorld","Auriium","1.0S");
        }

        @Override
        public HookData getInsertionData() {
            return HookData.make();
        }

        @Override
        public ReloadablePlugin supply(Platform platform, ReloadablePluginState reloadablePluginState) {
            return new HelloWorldPlugin(platform.telescope(TypedPlatformTelescope.of(JavaPlugin.class)), reloadablePluginState);
        }

    }

}
