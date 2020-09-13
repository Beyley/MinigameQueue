package poltixe.spigot.minigamequeue;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerReadyEvent extends Event {
    private final Player player;
    private final ReadyState[] readyStates;

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

    public String getPlayerName() {
        return this.player.getName();
    }

    public Player getPlayer() {
        return this.player;
    }

    public ReadyState[] getReadyStates() {
        return readyStates;
    }
}
