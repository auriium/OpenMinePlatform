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

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import xyz.auriium.openmineplatform.api.binding.location.PlatformLocation;
import xyz.auriium.openmineplatform.api.binding.location.UnboundPlatformLocation;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;

import java.util.Optional;

/**
 * Represents a meta-interfaceable that is confirmed to exist at the moment and therefore can offer some tentative
 * optional state (this state is not guaranteed and so all stateful functions will return an optional)
 */
public interface User extends Interfaceable {

    /**
     * Returns the user's name if they are present on the server
     * @return
     */
    Optional<String> getName();


    void teleport(PlatformLocation platformLocation);

    void sendActionbarComponent(Component component);
    void sendActionbar(String string);

    void sendSystem(String string);

    void sendTitleComponent(Title title);
    void sendTitle(PlatformTitle title);

    void runCommandAsUser(String command);
    void runCommandAsPlatform(String command);

    @Deprecated
    void sendClickable(String message, String command);

}
