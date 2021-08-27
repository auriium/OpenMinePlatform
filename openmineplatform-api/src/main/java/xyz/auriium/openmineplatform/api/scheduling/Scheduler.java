package xyz.auriium.openmineplatform.api.scheduling;

import xyz.auriium.openmineplatform.api.PlatformCloseable;

import java.util.concurrent.Executor;

public interface Scheduler extends PlatformCloseable {

    Executor sync();
    DelayedPurveyor async();

    SchedulerTask run(Runnable runnable);
    SchedulerTask runLater(Runnable runnable, long delay);
    SchedulerTask runRepeated(Runnable runnable, long period);

    SchedulerTask runAsync(Runnable runnable);
    SchedulerTask runLaterAsync(Runnable runnable, long delay);
    SchedulerTask runRepeatedAsync(Runnable runnable, long period);

    SchedulerTask run(Runnable runnable, boolean persistent);
    SchedulerTask runLater(Runnable runnable, long delay, boolean persistent);
    SchedulerTask runRepeated(Runnable runnable, long period, boolean persistent);

    SchedulerTask runAsync(Runnable runnable, boolean persistent);
    SchedulerTask runLaterAsync(Runnable runnable, long delay, boolean persistent);
    SchedulerTask runRepeatedAsync(Runnable runnable, long period, boolean persistent);



}
