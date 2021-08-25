package xyz.auriium.example;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;

public class Suicide implements CommandExecutor {

    private final ReloadablePluginState state;

    public Suicide(ReloadablePluginState state) {
        this.state = state;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage("Restarting!");
        state.reload();

        return true;
    }
}
