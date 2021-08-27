package xyz.auriium.openmineplatform.api.interfaceable.user;

/**
 * Represents an object added to the user
 * @param <T>
 */
public interface State<T> {

    String serviceName();

    T getDefaultInsertion();
    T retrieve(Object object);

}
