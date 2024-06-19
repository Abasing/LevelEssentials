package me.zaming.levelessentials.utils;

import me.zaming.levelessentials.LevelEssentials;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerData {
    private final LevelEssentials plugin;
    private final File playerDataFolder;
    private final Logger logger;

    public PlayerData(LevelEssentials plugin) {
        this.plugin = plugin;
        this.playerDataFolder = new File(plugin.getDataFolder(), "playerdata");
        this.logger = plugin.getLogger();
        if (!playerDataFolder.exists()) {
            playerDataFolder.mkdirs();
        }
    }

    private File getPlayerFile(Player player) {
        return new File(playerDataFolder, player.getUniqueId() + ".yml");
    }

    public FileConfiguration getPlayerConfig(Player player) {
        File playerFile = getPlayerFile(player);
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Could not create player data file for " + player.getName(), e);
            }
        }
        return YamlConfiguration.loadConfiguration(playerFile);
    }

    public void savePlayerConfig(Player player, FileConfiguration config) {
        try {
            config.save(getPlayerFile(player));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not save player data file for " + player.getName(), e);
        }
    }
}
