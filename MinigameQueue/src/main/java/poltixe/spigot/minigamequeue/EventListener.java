package poltixe.spigot.minigamequeue;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.metadata.*;

import net.md_5.bungee.api.ChatColor;

public class EventListener implements Listener {
    // Get an instance of the App class
    App app = App.getPlugin(App.class);

    // Get the file configuration from the App class
    FileConfiguration config = app.getConfig();

    // Called when a player joins the server
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Gets the target of the event
        Player target = event.getPlayer();

        // Sets metadata
        target.setMetadata("ready", new FixedMetadataValue(App.getPlugin(App.class), false));

        // Checks if the game is started
        if (app.gameStarted) {
            // If the game is already going, then set their gamemode to spectator
            target.setGameMode(GameMode.SPECTATOR);
        } else {
            // If the game is yet to start, set thier gamemode to adventure
            target.setGameMode(GameMode.ADVENTURE);
        }
    }

    // Called when a player changes their ready state
    @EventHandler
    public void onReady(PlayerReadyEvent event) {
        // Value for knowing whether or not to start the game
        Boolean startGame = true;

        // Gets the server
        Server server = Bukkit.getServer();

        // Loops through all ready states
        for (ReadyState state : event.getReadyStates()) {
            // Checks if someone is not ready
            if (!state.readyState) {
                // Set startGame to false, stopping the game from starting
                startGame = false;
            }
        }

        // If startGame, then run the code to start the game
        if (startGame) {
            // Checks if the minimum amount of players in the game was reached
            if (server.getOnlinePlayers().size() >= config.getInt("minPlayersForStart")) {
                // Creates a new GameStartEvent
                GameStartEvent gameStartEvent = new GameStartEvent();
                // Calls the GameStartEvent
                Bukkit.getPluginManager().callEvent(gameStartEvent);
            } else {
                // Broadcasts a message saying that the game would have started if there was
                // enough players
                Bukkit.broadcastMessage(
                        ChatColor.RED + "All players ready, but there are not enough players to start the game.");
            }
        }
    }

    // Called when the game starts
    @EventHandler
    public void onGameStart(GameStartEvent event) {
        // Sets the gameStarted variable to true
        app.gameStarted = true;

        // Broadcasts a message saying that the game is starting
        Bukkit.broadcastMessage(ChatColor.BLUE + "Game starting!");
    }
}
