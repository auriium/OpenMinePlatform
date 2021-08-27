package xyz.auriium.openmineplatform.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.telescope.ExceptionalMapper;
import xyz.auriium.openmineplatform.api.telescope.Telescope;
import xyz.auriium.openmineplatform.api.telescope.TelescopeResult;

public class JavaPluginTelescope implements Telescope<Platform, JavaPlugin> {

    JavaPluginTelescope() {}

    @Override
    public TelescopeResult<Platform, JavaPlugin> telescope(Platform input) {
        if (!(input instanceof SpigotPlatform)) return TelescopeResult.fail(input, "Platform is not a SpigotPlatform");

        return TelescopeResult.complete(input, ((SpigotPlatform)input).getPlugin());
    }

    public static final ExceptionalMapper<Platform, JavaPlugin> EXCEPTIONAL = new ExceptionalMapper<>(new JavaPluginTelescope());
}
