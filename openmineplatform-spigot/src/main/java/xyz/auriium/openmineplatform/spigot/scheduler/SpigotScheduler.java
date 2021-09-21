/*
 * OpenMinePlatform
 * Copyright © 2021 Auriium
 *
 * OpenMinePlatform is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * OpenMinePlatform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenMinePlatform. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

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
