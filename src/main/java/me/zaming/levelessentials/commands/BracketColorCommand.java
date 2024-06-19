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

public class BracketColorCommand implements CommandExecutor, TabCompleter {
    private final LevelEssentials plugin;
    private final List<String> colors = Arrays.asList(
            "&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9",
            "&a", "&b", "&c", "&d", "&e", "&f"
    );

    public BracketColorCommand(LevelEssentials plugin) {
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
            player.sendMessage(ChatColor.RED + "Usage: /bracketcolor <color>");
            return true;
        }

        String color = args[0].toLowerCase();
        if (!player.hasPermission("levelessentials.bracketcolor." + color.replace("&", ""))) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this color.");
            return true;
        }

        ChatColor chatColor = ChatColor.getByChar(color.replace("&", ""));
        if (chatColor == null) {
            player.sendMessage(ChatColor.RED + "Invalid color. Use color codes like &d.");
            return true;
        }

        PlayerData playerData = plugin.getPlayerData();
        FileConfiguration config = playerData.getPlayerConfig(player);
        config.set("bracketcolor", color);
        playerData.savePlayerConfig(player, config);

        player.sendMessage(ChatColor.GREEN + "Bracket color set to " + chatColor + color);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            return colors.stream()
                    .filter(color -> color.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
