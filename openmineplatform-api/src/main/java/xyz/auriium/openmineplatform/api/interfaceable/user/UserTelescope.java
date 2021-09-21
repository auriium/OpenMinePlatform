/*
 * OpenMinePlatform
 * Copyright © 2021 Auriium
 *
 * OpenMinePlatform is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * OpenMinePlatform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenMinePlatform. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

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
