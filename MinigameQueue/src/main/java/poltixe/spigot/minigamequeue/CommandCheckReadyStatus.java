package poltixe.spigot.minigamequeue;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class CommandCheckReadyStatus implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (App.getPlugin(App.class).gameStarted) {
            sender.sendMessage("The game already started!");
            return true;
        }

        Server server = Bukkit.getServer();

        Object[] players = server.getOnlinePlayers().toArray();
        ReadyState[] playerReadyArray = new ReadyState[players.length];

        for (int i = 0; i < players.length; i++) {
            Player player = (Player) players[i];

            playerReadyArray[i] = new ReadyState(player, player.getMetadata("ready").get(0).asBoolean());
        }

        String combinedString = "";

        for (int i = 0; i < playerReadyArray.length; i++) {
            ReadyState state = playerReadyArray[i];
            combinedString = combinedString + state.player.getName() + " : " + Boolean.toString(state.readyState)
                    + "\n";
        }

        sender.sendMessage(combinedString);

        return true;
    }
}
