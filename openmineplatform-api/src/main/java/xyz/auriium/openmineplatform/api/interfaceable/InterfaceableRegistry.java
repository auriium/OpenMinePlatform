package xyz.auriium.openmineplatform.api.interfaceable;

import xyz.auriium.openmineplatform.api.telescope.TelescopeMapping;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a registry for
 */
public interface InterfaceableRegistry {

    /**
     * Attempts to get a user by the uuid it has.
     * @param uuid the uuid of the user
     * @return an optional with a guarantee if the user is present at the moment of method call
     *
     * This essentially just functions as a way to group calls to the user object with
     * any other functions that require the user to be online at the instant of method calls
     * since interfaceables are safe enough to not mind being called even if their bound user is
     * offline.
     */
    Optional<Interfaceable> getCurrent(UUID uuid);

    /**
     * Gets a user by the uuid it has
     * @param uuid the uuid of the user
     * @return the user represented as an interfaceable
     */
    Interfaceable get(UUID uuid);

    /**
     * Gets all accessible users of type T
     * @return collection containing all users of type T
     */
    Collection<Interfaceable> getAll();

    /**
     * Attempts to get a user and internally cast it to whatever object is required
     * @param uuid the uuid of the user
     * @param telescope the converter
     * @param <O> output
     * @return output with a guarantee that the user is online at time of calling
     *
     * This essentially just functions as a way to group calls to the user object with
     * any other functions that require the user to be online at the instant of method calls
     * since interfaceables are safe enough to not mind being called even if their bound user is
     * offline.
     */
    <O> Optional<O> getTelescopingCurrent(UUID uuid, TelescopeMapping<O, Interfaceable> telescope);

    /**
     * Attempts to get a user and internally cast it to whatever object is required
     * @param uuid the uuid of the user
     * @param telescope the converter
     * @param <O> output
     * @return output object
     */
    <O> O getTelescoping(UUID uuid, TelescopeMapping<O, Interfaceable> telescope);

    void close();

}
