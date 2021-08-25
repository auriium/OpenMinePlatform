package xyz.auriium.openmineplatform.api;

public interface ServiceHook<T> {

    T execute(Platform platform);

}
