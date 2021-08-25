package xyz.auriium.openmineplatform.api.interfaceable;

import xyz.auriium.openmineplatform.api.TelescopeResult;

import java.util.Optional;

/**
 * Handler that throws a telescopefail exception when a telescoping fails
 */
public class ExceptionalTelescopeHandler implements TelescopeHandler {
    @Override
    public <O> Optional<O> handleResult(TelescopeResult<Interfaceable, O> result) {

        if (!result.isSuccess()) throw new InvalidUserRescopeException(result.getError());

        return Optional.of(result.getCompletion());
    }
}
