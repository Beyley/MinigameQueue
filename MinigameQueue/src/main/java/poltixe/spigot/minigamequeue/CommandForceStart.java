package poltixe.spigot.minigamequeue;

import org.bukkit.Bukkit;
import org.bukkit.command.*;

//Command that forces a GameStartEvent no matter who is readied
public class CommandForceStart implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Create a new GameStartEvent
        GameStartEvent gameStartEvent = new GameStartEvent();
        // Call the GameStartEvent
        Bukkit.getPluginManager().callEvent(gameStartEvent);

        return true;
    }
}
