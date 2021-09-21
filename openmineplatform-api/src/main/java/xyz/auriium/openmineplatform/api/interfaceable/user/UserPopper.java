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
