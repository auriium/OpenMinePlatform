package xyz.auriium.openmineplatform.api;

public interface PlatformHook {

    void execute(Platform platform);
    String name();

}
