package xyz.auriium.openmineplatform.api.interfaceable;

import net.kyori.adventure.text.Component;

import java.util.UUID;

/**
 * Represents a low-type object that is tied to an object that may or may not exist on the platform
 * Since the object has no guarantee to existence, all stateless (void) operations should be considered
 * to fail if the user is not present on the server.
 *
 * If the library user needs objects that offer a more strong guarantee to existence, see ExistentRegistry.
 *
 * It or it's inheritors typically do not expose any state.
 * Exceptions include things like exposing the internal object via lambda ifPresent or via an unsafe telescope, or
 * intrinsically existence-ignorant values like the object uuid.
 */
public interface Interfaceable {

    UUID getObjectUUID();

    void sendComponent(Component component);
    void sendString(String message);
    void sendFormat(String message, Object... objects);

}
