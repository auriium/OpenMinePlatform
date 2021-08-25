package xyz.auriium.openmineplatform.api.apiprovide;

public interface APIProvidingPlatform<T extends APIObject> {

    T getAPI();

}
