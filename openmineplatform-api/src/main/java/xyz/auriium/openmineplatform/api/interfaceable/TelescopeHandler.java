package xyz.auriium.openmineplatform.api.interfaceable;

import xyz.auriium.openmineplatform.api.TelescopeResult;

import java.util.Optional;

public interface TelescopeHandler {

    <O> Optional<O> handleResult(TelescopeResult<Interfaceable, O> result);

}
