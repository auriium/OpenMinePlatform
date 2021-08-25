package xyz.auriium.openmineplatform.spigot;

import org.bukkit.entity.Player;
import xyz.auriium.openmineplatform.api.TelescopeResult;
import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.Telescope;
import xyz.auriium.openmineplatform.api.interfaceable.user.TypedUser;

public class PlayerTelescope implements Telescope<Interfaceable, TypedUser<Player>> {
    @Override
    public TelescopeResult<Interfaceable, TypedUser<Player>> telescope(Interfaceable input) {




        return null;
    }
}
