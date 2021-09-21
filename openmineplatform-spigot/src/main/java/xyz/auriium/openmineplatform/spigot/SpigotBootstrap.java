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
import xyz.auriium.openmineplatform.api.Bootstrap;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.PlatformLauncher;
import xyz.auriium.openmineplatform.api.plugin.PluginRepresentation;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginController;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginControllerImpl;

public abstract class SpigotBootstrap extends JavaPlugin implements Bootstrap {

    private ReloadablePluginController controller;

    private volatile Platform platform;

    @Override
    public void onEnable() {

        //0. Create platform

        PluginRepresentation representation = representation();
        platform = generateLauncher().launch(representation.getIdentity());

            //a. Activate all startup hooks

        representation.getInsertionData().insert(platform, true);

        //1. Initialize plugin
        controller = new ReloadablePluginControllerImpl(platform, representation());
        controller.load();

        representation.getInsertionData().insert(platform, false);

    }

    @Override
    public void onDisable() {
        if (controller == null) throw new IllegalStateException("Warning: Plugin controller was never initialized!");

        controller.stop();

        platform.interRegistry().close();
    }

    @Override
    public PlatformLauncher generateLauncher() {
        return new SpigotLauncher(this);
    }
}
