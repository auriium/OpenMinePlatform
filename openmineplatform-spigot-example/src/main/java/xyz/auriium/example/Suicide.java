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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;

public class Suicide implements CommandExecutor {

    private final ReloadablePluginState state;

    public Suicide(ReloadablePluginState state) {
        this.state = state;
    }

    @Override
    public boolean onCommand( CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("Restarting!");
        state.reload();

        return true;
    }
}
