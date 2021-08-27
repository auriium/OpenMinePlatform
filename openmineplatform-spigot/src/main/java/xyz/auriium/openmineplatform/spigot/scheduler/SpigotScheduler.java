package xyz.auriium.openmineplatform.spigot.scheduler;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import xyz.auriium.openmineplatform.api.scheduling.DelayedPurveyor;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;
import xyz.auriium.openmineplatform.api.scheduling.SchedulerTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

public class SpigotScheduler implements Scheduler {

    private final Map<Integer, Boolean> cleanupMap = new ConcurrentHashMap<>();

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
        return run(runnable, false);
    }

    @Override
    public SchedulerTask runLater(Runnable runnable, long delay) {
        return runLater(runnable, delay, false);
    }

    @Override
    public SchedulerTask runRepeated(Runnable runnable, long period) {
        return runRepeated(runnable, period, false);
    }

    @Override
    public SchedulerTask runAsync(Runnable runnable) {
        return runAsync(runnable, false);
    }

    @Override
    public SchedulerTask runLaterAsync(Runnable runnable, long delay) {
        return runLaterAsync(runnable, delay, false);
    }

    @Override
    public SchedulerTask runRepeatedAsync(Runnable runnable, long period) {
        return runRepeatedAsync(runnable, period, false);
    }

    @Override
    public SchedulerTask run(Runnable runnable, boolean persistent) {
        BukkitTask task = plugin.getServer().getScheduler().runTask(plugin, runnable);
        cleanupMap.put(task.getTaskId(), persistent);

        return new BukkitDelegatingSchedulerTask(task);
    }

    @Override
    public SchedulerTask runLater(Runnable runnable, long delay, boolean persistent) {
        BukkitTask task = plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
        cleanupMap.put(task.getTaskId(), persistent);

        return new BukkitDelegatingSchedulerTask(task);
    }

    @Override
    public SchedulerTask runRepeated(Runnable runnable, long period, boolean persistent) {
        BukkitTask task = plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, 0L, period);
        cleanupMap.put(task.getTaskId(), persistent);

        return new BukkitDelegatingSchedulerTask(task);
    }

    @Override
    public SchedulerTask runAsync(Runnable runnable, boolean persistent) {
        BukkitTask task = plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
        cleanupMap.put(task.getTaskId(), persistent);

        return new BukkitDelegatingSchedulerTask(task);
    }

    @Override
    public SchedulerTask runLaterAsync(Runnable runnable, long delay, boolean persistent) {
        BukkitTask task = plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
        cleanupMap.put(task.getTaskId(), persistent);

        return new BukkitDelegatingSchedulerTask(task);
    }

    @Override
    public SchedulerTask runRepeatedAsync(Runnable runnable, long period, boolean persistent) {
        BukkitTask task = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, 0L, period);
        cleanupMap.put(task.getTaskId(), persistent);

        return new BukkitDelegatingSchedulerTask(task);
    }


    @Override
    public void closePlatform() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();

        cleanupMap.forEach((i,b) -> {
            scheduler.cancelTask(i);
        });
    }

    @Override
    public void closeApplication() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();

        cleanupMap.forEach((i,b) -> {
            if (!b) {
                scheduler.cancelTask(i);
            }
        });
    }
}
