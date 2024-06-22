package me.abasing.levelessentials.utils;

import me.abasing.levelessentials.LevelEssentials;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerData {
    private final LevelEssentials plugin;
    private final File playerDataFolder;
    private final Logger logger;
    private final Map<UUID, FileConfiguration> playerConfigs;

    public PlayerData(LevelEssentials plugin) {
        this.plugin = plugin;
        this.playerDataFolder = new File(plugin.getDataFolder(), "playerdata");
        this.logger = plugin.getLogger();
        this.playerConfigs = new HashMap<>();

        // Ensure playerdata folder exists
        if (!playerDataFolder.exists()) {
            if (!playerDataFolder.mkdirs()) {
                logger.severe("Failed to create playerdata folder!");
            }
        }
    }

    private File getPlayerFile(UUID uuid) {
        return new File(playerDataFolder, uuid.toString() + ".yml");
    }

    public FileConfiguration getPlayerConfig(Player player) {
        return getPlayerConfig(player.getUniqueId());
    }

    public FileConfiguration getPlayerConfig(UUID uuid) {
        if (playerConfigs.containsKey(uuid)) {
            return playerConfigs.get(uuid);
        }

        File playerFile = getPlayerFile(uuid);
        FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile);

        // Check if the file exists; create it if it doesn't
        if (!playerFile.exists()) {
            try {
                if (!playerFile.createNewFile()) {
                    throw new IOException("Failed to create new file");
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Could not create player data file for " + uuid, e);
                return null; // Return null on failure to create file
            }
        }

        playerConfigs.put(uuid, config);
        return config;
    }

    public void savePlayerConfig(Player player, FileConfiguration config) {
        savePlayerConfig(player.getUniqueId(), config);
    }

    public void savePlayerConfig(UUID uuid, FileConfiguration config) {
        try {
            config.save(getPlayerFile(uuid));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not save player data file for " + uuid, e);
        }
    }

    public void reloadPlayerConfig(UUID uuid) {
        File playerFile = getPlayerFile(uuid);
        if (!playerFile.exists()) {
            logger.warning("Player data file for " + uuid + " does not exist.");
            return;
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
        playerConfigs.put(uuid, config);
    }

    public void reloadAllPlayerConfigs() {
        playerConfigs.keySet().forEach(this::reloadPlayerConfig);
    }
}
