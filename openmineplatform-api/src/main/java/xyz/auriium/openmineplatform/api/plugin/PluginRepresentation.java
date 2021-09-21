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

package xyz.auriium.openmineplatform.api.plugin;

import xyz.auriium.openmineplatform.api.*;

import java.util.function.Supplier;

public interface PluginRepresentation {

    /**
     * Gets the identity of the project
     * @return the identity
     */
    PlatformProjectIdentity getIdentity();

    /**
     * Gets all hook data
     * @return the hook insertion data
     */
    HookData getInsertionData();

    /**
     * This method is called every time the plugin is reloaded in order to produce a new instance of your plugin.
     *
     * @param platform the platform
     * @param reloadablePluginState the statecontroller
     * @return a new reloadableplugin
     */
    ReloadablePlugin supply(Platform platform, ReloadablePluginState reloadablePluginState);


}
