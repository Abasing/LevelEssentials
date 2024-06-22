package me.abasing.levelessentials;

import me.abasing.levelessentials.commands.BracketColorCommand;
import me.abasing.levelessentials.commands.LevelColorCommand;
import me.abasing.levelessentials.commands.BracketsCommand;
import me.abasing.levelessentials.commands.LevelEssentialsCommand;
import me.abasing.levelessentials.expansion.papi.LevelEssentialsExpansion;
import me.abasing.levelessentials.utils.PlayerData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;
import java.util.ResourceBundle;

public class LevelEssentials extends JavaPlugin {
    private PlayerData playerData;
    private ResourceBundle messages;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        playerData = new PlayerData(this);
        messages = ResourceBundle.getBundle("messages");

        Objects.requireNonNull(getCommand("bracketcolor")).setExecutor(new BracketColorCommand(this));
        Objects.requireNonNull(getCommand("brackets")).setExecutor(new BracketsCommand(this));
        Objects.requireNonNull(getCommand("levelcolor")).setExecutor(new LevelColorCommand(this));
        Objects.requireNonNull(getCommand("levelessentials")).setExecutor(new LevelEssentialsCommand(this));

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new LevelEssentialsExpansion(this).register();
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Successfully hooked into PlaceholderAPI.");
        } else {
            getLogger().warning("PlaceholderAPI not detected. Placeholders will not work.");
        }
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public String getMessage(@NotNull String key) {
        return ChatColor.translateAlternateColorCodes('&', messages.getString(key));
    }

    public void reloadMessages() {
        messages = ResourceBundle.getBundle("messages");
    }

    public void reloadPlayerData() {
        File playerDataFolder = new File(getDataFolder(), "playerdata");
        if (playerDataFolder.exists() && playerDataFolder.isDirectory()) {
            for (File file : Objects.requireNonNull(playerDataFolder.listFiles())) {
                if (file.isFile() && file.getName().endsWith(".yml")) {
                    YamlConfiguration.loadConfiguration(file);
                }
            }
        }
    }
}