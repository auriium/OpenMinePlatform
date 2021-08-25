package xyz.auriium.openmineplatform.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.*;

public class SpigotPlatform extends AbstractTypedPlatform<JavaPlugin> {

    private final JavaPlugin plugin;

    public SpigotPlatform(PlatformPartA platformPartA, JavaPlugin plugin) {
        super(platformPartA);
        this.plugin = plugin;
    }

    @Override
    public JavaPlugin getBoundPlatform() {
        return plugin;
    }

    @Override
    public <T extends Platform> T telescope(Telescope<Platform,T> telescope) {
        var result = telescope.telescope(this);

        if (!result.isSuccess()) throw new InvalidPlatformRescopeException("Attempted to smart cast (telescope) platform to a new platform type but failed: " + result.getError());

        return result.getCompletion();
    }
}
