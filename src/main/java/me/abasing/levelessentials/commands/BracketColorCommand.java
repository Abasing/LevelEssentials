package me.abasing.levelessentials.commands;

import me.abasing.levelessentials.LevelEssentials;
import me.abasing.levelessentials.utils.PlayerData;
import org.bukkit.ChatColor;
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
            sender.sendMessage(ChatColor.RED + plugin.getMessage("player_only"));
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("§b§l[L§3§lE] §7Unknown color. Available colors are:");
            player.sendMessage("§8▪ §fWhite §7- §f§§r§ff");
            player.sendMessage("§8▪ §eYellow §7- §e§§r§ee");
            player.sendMessage("§8▪ §dLight Purple §7- §d§§r§dd");
            player.sendMessage("§8▪ §cRed §7- §c§§r§cc");
            player.sendMessage("§8▪ §bAqua §7- §b§§r§bd");
            player.sendMessage("§8▪ §aGreen §7- §a§§r§aa");
            player.sendMessage("§8▪ §9Blue §7- §9§§r§99");
            player.sendMessage("§8▪ §8Dark Gray §7- §8§§r§88");
            player.sendMessage("§8▪ §7Gray §7- §7§§r§77");
            player.sendMessage("§8▪ §6Gold §7- §6§§r§66");
            player.sendMessage("§8▪ §5Dark Purple §7- §5§§r§55");
            player.sendMessage("§8▪ §4Dark Red §7- §4§§r§44");
            player.sendMessage("§8▪ §3Dark Aqua §7- §3§§r§33");
            player.sendMessage("§8▪ §2Dark Green §7- §2§§r§22");
            player.sendMessage("§8▪ §1Dark Blue §7- §1§§r§10");
            player.sendMessage("§8▪ §0Black §7- §0§§r§00");
            return true;
        }

        String color = args[0].toLowerCase();
        if (!color.startsWith("&")) {
            player.sendMessage(ChatColor.RED + plugin.getMessage("error_invalid_color_format"));
            return true;
        }

        if (!player.hasPermission("levelessentials.bracketcolor." + color.replace("&", ""))) {
            player.sendMessage(ChatColor.RED + plugin.getMessage("no_permission"));
            return true;
        }

        ChatColor chatColor = ChatColor.getByChar(color.replace("&", ""));
        if (chatColor == null) {
            player.sendMessage(ChatColor.RED + plugin.getMessage("error_invalid_color_code"));
            return true;
        }

        PlayerData playerData = plugin.getPlayerData();
        FileConfiguration config = playerData.getPlayerConfig(player);
        config.set("bracketcolor", color);
        playerData.savePlayerConfig(player, config);

        // Format the success message with the actual color string
        String successMessage = String.format(plugin.getMessage("success_bracket_color_changed"), chatColor + color);
        player.sendMessage(ChatColor.GREEN + successMessage);
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