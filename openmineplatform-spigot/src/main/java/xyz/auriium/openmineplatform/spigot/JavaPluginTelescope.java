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

package xyz.auriium.openmineplatform.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.telescope.ExceptionalMapper;
import xyz.auriium.openmineplatform.api.telescope.Telescope;
import xyz.auriium.openmineplatform.api.telescope.TelescopeResult;

public class JavaPluginTelescope implements Telescope<Platform, JavaPlugin> {

    JavaPluginTelescope() {}

    @Override
    public TelescopeResult<Platform, JavaPlugin> telescope(Platform input) {
        if (!(input instanceof SpigotPlatform)) return TelescopeResult.fail(input, "Platform is not a SpigotPlatform");

        return TelescopeResult.complete(input, ((SpigotPlatform)input).getPlugin());
    }

    public static final ExceptionalMapper<Platform, JavaPlugin> EXCEPTIONAL = new ExceptionalMapper<>(new JavaPluginTelescope());
}
