package xyz.auriium.openmineplatform.api;

import org.slf4j.Logger;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.InterfaceableRegistry;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;
import xyz.auriium.openmineplatform.api.service.ServiceRegistry;

public class PlatformPartA {

    private final PlatformProjectIdentity identity;
    private final PlatformLocation location;
    private final InterfaceableRegistry registry;
    private final ServiceRegistry serviceRegistry;
    private final Scheduler scheduler;
    private final Colorer colorer;
    private final Logger logger;

    public PlatformPartA(PlatformProjectIdentity identity, PlatformLocation location, InterfaceableRegistry registry, ServiceRegistry serviceRegistry, Scheduler scheduler, Colorer colorer, Logger logger) {
        this.identity = identity;
        this.location = location;
        this.registry = registry;
        this.serviceRegistry = serviceRegistry;
        this.scheduler = scheduler;
        this.colorer = colorer;
        this.logger = logger;
    }

    public PlatformProjectIdentity getIdentity() {
        return identity;
    }

    public PlatformLocation getLocation() {
        return location;
    }

    public InterfaceableRegistry interfaceables() {
        return registry;
    }

    public Scheduler scheduler() {
        return scheduler;
    }

    public Colorer colorer() {
        return colorer;
    }

    public Logger logger() {
        return logger;
    }

    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }
}
