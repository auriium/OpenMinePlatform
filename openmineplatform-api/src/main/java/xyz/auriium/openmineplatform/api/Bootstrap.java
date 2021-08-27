package xyz.auriium.openmineplatform.api;

import xyz.auriium.openmineplatform.api.plugin.PluginRepresentation;

public interface Bootstrap {

    /**
     * Represents the plugin's launch details
     * @return the launch details
     */
    PluginRepresentation representation();

    /**
     * Represents method called on underlying server startup in order to launch a Platform object
     * @return the platform
     */
    PlatformLauncher generateLauncher(); //called once ever

}
