package xyz.auriium.openmineplatform.api;

public interface PlatformCloseable {

    /**
     * Method executed when entire platform is closed (full shutdown)
     */
    void closePlatform();

    /**
     * Method called when only plugin is closed (typically reloads but sometimes also shutdown)
     */
    void closeApplication();

}
