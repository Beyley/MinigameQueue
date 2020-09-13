package poltixe.spigot.minigamequeue;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;

public class CommandForceStart implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GameStartEvent gameStartEvent = new GameStartEvent();
        Bukkit.getPluginManager().callEvent(gameStartEvent);

        return true;
    }
}
