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
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.interfaceable.InterfaceableRegistry;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserPopper;
import xyz.auriium.openmineplatform.api.telescope.TelescopeMapping;
import xyz.auriium.openmineplatform.spigot.user.SpigotUser;

import java.util.*;

public class SpigotInterfaceableRegistry implements InterfaceableRegistry {

    private final Commander commander;
    private final UserPopper<Player> popper;
    private final Colorer colorer;
    private final Server server;

    public SpigotInterfaceableRegistry(Commander commander, UserPopper<Player> popper, Colorer colorer, Server server) {
        this.commander = commander;
        this.popper = popper;
        this.colorer = colorer;
        this.server = server;
    }

    @Override
    public Optional<Interfaceable> getCurrent(UUID uuid) {

        if (!popper.present(uuid)) return Optional.empty();

        return Optional.of(unsafe(uuid));
    }

    @Override
    public Interfaceable get(UUID uuid) {
        return unsafe(uuid);
    }

    @Override
    public Collection<Interfaceable> getAll() {

        Set<Interfaceable> interfaceables = new HashSet<>();

        for (Player player : server.getOnlinePlayers()) {
            interfaceables.add(unsafe(player.getUniqueId()));
        }

        return interfaceables;
    }

    public Interfaceable unsafe(UUID uuid) {
        return new SpigotUser(uuid, popper, colorer, server, commander);
    }


    @Override
    public <O> Optional<O> getTelescopingCurrent(UUID uuid, TelescopeMapping<O, Interfaceable> telescope) {
        if (popper.present(uuid)) return Optional.empty();

        O o = telescope.calculate(unsafe(uuid));

        return Optional.of(o);
    }

    @Override
    public <O> O getTelescoping(UUID uuid, TelescopeMapping<O, Interfaceable> telescope) {
        return telescope.calculate(unsafe(uuid));
    }

    @Override
    public void close() {

    }
}
