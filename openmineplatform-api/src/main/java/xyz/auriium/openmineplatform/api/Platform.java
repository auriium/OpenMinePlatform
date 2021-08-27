package xyz.auriium.openmineplatform.api;

import org.slf4j.Logger;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.InterfaceableRegistry;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;
import xyz.auriium.openmineplatform.api.service.ServiceRegistry;
import xyz.auriium.openmineplatform.api.telescope.TelescopeMapping;

public interface Platform {

    PlatformProjectIdentity getIdentity();
    PlatformLocation getLocation();
    InterfaceableRegistry interRegistry();
    ServiceRegistry serviceRegistry();
    Scheduler scheduler();
    Colorer colorer();
    Logger logger();

    <T> T telescope(TelescopeMapping<T,Platform> telescope);

}
