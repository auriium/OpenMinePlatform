package xyz.auriium.openmineplatform.spigot;

import org.bukkit.ChatColor;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;

public class SpigotColorer implements Colorer {
    @Override
    public String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }
}
