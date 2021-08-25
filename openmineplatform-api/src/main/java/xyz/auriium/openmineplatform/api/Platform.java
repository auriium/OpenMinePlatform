package xyz.auriium.openmineplatform.api;

import org.slf4j.Logger;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.InterfaceableRegistry;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;

public interface Platform {

    PlatformProjectIdentity getIdentity();
    PlatformLocation getLocation();
    InterfaceableRegistry interfaceables();
    Scheduler scheduler();
    Colorer colorer();
    Logger logger();

    <T extends Platform> T telescope(Telescope<Platform,T> telescope);

}
