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
