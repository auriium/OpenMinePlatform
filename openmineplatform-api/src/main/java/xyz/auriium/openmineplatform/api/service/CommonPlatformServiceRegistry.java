package xyz.auriium.openmineplatform.api.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CommonPlatformServiceRegistry implements PlatformServiceRegistry {

    private final Map<Class<?>, Service<?>> map = new ConcurrentHashMap<>();

    @Override
    public <T> void register(Class<T> service, T provider, String name) {
        map.put(service, new Service<>(provider, name));
    }

    @Override
    public <T> Optional<T> retrieve(Class<T> service) {
        return retrieveService(service).map(Service::getService);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<Service<T>> retrieveService(Class<T> service) {
        return Optional.ofNullable((Service<T>)map.get(service));
    }

    @Override
    public boolean unregister(Class<?> clazz) {
        return map.remove(clazz) != null;
    }

    @Override
    public <T> boolean hasService(Class<T> service) {
        return map.containsKey(service);
    }
}
