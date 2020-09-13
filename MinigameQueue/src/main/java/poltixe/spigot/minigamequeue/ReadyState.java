package poltixe.spigot.minigamequeue;

import org.bukkit.entity.Player;

public class ReadyState {
    public Player player;
    public boolean readyState;

    ReadyState(Player player, boolean readyState) {
        this.player = player;
        this.readyState = readyState;
    }
}
