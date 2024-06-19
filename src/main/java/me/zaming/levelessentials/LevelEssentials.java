package me.zaming.levelessentials;

import me.zaming.levelessentials.commands.BracketColorCommand;
import me.zaming.levelessentials.commands.BracketsCommand;
import me.zaming.levelessentials.commands.LevelColorCommand;
import me.zaming.levelessentials.placeholders.*;
import me.zaming.levelessentials.utils.PlayerData;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class LevelEssentials extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("bracketcolor")).setExecutor(new BracketColorCommand(this));
        Objects.requireNonNull(getCommand("brackets")).setExecutor(new BracketsCommand(this));
        Objects.requireNonNull(getCommand("levelcolor")).setExecutor(new LevelColorCommand(this));

        Objects.requireNonNull(getCommand("bracketcolor")).setTabCompleter(new BracketColorCommand(this));
        Objects.requireNonNull(getCommand("brackets")).setTabCompleter(new BracketsCommand(this));
        Objects.requireNonNull(getCommand("levelcolor")).setTabCompleter(new LevelColorCommand(this));

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new LevelEssentialsPlaceholder(this).register();
        }
    }

    public PlayerData getPlayerData() {
        return new PlayerData(this);
    }
}
