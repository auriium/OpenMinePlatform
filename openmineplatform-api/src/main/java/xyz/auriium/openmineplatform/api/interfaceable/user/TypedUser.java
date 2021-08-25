package xyz.auriium.openmineplatform.api.interfaceable.user;

import java.util.Optional;
import java.util.function.Consumer;

import xyz.auriium.openmineplatform.api.interfaceable.NotExistentException;

/**
 * Represents a user that can have the underlying platformed user accessed
 *
 * @param <T> the type of the platformed user
 */
public interface TypedUser<T> extends User {

    /**
     * Gets the object bound to this user
     *
     * WARNING: Do not store this accessed object anywhere. That isn't what user (interfaceable) is for,
     * instead, if you need to store that object, you are probably better off using the existentialregistry instead.
     *
     * @return an optional containing this object
     */
    Optional<T> accessRaw();

    /**
     * Gets the object bound to this user
     *
     * WARNING: Do not store this accessed object anywhere. That isn't what user (interfaceable) is for,
     * instead, if you need to store that object, you are probably better off using the existentialregistry instead.
     *
     * @throws NotExistentException if no user is present.
     * @return this object
     */
    T accessThrow();

    /**
     * Accesses the value safely and executes the provided consumer if the user is on the server.
     *
     * @param consumer consumer
     */
    void access(Consumer<T> consumer);

    /**
     * Gets the class for the type of object this stores
     * @return the class
     */
    Class<T> getUserClass();


}
