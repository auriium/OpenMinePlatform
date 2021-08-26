package xyz.auriium.openmineplatform.api.service;

public class Service<T> {

    private final T service;
    private final String name;

    public Service(T service, String name) {
        this.service = service;
        this.name = name;
    }

    public T getService() {
        return service;
    }

    public String getName() {
        return name;
    }
}
