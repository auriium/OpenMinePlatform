package xyz.auriium.openmineplatform.api.interfaceable;

import java.util.UUID;

/**
 * Exception thrown when an action is called to a {@link Interfaceable} but the underlying user is not present
 * Also thrown when a registry request is called but there is no underlying user
 *
 */
public class NotExistentException extends RuntimeException {
    public NotExistentException(UUID uuid, String message) {
        super(String.format("For UUID: [%s] %s", uuid, message));
    }
}
