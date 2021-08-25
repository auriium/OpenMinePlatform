package xyz.auriium.openmineplatform.api.plugin;

public interface ReloadablePluginController extends ReloadablePluginState {

    ReloadablePlugin getCurrent();

    void load();
    void stop();

}
