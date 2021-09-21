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

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.binding.location.PlatformLocation;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.interfaceable.user.*;
import xyz.auriium.openmineplatform.api.telescope.TelescopeMapping;
import xyz.auriium.openmineplatform.spigot.Commander;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SpigotUser implements User {

    private final Map<String, Object> states = new ConcurrentHashMap<>();

    private final UUID uuid;
    private final UserPopper<Player> popper;
    private final Colorer colorer;
    private final Server server;
    private final BukkitAudiences audiences;
    private final Commander commander;

    public SpigotUser(UUID uuid, UserPopper<Player> popper, Colorer colorer, Server server, BukkitAudiences audiences, Commander commander) {
        this.uuid = uuid;
        this.popper = popper;
        this.colorer = colorer;
        this.server = server;
        this.audiences = audiences;
        this.commander = commander;
    }


    @Override
    public UUID getObjectUUID() {
        return uuid;
    }

    @Override
    public void sendComponent(Component component) {
        audiences.player(uuid).sendMessage(component);
    }

    @Override
    public void sendString(String message) {
        audiences.player(uuid).sendMessage(Component.text(colorer.color(message)));
    }

    @Override
    public void sendFormat(String message, Object... objects) {
        audiences.player(uuid).sendMessage(Component.text(String.format(message, objects)));
    }

    @Override
    public <T> T telescope(TelescopeMapping<T, Interfaceable> mapping) {
        return mapping.calculate(this);
    }

    @Override
    public Optional<String> getName() {
        return popper.pop(uuid).map(HumanEntity::getName);
    }

    @Override
    public void teleport(PlatformLocation platformLocation) {
        var optional = popper.pop(uuid);

        if (optional.isEmpty()) return;

        Player player = optional.get();

        String world = platformLocation.getWorld();
        Long pitch = platformLocation.getPitch();
        Long yaw = platformLocation.getYaw();
        World realWorld = world == null ? player.getWorld() : server.getWorld(world) == null ? player.getWorld() : server.getWorld(world);


        if (pitch == null || yaw == null) {
            player.teleport(new Location(realWorld, platformLocation.getX(), platformLocation.getY(), platformLocation.getZ()));
        } else {
            player.teleport(new Location(realWorld, platformLocation.getX(), platformLocation.getY(), platformLocation.getZ(), yaw, pitch));
        }

    }

    @Override
    public void sendActionbarComponent(Component component) {
        audiences.player(uuid).sendActionBar(component);
    }

    @Override
    public void sendActionbar(String string) {
        audiences.player(uuid).sendActionBar(Component.text(colorer.color(string)));
    }

    @Override
    public void sendSystem(String string) {
        popper.pop(uuid).ifPresent(player -> player.spigot().sendMessage(ChatMessageType.SYSTEM, TextComponent.fromLegacyText(colorer.color(string))));
    }

    @Override
    public void sendTitleComponent(Title title) {
        audiences.player(uuid).showTitle(title);
    }

    @Override
    public void sendTitle(PlatformTitle title) {
        popper.pop(uuid).ifPresent(player -> player.sendTitle(
                colorer.color(title.getTitle() == null ? "" : title.getTitle()),
                colorer.color(title.getSubtitle() == null ? "" : title.getSubtitle()),
                title.getFadein(),
                title.getStay(),
                title.getFadeout()));
    }

    @Override
    public void runCommandAsUser(String command) {
        popper.pop(uuid).ifPresent(player -> commander.runAsPlayer(player, command));
    }

    @Override
    public void runCommandAsPlatform(String command) {
        commander.runAsServer(command);
    }

    @Override
    public void sendClickable(String message, String command) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
        BaseComponent[] hoverComponent = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',message)).create();

        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverComponent));

        popper.pop(uuid).ifPresent(player -> player.spigot().sendMessage(component));
    }

    public Optional<Player> getPlayer() {
        return popper.pop(uuid);
    }

}
