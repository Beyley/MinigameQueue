package poltixe.spigot.minigamequeue;

import org.bukkit.Bukkit;
import org.bukkit.command.*;

public class CommandForceStart implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GameStartEvent gameStartEvent = new GameStartEvent();
        Bukkit.getPluginManager().callEvent(gameStartEvent);

        return true;
    }
}
