package xyz.auriium.openmineplatform.spigot.scheduler;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.auriium.openmineplatform.api.scheduling.DelayedPurveyor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

public class BukkitUnsafePurveyor implements DelayedPurveyor {
    
    private final JavaPlugin plugin;

    public BukkitUnsafePurveyor(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public <T> CompletionStage<T> provide(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, this); //TODO THIS IS UNSAFE
        /// FIXME: 8/23/2021 MAKE SAFE WITH PAPER
    }

    @Override
    public void execute(@NotNull Runnable command) {
        plugin.getServer().getScheduler().runTask(plugin, command);
    }
}
