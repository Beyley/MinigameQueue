package poltixe.spigot.minigamequeue;

import org.bukkit.entity.*;
import org.bukkit.event.*;

// Event to say a player readied
public class PlayerReadyEvent extends Event {
    // The player that readied
    private final Player player;
    // The states of all the players (mainly for convenience)
    private final ReadyState[] readyStates;

    // Define the constructor requireing a Player and a ReadyState array
    public PlayerReadyEvent(Player player, ReadyState[] readyStates) {
        this.player = player;
        this.readyStates = readyStates;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    // Gets the player name
    public String getPlayerName() {
        return this.player.getName();
    }

    // Gets the player object
    public Player getPlayer() {
        return this.player;
    }

    // Gets the readystates of all the players
    public ReadyState[] getReadyStates() {
        return readyStates;
    }
}
