package poltixe.spigot.minigamequeue;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;

//Command that either readies up or unreadies the command sender
public class CommandReady implements CommandExecutor {
    // Get an instance of the plugin
    private App app = App.getPlugin(App.class);

    // Runs when the command is executed
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Checks if the game is started
        if (app.gameStarted) {
            // Messages the executor that the game has already started
            sender.sendMessage("The game already started!");
            return true;
        }

        // Gets the target of the command (the sender in this case)
        Player target = Bukkit.getPlayer(sender.getName());

        // Checks if the played is already readied or not
        if (!target.getMetadata("ready").get(0).asBoolean()) {
            // Sets their metadata to true (readied up) if they are not already readied up
            target.setMetadata("ready", new FixedMetadataValue(app, true));
            // Broadcasts that the player has readied up
            Bukkit.broadcastMessage(ChatColor.AQUA + target.getName() + " has readied up!");
        } else {
            // Sets thier metadata to false (unreadied) iu they are not already readied up
            target.setMetadata("ready", new FixedMetadataValue(app, false));
            // Broadcasts that the player has unreadied
            Bukkit.broadcastMessage(ChatColor.AQUA + target.getName() + " has readied down?");
        }

        // Gets the server
        Server server = Bukkit.getServer();

        // Gets the online players as an array
        Object[] players = server.getOnlinePlayers().toArray();
        // Creates an array of ReadyStates with a length the same as the player count
        ReadyState[] playerReadyArray = new ReadyState[players.length];

        // Loops through all players in the server
        for (int i = 0; i < players.length; i++) {
            // Gets the player from the playerlist
            Player player = (Player) players[i];

            // Sets the corrosponding ReadyState in the ReadyState array to a new ReadyState
            // using the player and thier ready metadata
            playerReadyArray[i] = new ReadyState(player, player.getMetadata("ready").get(0).asBoolean());
        }

        // Create a new PlayerReadyEvent
        PlayerReadyEvent playerReadyEvent = new PlayerReadyEvent(target, playerReadyArray);
        // Call the new PlayerReadyEvent
        Bukkit.getPluginManager().callEvent(playerReadyEvent);

        return true;
    }

}
