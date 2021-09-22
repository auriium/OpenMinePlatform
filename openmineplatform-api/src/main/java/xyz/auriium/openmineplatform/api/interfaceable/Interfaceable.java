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

package xyz.auriium.openmineplatform.api.interfaceable;

import xyz.auriium.openmineplatform.api.telescope.TelescopeMapping;

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

    void sendString(String message);
    void sendFormat(String message, Object... objects);

    <T> T telescope(TelescopeMapping<T, Interfaceable> mapping);

}
