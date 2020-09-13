package poltixe.spigot.minigamequeue;

import org.bukkit.event.*;

public class GameStartEvent extends Event {

    public GameStartEvent() {
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
