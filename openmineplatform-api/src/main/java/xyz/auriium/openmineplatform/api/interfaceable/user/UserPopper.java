package xyz.auriium.openmineplatform.api.interfaceable.user;

import java.util.Optional;
import java.util.UUID;

public interface UserPopper<T> {

    /**
     * Attempts to retrieve a platform-bound object based on UUID
     * @param uuid the id of the object
     * @return an optional containing the platform bound object
     */
    Optional<T> pop(UUID uuid);

    /**
     * Method representing whether a specific user is online
     * @param uuid the uuid of the user
     * @return whether the user is online or not.
     */
    boolean present(UUID uuid);

}
