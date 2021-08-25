package xyz.auriium.openmineplatform.api.plugin;

/**
 * Represents a simple plugin implementation for platform plugins to implement
 */
public interface ReloadablePlugin {

    void onStart();
    void onStop();


}
