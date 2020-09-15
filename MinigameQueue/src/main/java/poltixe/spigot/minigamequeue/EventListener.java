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
    App app = App.getPlugin(App.class);

    FileConfiguration config = app.getConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player target = event.getPlayer();

        target.setMetadata("ready", new FixedMetadataValue(App.getPlugin(App.class), false));

        if (app.gameStarted) {
            target.setGameMode(GameMode.SPECTATOR);
        } else {
            target.setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler
    public void onReady(PlayerReadyEvent event) {
        Boolean startGame = true;

        Server server = Bukkit.getServer();

        for (ReadyState state : event.getReadyStates()) {
            if (!state.readyState) {
                startGame = false;
            }
        }

        if (startGame) {
            if (server.getOnlinePlayers().size() >= config.getInt("minPlayersForStart")) {
                GameStartEvent gameStartEvent = new GameStartEvent();
                Bukkit.getPluginManager().callEvent(gameStartEvent);
            } else {
                Bukkit.broadcastMessage(
                        ChatColor.RED + "All players ready, but there are not enough players to start the game.");
            }
        }
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        app.gameStarted = true;

        Bukkit.broadcastMessage(ChatColor.BLUE + "Game starting!");
    }
}
