package xyz.auriium.openmineplatform.api.scheduling;

import java.util.concurrent.Executor;

public interface Scheduler {

    Executor sync();
    DelayedPurveyor async();

    SchedulerTask run(Runnable runnable);
    SchedulerTask runLater(Runnable runnable, long delay);
    SchedulerTask runRepeated(Runnable runnable, long period);

    SchedulerTask runAsync(Runnable runnable);
    SchedulerTask runLaterAsync(Runnable runnable, long delay);
    SchedulerTask runRepeatedAsync(Runnable runnable, long period);


}
