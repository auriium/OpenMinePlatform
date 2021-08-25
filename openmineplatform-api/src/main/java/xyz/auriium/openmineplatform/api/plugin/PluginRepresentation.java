package xyz.auriium.openmineplatform.api.plugin;

import xyz.auriium.openmineplatform.api.*;

import java.util.function.Supplier;

public interface PluginRepresentation {

    /**
     * Gets the identity of the project
     * @return the identity
     */
    PlatformProjectIdentity getIdentity();

    /**
     * Gets all hook data
     * @return the hook insertion data
     */
    HookData getInsertionData();

    /**
     * This method is called every time the plugin is reloaded in order to produce a new instance of your plugin.
     *
     * @param platform the platform
     * @param reloadablePluginState the statecontroller
     * @return a new reloadableplugin
     */
    ReloadablePlugin supply(Platform platform, ReloadablePluginState reloadablePluginState);


}
