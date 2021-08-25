package xyz.auriium.openmineplatform.api;

import org.slf4j.Logger;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.InterfaceableRegistry;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;

public abstract class AbstractTypedPlatform<T> implements TypedPlatform<T> {

    private final PlatformPartA platformPartA;

    public AbstractTypedPlatform(PlatformPartA platformPartA) {
        this.platformPartA = platformPartA;
    }

    @Override
    public PlatformProjectIdentity getIdentity() {
        return platformPartA.getIdentity();
    }

    @Override
    public PlatformLocation getLocation() {
        return platformPartA.getLocation();
    }

    @Override
    public InterfaceableRegistry interfaceables() {
        return platformPartA.interfaceables();
    }

    @Override
    public Scheduler scheduler() {
        return platformPartA.scheduler();
    }

    @Override
    public Colorer colorer() {
        return platformPartA.colorer();
    }

    @Override
    public Logger logger() {
        return platformPartA.logger();
    }



}
