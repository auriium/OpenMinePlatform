package xyz.auriium.openmineplatform.api.interfaceable.user;

import xyz.auriium.openmineplatform.api.*;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;

public class TypedUserTelescope<T> implements Telescope<Interfaceable, TypedUser<T>> {

    private final Class<T> clazz;

    TypedUserTelescope(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public TelescopeResult<Interfaceable, TypedUser<T>> telescope(Interfaceable user) {

        if (!(user instanceof TypedUser)) return TelescopeResult.fail(user, "Interfaceable is not instance of TypedUser!");

        var s = (TypedUser<?>) user;

        if (!clazz.isAssignableFrom(s.getUserClass())) return TelescopeResult.fail(user, "User does not have same type or inherited type as requested TypedUser cast!");

        return TelescopeResult.complete(user, (TypedUser<T>) s);
    }

    public static <T> TypedUserTelescope<T> of(Class<T> requestedPlatformType) {
        return new TypedUserTelescope<>(requestedPlatformType);
    }

}
