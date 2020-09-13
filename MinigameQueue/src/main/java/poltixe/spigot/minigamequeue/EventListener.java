package poltixe.spigot.minigamequeue;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.metadata.*;

import net.md_5.bungee.api.ChatColor;

public class EventListener implements Listener {
    // App app = new App();

    FileConfiguration config = App.getPlugin(App.class).getConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player target = event.getPlayer();

        target.setMetadata("ready", new FixedMetadataValue(App.getPlugin(App.class), false));
        // target.setMetadata("imposter", new
        // FixedMetadataValue(App.getPlugin(App.class), false));
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
        App.getPlugin(App.class).gameStarted = true;
        Bukkit.broadcastMessage(ChatColor.BLUE + "Game starting!");
    }
}
