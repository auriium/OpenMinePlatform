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

import xyz.auriium.openmineplatform.api.Platform;

public class ReloadablePluginControllerImpl implements ReloadablePluginController{

    private final Platform platform;
    private final PluginRepresentation representation;

    private volatile ReloadablePlugin plugin;

    public ReloadablePluginControllerImpl(Platform platform, PluginRepresentation representation) {
        this.platform = platform;
        this.representation = representation;
    }


    @Override
    public ReloadablePlugin getCurrent() {
        return plugin;
    }

    @Override
    public void load() {
        if (platform == null) throw new IllegalStateException("Platform is not present! Cannot load plugin!");
        plugin = representation.supply(platform, this);

        plugin.onStart();
    }

    @Override
    public void stop() {
        plugin.onStop();

        plugin = null;
    }

    @Override
    public void reload() {
        if (platform == null) throw new IllegalStateException("Platform is not present! Cannot reload plugin!");

        plugin.onStop();

        plugin = representation.supply(platform, this);

        plugin.onStart();
    }
}
