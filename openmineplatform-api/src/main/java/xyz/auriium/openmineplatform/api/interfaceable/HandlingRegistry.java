package xyz.auriium.openmineplatform.api.interfaceable;

import xyz.auriium.openmineplatform.api.telescope.Telescope;

import java.util.Optional;
import java.util.UUID;

public abstract class HandlingRegistry implements InterfaceableRegistry {

    protected final TelescopeHandler handler;

    protected HandlingRegistry(TelescopeHandler handler) {
        this.handler = handler;
    }

    @Override
    public <O extends Interfaceable> Optional<O> getTelescoping(UUID uuid, Telescope<Interfaceable, O> telescope) {

        var op = getByUUID(uuid);

        if (op.isEmpty()) return Optional.empty();

        var result = telescope.telescope(op.get());

        return handler.handleResult(result);
    }

    protected abstract Interfaceable unsafe(UUID uuid);
}
