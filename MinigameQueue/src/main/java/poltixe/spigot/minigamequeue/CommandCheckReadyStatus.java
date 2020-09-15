package poltixe.spigot.minigamequeue;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

//Command to view the ReadyStates of all players
public class CommandCheckReadyStatus implements CommandExecutor {
    // Get an instance of the plugin
    private App app = App.getPlugin(App.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the game is started
        if (app.gameStarted) {
            // Message the player that the game has already started
            sender.sendMessage("The game already started!");
            return true;
        }

        // Get the server
        Server server = Bukkit.getServer();

        // Get all players as an array
        Object[] players = server.getOnlinePlayers().toArray();
        // Create a new ReadyState array of equal length to the player count
        ReadyState[] playerReadyArray = new ReadyState[players.length];

        // Loop through all players
        for (int i = 0; i < players.length; i++) {
            // Get the player from the player array
            Player player = (Player) players[i];

            // Set the corrosponding index in the PlayerReady array to a new ReadyState with
            // the player and the players metadata
            playerReadyArray[i] = new ReadyState(player, player.getMetadata("ready").get(0).asBoolean());
        }

        // Create a blank string to add on to
        String combinedString = "";

        // Loop through the PlayerReady array
        for (int i = 0; i < playerReadyArray.length; i++) {
            // Get the state
            ReadyState state = playerReadyArray[i];
            // Concatanate the string with data grabbed from the state
            combinedString = combinedString + state.player.getName() + " : " + Boolean.toString(state.readyState)
                    + "\n";
        }

        // Send the command executor the final string
        sender.sendMessage(combinedString);

        return true;
    }
}
