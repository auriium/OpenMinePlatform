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

import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePlugin;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;

public class HelloWorldPlugin implements ReloadablePlugin {

    private final JavaPlugin plugin;
    private final ReloadablePluginState state;

    private int cum = 0;

    public HelloWorldPlugin(JavaPlugin plugin, ReloadablePluginState state) {
        this.plugin = plugin;
        this.state = state;
    }

    @Override
    public void onStart() {
        cum++;

        System.out.println("Hello World! Activating reload ensurement counter! Value of retainment is: " + cum);
        System.out.println("Registering reload command. Please execute it at your discretion.");

        plugin.getCommand("reloadexample").setExecutor(new Suicide(state)); //should sever this platform
    }

    @Override
    public void onStop() {
        System.out.println("Goodbye!");
    }


}
