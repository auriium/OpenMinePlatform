package xyz.auriium.openmineplatform.api.apiprovide;

import xyz.auriium.openmineplatform.api.Platform;

public interface APIProvidingPlatform<T extends APIObject> extends Platform {

    T getAPI();

}
