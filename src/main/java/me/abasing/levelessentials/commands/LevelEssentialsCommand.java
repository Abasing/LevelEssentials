package me.abasing.levelessentials.commands;

import me.abasing.levelessentials.LevelEssentials;
import me.abasing.levelessentials.utils.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LevelEssentialsCommand implements CommandExecutor {
    private final LevelEssentials plugin;

    public LevelEssentialsCommand(LevelEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sendHelpMessage(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("levelessentials.reload")) {
                sender.sendMessage(ChatColor.RED + plugin.getMessage("no_permission"));
                return true;
            }

            if (args.length == 2 && args[1].equalsIgnoreCase("-a")) {
                reloadAll(sender);
            } else {
                reloadConfig(sender);
            }
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + plugin.getMessage("player_only"));
            return true;
        }

        Player player = (Player) sender;
        PlayerData playerData = plugin.getPlayerData();
        FileConfiguration config = playerData.getPlayerConfig(player);

        if (args.length < 2) {
            sendHelpMessage(player);
            return true;
        }

        String subCommand = args[0].toLowerCase();
        String type = args[1].toLowerCase();

        switch (subCommand) {
            case "bold":
                handleToggleCommand(player, config, type, "bold", "levelessentials.level.bold", "levelessentials.brackets.bold", "&l");
                break;
            case "italic":
                handleToggleCommand(player, config, type, "italic", "levelessentials.level.italic", "levelessentials.brackets.italic", "&o");
                break;
            case "underline":
                handleToggleCommand(player, config, type, "underline", "levelessentials.level.underline", null, "&n");
                break;
            default:
                sendHelpMessage(player);
                break;
        }

        playerData.savePlayerConfig(player, config);
        return true;
    }

    private void reloadConfig(CommandSender sender) {
        plugin.reloadConfig();
        plugin.reloadMessages();
        sender.sendMessage(ChatColor.GREEN + plugin.getMessage("reload_success"));
    }

    private void reloadAll(CommandSender sender) {
        plugin.reloadConfig();
        plugin.reloadMessages();
        plugin.reloadPlayerData();
        sender.sendMessage(ChatColor.GREEN + plugin.getMessage("reload_success_all"));
    }

    private void handleToggleCommand(Player player, FileConfiguration config, String type, String key, String levelPermission, String bracketsPermission, String code) {
        boolean hasPermission = type.equals("level") ? player.hasPermission(levelPermission) : player.hasPermission(bracketsPermission);
        if (!hasPermission) {
            player.sendMessage(ChatColor.RED + plugin.getMessage("no_permission"));
            return;
        }

        String configKey = "format." + key + "." + type;
        if (config.getString(configKey, "").isEmpty()) {
            config.set(configKey, code);
            player.sendMessage(ChatColor.GREEN + plugin.getMessage("format_enabled").replace("{type}", type).replace("{format}", key));
        } else {
            config.set(configKey, null);
            player.sendMessage(ChatColor.GREEN + plugin.getMessage("format_disabled").replace("{type}", type).replace("{format}", key));
        }
    }

    private void sendHelpMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.BLUE + "[LE] " + ChatColor.GRAY + "Available commands:");
        sender.sendMessage(ChatColor.GRAY + " /le bold <level|brackets>");
        sender.sendMessage(ChatColor.GRAY + " /le italic <level|brackets>");
        sender.sendMessage(ChatColor.GRAY + " /le underline <level>");
        sender.sendMessage(ChatColor.GRAY + " /levelessentials reload [-a]");
    }
}
