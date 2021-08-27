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
import xyz.auriium.openmineplatform.api.binding.location.UnboundPlatformLocation;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.interfaceable.NotExistentException;
import xyz.auriium.openmineplatform.api.interfaceable.user.*;
import xyz.auriium.openmineplatform.api.telescope.TelescopeMapping;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class SpigotUser implements User {

    private final Map<String, Object> states = new ConcurrentHashMap<>();

    private final UUID uuid;
    private final UserPopper<Player> popper;
    private final Colorer colorer;
    private final Server server;
    private final BukkitAudiences audiences;

    public SpigotUser(UUID uuid, UserPopper<Player> popper, Colorer colorer, Server server, BukkitAudiences audiences) {
        this.uuid = uuid;
        this.popper = popper;
        this.colorer = colorer;
        this.server = server;
        this.audiences = audiences;
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
        audiences.player(uuid).sendMessage(Component.text(message));
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
        audiences.player(uuid).sendMessage(component);
    }

    @Override
    public void sendActionbar(String string) {
        audiences.player(uuid).sendMessage(Component.text(string));
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
        popper.pop(uuid).ifPresent(player -> {
            server.dispatchCommand(player, command);
        });
    }

    @Override
    public void runCommandAsPlatform(String command) {
        server.dispatchCommand(server.getConsoleSender(), command);
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
