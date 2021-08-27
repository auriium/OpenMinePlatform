package xyz.auriium.openmineplatform.api;

public interface ServiceHook<T> {

    Class<T> providedServiceType();
    T provide(Platform platform);
    String name();

    default boolean isPersistent() {
        return true;
    }

    default boolean startsBeforePlugin() {
        return true;
    }

}
