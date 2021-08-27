package xyz.auriium.openmineplatform.spigot.user;

import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.telescope.ExceptionalMapper;
import xyz.auriium.openmineplatform.api.telescope.OptionalMapper;
import xyz.auriium.openmineplatform.api.telescope.TelescopeResult;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.telescope.Telescope;

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
