package xyz.auriium.openmineplatform.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.*;
import xyz.auriium.openmineplatform.api.telescope.TelescopeMapping;

public class SpigotPlatform extends AbstractPlatform {

    private final JavaPlugin plugin;

    public SpigotPlatform(PlatformPartA platformPartA, JavaPlugin plugin) {
        super(platformPartA);
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public <T> T telescope(TelescopeMapping<T, Platform> telescope) {
        return telescope.calculate(this);
    }
}
