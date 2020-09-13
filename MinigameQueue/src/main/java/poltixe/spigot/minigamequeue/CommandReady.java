package poltixe.spigot.minigamequeue;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;

public class CommandReady implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (App.getPlugin(App.class).gameStarted) {
            sender.sendMessage("The game already started!");
            return true;
        }

        Player target = Bukkit.getPlayer(sender.getName());

        if (!target.getMetadata("ready").get(0).asBoolean()) {
            target.setMetadata("ready", new FixedMetadataValue(App.getPlugin(App.class), true));
            Bukkit.broadcastMessage(ChatColor.AQUA + target.getName() + " has readied up!");
            // target.sendMessage("You have readied up!");
        } else {
            target.setMetadata("ready", new FixedMetadataValue(App.getPlugin(App.class), false));
            Bukkit.broadcastMessage(ChatColor.AQUA + target.getName() + " has readied down?");
            // target.sendMessage("You have readied down?");
        }

        Server server = Bukkit.getServer();

        Object[] players = server.getOnlinePlayers().toArray();
        ReadyState[] playerReadyArray = new ReadyState[players.length];

        for (int i = 0; i < players.length; i++) {
            Player player = (Player) players[i];

            playerReadyArray[i] = new ReadyState(player, player.getMetadata("ready").get(0).asBoolean());
        }

        PlayerReadyEvent playerReadyEvent = new PlayerReadyEvent(target, playerReadyArray); // Initialize your Event
        Bukkit.getPluginManager().callEvent(playerReadyEvent); // This fires the event and allows any listener to listen
                                                               // to the event

        return true;
    }

}
