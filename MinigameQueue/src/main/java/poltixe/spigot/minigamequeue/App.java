package poltixe.spigot.minigamequeue;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    public FileConfiguration config = getConfig();

    public boolean gameStarted = false;

    @Override
    public void onEnable() {
        config.addDefault("minPlayersForStart", 4);
        config.options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new EventListener(), this);

        this.getCommand("readycheck").setExecutor(new CommandCheckReadyStatus());
        this.getCommand("ready").setExecutor(new CommandReady());
        this.getCommand("forcestart").setExecutor(new CommandForceStart());
    }
}
