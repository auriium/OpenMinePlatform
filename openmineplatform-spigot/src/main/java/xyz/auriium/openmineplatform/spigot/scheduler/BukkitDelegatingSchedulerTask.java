package xyz.auriium.openmineplatform.spigot.scheduler;

import org.bukkit.scheduler.BukkitTask;
import xyz.auriium.openmineplatform.api.scheduling.SchedulerTask;

public class BukkitDelegatingSchedulerTask implements SchedulerTask {

    private final BukkitTask task;

    public BukkitDelegatingSchedulerTask(BukkitTask task) {
        this.task = task;
    }

    @Override
    public int getID() {
        return task.getTaskId();
    }

    @Override
    public void cancel() {
        task.cancel();
    }
}
