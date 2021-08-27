package xyz.auriium.openmineplatform.api;

public interface PlatformHook {

    void execute(Platform platform);
    String name();

    default boolean isPersistent() {
        return true;
    }

    default boolean startsBeforePlugin() {
        return true;
    }

}
