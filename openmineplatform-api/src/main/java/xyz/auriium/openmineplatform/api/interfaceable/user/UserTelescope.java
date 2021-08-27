package xyz.auriium.openmineplatform.api.interfaceable.user;

import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.telescope.*;

import java.util.Optional;

public class UserTelescope implements Telescope<Interfaceable, User> {

    @Override
    public TelescopeResult<Interfaceable, User> telescope(Interfaceable input) {
        if (!(input instanceof User)) return TelescopeResult.fail(input, "Interfaceable provided is not a User or does not support requested operations");

        return TelescopeResult.complete(input, (User) input);
    }

    public static final ExceptionalMapper<Interfaceable, User> EXCEPTIONAL = new ExceptionalMapper<>(new UserTelescope());
    public static final OptionalMapper<Interfaceable, User> OPTIONAL = new OptionalMapper<>(new UserTelescope());

}
