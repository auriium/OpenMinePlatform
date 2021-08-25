package xyz.auriium.openmineplatform.api;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface RegistryArchetype<T> {

    /**
     * Attempts to get a user by the uuid it has.
     * @param uuid the uuid of the user
     * @return an optional with whether the user is present
     */
    Optional<T> getByUUID(UUID uuid);

    /**
     * Attempts to get a user and internally cast it to whatever object is required
     * @param uuid the uuid of the user
     * @param telescope the object-function that converts the
     * @param <O>
     * @return
     */
    <O extends T> Optional<O> getTelescoping(UUID uuid, Telescope<T,O> telescope);

    /**
     * Gets all accessible users of type T
     * @return collection containing all users of type T
     */
    Collection<T> getAll();

}
