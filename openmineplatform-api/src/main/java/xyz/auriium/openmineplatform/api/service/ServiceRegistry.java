package xyz.auriium.openmineplatform.api.service;

import java.util.Optional;

public interface ServiceRegistry {

    <T> void register(Class<T> service, T provider, String name);

    <T> Optional<T> retrieve(Class<T> service);
    <T> Optional<Service<T>> retrieveService(Class<T> service);

    <T> boolean hasService(Class<T> service);

}
