package xyz.auriium.openmineplatform.api.scheduling;

public interface SchedulerTask {

    int getID();
    void cancel();

    default boolean persistent() {
        return false;
    }

}
