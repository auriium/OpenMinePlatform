package xyz.auriium.openmineplatform.spigot;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.openmineplatform.api.binding.location.UnboundPlatformLocation;
import xyz.auriium.openmineplatform.api.interfaceable.NotExistentException;
import xyz.auriium.openmineplatform.api.interfaceable.user.PlatformTitle;
import xyz.auriium.openmineplatform.api.interfaceable.user.TypedUser;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserPopper;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class SpigotUser implements TypedUser<Player> {

    private final UUID uuid;
    private final UserPopper<Player> popper;
    private final Colorer colorer;
    private final Server server;

    public SpigotUser(UUID uuid, UserPopper<Player> popper, Colorer colorer, Server server) {
        this.uuid = uuid;
        this.popper = popper;
        this.colorer = colorer;
        this.server = server;
    }


    @Override
    public UUID getObjectUUID() {
        return uuid;
    }

    @Override
    public void sendComponent(Component component) {
        popper.pop(uuid).ifPresent(
                player -> player.sendMessage(colorer.color(component.toString()))
        ); //TODO fix
    }

    @Override
    public void sendString(String message) {
        popper.pop(uuid).ifPresent(player -> player.sendMessage(colorer.color(message)));
    }

    @Override
    public void sendFormat(String message, Object... objects) {
        popper.pop(uuid).ifPresent(player -> player.sendMessage(colorer.color(String.format(message, objects))));
    }

    @Override
    public Optional<Player> accessRaw() {
        return popper.pop(uuid);
    }

    @Override
    public Player accessThrow() {
        return accessRaw().orElseThrow(() -> new NotExistentException(uuid, "Attempted to access user but user was not found on server!"));
    }

    @Override
    public void access(Consumer<Player> consumer) {
        popper.pop(uuid).ifPresent(consumer);
    }

    @Override
    public Class<Player> getUserClass() {
        return Player.class;
    }


    @Override
    public Optional<String> getName() {
        return popper.pop(uuid).map(HumanEntity::getName);
    }

    @Override
    public void teleport(UnboundPlatformLocation platformLocation) {
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
        popper.pop(uuid).ifPresent(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(colorer.color(component.toString())))); //TODO fix
    }

    @Override
    public void sendActionbar(String string) {
        popper.pop(uuid).ifPresent(player ->player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(colorer.color(string))));
    }

    @Override
    public void sendSystem(String string) {
        popper.pop(uuid).ifPresent(player -> player.spigot().sendMessage(ChatMessageType.SYSTEM, TextComponent.fromLegacyText(colorer.color(string))));
    }

    @Override
    public void sendTitleComponent(Title title) {
        popper.pop(uuid).ifPresent(player -> player.sendMessage(title.toString())); //TODO
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
}
