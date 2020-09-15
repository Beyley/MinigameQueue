package poltixe.spigot.minigamequeue;

import org.bukkit.entity.Player;

//Object to represent a players state in regards to readying
public class ReadyState {
    // The player
    public Player player;
    // Thier ready state itself
    public boolean readyState;

    // Define the constructor requiring a Player and their ready state itself
    ReadyState(Player player, boolean readyState) {
        this.player = player;
        this.readyState = readyState;
    }
}
