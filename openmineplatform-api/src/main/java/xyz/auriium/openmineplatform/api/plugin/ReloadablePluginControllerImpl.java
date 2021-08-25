package xyz.auriium.openmineplatform.api.plugin;

import xyz.auriium.openmineplatform.api.Platform;

public class ReloadablePluginControllerImpl implements ReloadablePluginController{

    private final Platform platform;
    private final PluginRepresentation representation;

    private volatile ReloadablePlugin plugin;

    public ReloadablePluginControllerImpl(Platform platform, PluginRepresentation representation) {
        this.platform = platform;
        this.representation = representation;
    }


    @Override
    public ReloadablePlugin getCurrent() {
        return plugin;
    }

    @Override
    public void load() {
        plugin = representation.supply(platform, this);

        plugin.onStart();
    }

    @Override
    public void stop() {
        plugin.onStop();

        plugin = null;
    }

    @Override
    public void reload() {
        plugin.onStop();

        plugin = representation.supply(platform, this);

        plugin.onStart();
    }
}
