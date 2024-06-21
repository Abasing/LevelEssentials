package me.abasing.levelessentials.commands;

import me.abasing.levelessentials.LevelEssentials;
import me.abasing.levelessentials.utils.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BracketsCommand implements CommandExecutor, TabCompleter {
    private final LevelEssentials plugin;
    private final List<String> brackets = Arrays.asList("angle", "square", "brace");

    public BracketsCommand(LevelEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessage("player_only"));
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage(plugin.getMessage("usage_brackets"));
            return true;
        }

        String type = args[0].toLowerCase();
        if (!player.hasPermission("levelessentials.bracket." + type)) {
            player.sendMessage(plugin.getMessage("no_permission"));
            return true;
        }

        String leftBracket;
        String rightBracket;

        switch (type) {
            case "angle":
                leftBracket = "<";
                rightBracket = ">";
                break;
            case "square":
                leftBracket = "[";
                rightBracket = "]";
                break;
            case "brace":
                leftBracket = "{";
                rightBracket = "}";
                break;
            default:
                player.sendMessage(plugin.getMessage("invalid_bracket_type"));
                return true;
        }

        PlayerData playerData = plugin.getPlayerData();
        FileConfiguration config = playerData.getPlayerConfig(player);
        config.set("brackets.left", leftBracket);
        config.set("brackets.right", rightBracket);
        playerData.savePlayerConfig(player, config);

        player.sendMessage(plugin.getMessage("bracket_changed").replace("{type}", type));
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            return brackets.stream()
                    .filter(bracket -> bracket.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
