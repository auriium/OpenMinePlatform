package xyz.auriium.openmineplatform.spigot;

import org.bukkit.entity.Player;

public interface Commander {

    void runAsPlayer(Player player, String cmd);
    void runAsServer(String cmd);

}
