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

import org.bukkit.Server;
import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.scheduling.Scheduler;

public class SpigotCommander implements Commander {

    private final Server server;
    private final Scheduler scheduler;

    public SpigotCommander(Server server, Scheduler scheduler) {
        this.server = server;
        this.scheduler = scheduler;
    }

    @Override
    public void runAsPlayer(Player player, String cmd) {

        if (server.isPrimaryThread()) {
            server.dispatchCommand(player, cmd);
            return;
        }

        scheduler.run(() -> server.dispatchCommand(player, cmd));


    }

    @Override
    public void runAsServer(String cmd) {
        if (server.isPrimaryThread()) {
            server.dispatchCommand(server.getConsoleSender(), cmd);
            return;
        }

        scheduler.run(() -> server.dispatchCommand(server.getConsoleSender(), cmd));
    }
}
