package xyz.auriium.openmineplatform.spigot.scheduler;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.scheduling.DelayedPurveyor;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;
import xyz.auriium.openmineplatform.api.scheduling.SchedulerTask;

import java.util.concurrent.Executor;

public class SpigotScheduler implements Scheduler {

    private final JavaPlugin plugin;
    private final DelayedPurveyor purveyor;

    public SpigotScheduler(JavaPlugin plugin, DelayedPurveyor purveyor) {
        this.plugin = plugin;
        this.purveyor = purveyor;
    }

    public SpigotScheduler(JavaPlugin plugin) {
        this.plugin = plugin;
        this.purveyor = new BukkitUnsafePurveyor(plugin);
    }

    @Override
    public Executor sync() {
        return this::run;
    }

    @Override
    public DelayedPurveyor async() {
        return purveyor;
    }

    @Override
    public SchedulerTask run(Runnable runnable) {
        return new BukkitDelegatingSchedulerTask(plugin.getServer().getScheduler().runTask(plugin, runnable));
    }

    @Override
    public SchedulerTask runLater(Runnable runnable, long delay) {
        return new BukkitDelegatingSchedulerTask(plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay));
    }

    @Override
    public SchedulerTask runRepeated(Runnable runnable, long period) {
        return new BukkitDelegatingSchedulerTask(plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, 0L, period));
    }

    @Override
    public SchedulerTask runAsync(Runnable runnable) {
        return new BukkitDelegatingSchedulerTask(plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable));
    }

    @Override
    public SchedulerTask runLaterAsync(Runnable runnable, long delay) {
        return new BukkitDelegatingSchedulerTask(plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay));
    }

    @Override
    public SchedulerTask runRepeatedAsync(Runnable runnable, long period) {
        return new BukkitDelegatingSchedulerTask(plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, 0L, period));
    }
}
