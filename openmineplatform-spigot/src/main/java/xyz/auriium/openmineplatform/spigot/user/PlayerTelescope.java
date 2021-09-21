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

import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.telescope.ExceptionalMapper;
import xyz.auriium.openmineplatform.api.telescope.OptionalMapper;
import xyz.auriium.openmineplatform.api.telescope.TelescopeResult;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.telescope.Telescope;

/**
 * THIS IS UNSAFE AS HELL (Functionality like this is to be superceded by Existent, providing stateful objects is not Interfaceable's job)
 */
@Deprecated
public class PlayerTelescope implements Telescope<Interfaceable, Player> {

    @Override
    public TelescopeResult<Interfaceable, Player> telescope(Interfaceable input) {

        if (!(input instanceof SpigotUser)) return TelescopeResult.fail(input, "Interfaceable is not an instance of SpigotUser!");

        SpigotUser user = (SpigotUser) input;

        var optional = user.getPlayer();

        if (optional.isEmpty()) return TelescopeResult.fail(input, "Interfaceable is a SpigotUser but the targeted player is not online!");

        return TelescopeResult.complete(input, optional.get());
    }

    public static final ExceptionalMapper<Interfaceable, Player> EXCEPTIONAL = new ExceptionalMapper<>(new PlayerTelescope());
    public static final OptionalMapper<Interfaceable, Player> OPTIONAL = new OptionalMapper<>(new PlayerTelescope());

}
