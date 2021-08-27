package xyz.auriium.openmineplatform.spigot.scheduler;

import org.bukkit.scheduler.BukkitTask;
import xyz.auriium.openmineplatform.api.scheduling.SchedulerTask;

public class BukkitDelegatingSchedulerTask implements SchedulerTask {

    private final BukkitTask task;
    private final boolean isPersistent;

    public BukkitDelegatingSchedulerTask(BukkitTask task, boolean isPersistent) {
        this.task = task;
        this.isPersistent = isPersistent;
    }

    public BukkitDelegatingSchedulerTask(BukkitTask task) {
        this.task = task;
        this.isPersistent = false;
    }

    @Override
    public int getID() {
        return task.getTaskId();
    }

    @Override
    public void cancel() {
        task.cancel();
    }

    @Override
    public boolean persistent() {
        return isPersistent;
    }
}
