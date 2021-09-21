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

package xyz.auriium.openmineplatform.spigot.user;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserPopper;

import java.util.Optional;
import java.util.UUID;

public class SpigotPopper implements UserPopper<Player> {

    private final Server server;

    public SpigotPopper(Server server) {
        this.server = server;
    }

    @Override
    public Optional<Player> pop(UUID uuid) {
        return Optional.ofNullable(server.getPlayer(uuid));
    }

    @Override
    public boolean present(UUID uuid) {
        return server.getPlayer(uuid) != null;
    }
}
