package xyz.auriium.openmineplatform.api;

public interface TypedPlatform<T> extends Platform {

    T getBoundPlatform();

}
