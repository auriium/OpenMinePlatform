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

package xyz.auriium.example;

import xyz.auriium.openmineplatform.api.HookData;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.PlatformProjectIdentity;
import xyz.auriium.openmineplatform.api.plugin.PluginRepresentation;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePlugin;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;
import xyz.auriium.openmineplatform.spigot.JavaPluginTelescope;
import xyz.auriium.openmineplatform.spigot.SpigotBootstrap;

public class HelloWorldBootstrap extends SpigotBootstrap {

    @Override
    public PluginRepresentation representation() {
        return new Representation();
    }

    public static class Representation implements PluginRepresentation {

        @Override
        public PlatformProjectIdentity getIdentity() {
            return new PlatformProjectIdentity("HelloWorld","Auriium","1.0S");
        }

        @Override
        public HookData getInsertionData() {
            return HookData.make();
        }

        @Override
        public ReloadablePlugin supply(Platform platform, ReloadablePluginState reloadablePluginState) {
            return new HelloWorldPlugin(platform.telescope(JavaPluginTelescope.EXCEPTIONAL), reloadablePluginState);
        }

    }

}
