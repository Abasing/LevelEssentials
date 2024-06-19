package me.zaming.levelessentials.commands;

import me.zaming.levelessentials.LevelEssentials;
import me.zaming.levelessentials.utils.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
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
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: /brackets <angle|square|brace>");
            return true;
        }

        String type = args[0].toLowerCase();
        if (!player.hasPermission("levelessentials.bracket." + type)) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this bracket type.");
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
                player.sendMessage(ChatColor.RED + "Invalid bracket type. Use angle, square, or brace.");
                return true;
        }

        PlayerData playerData = plugin.getPlayerData();
        FileConfiguration config = playerData.getPlayerConfig(player);
        config.set("brackets.left", leftBracket);
        config.set("brackets.right", rightBracket);
        playerData.savePlayerConfig(player, config);

        player.sendMessage(ChatColor.GREEN + "Brackets set to " + leftBracket + " and " + rightBracket);
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
