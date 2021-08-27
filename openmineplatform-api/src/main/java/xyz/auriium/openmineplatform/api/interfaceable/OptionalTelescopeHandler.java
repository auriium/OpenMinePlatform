package xyz.auriium.openmineplatform.api.interfaceable;

import xyz.auriium.openmineplatform.api.telescope.TelescopeResult;

import java.util.Optional;

public class OptionalTelescopeHandler implements TelescopeHandler{
    @Override
    public <O> Optional<O> handleResult(TelescopeResult<Interfaceable, O> result) {

        if (!result.isSuccess()) return Optional.empty();

        return Optional.of(result.getCompletion());
    }
}
