package xyz.auriium.openmineplatform.api.interfaceable;

import xyz.auriium.openmineplatform.api.Telescope;
import xyz.auriium.openmineplatform.api.TelescopeResult;
import xyz.auriium.openmineplatform.api.interfaceable.user.User;

public class UserTelescope implements Telescope<Interfaceable, User> {

    static UserTelescope INSTANCE = new UserTelescope();

    UserTelescope() {}

    @Override
    public TelescopeResult<Interfaceable, User> telescope(Interfaceable input) {

        if (input instanceof User) {
            return TelescopeResult.complete(input, (User)input);
        }

        return TelescopeResult.fail(input, "Input object is not of type User");
    }

    public static UserTelescope of() {
        return INSTANCE;
    }

}
