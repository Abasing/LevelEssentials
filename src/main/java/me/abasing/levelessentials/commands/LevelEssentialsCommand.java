package me.abasing.levelessentials.commands;

import me.abasing.levelessentials.LevelEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class LevelEssentialsCommand implements CommandExecutor {
    private final LevelEssentials plugin;

    public LevelEssentialsCommand(LevelEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("&b&l[L&3&lE] &7Unknown command. Available commands are:");
            sender.sendMessage(" &8▪ &3/levelcolor <color>");
            sender.sendMessage(" &8▪ &3/bracketcolor <color");
            sender.sendMessage(" &8▪ &3/brackets <bracket>");
            sender.sendMessage(" &8▪ &3/levelessentials reload [-a]");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("levelessentials.reload")) {
                sender.sendMessage(ChatColor.RED + plugin.getMessage("no_permission"));
                return true;
            }

            if (args.length == 2 && args[1].equalsIgnoreCase("-a")) {
                plugin.reloadConfig();
                plugin.reloadMessages();
                plugin.reloadPlayerData();
                sender.sendMessage(ChatColor.GREEN + plugin.getMessage("reload_success_all"));
            } else {
                plugin.reloadConfig();
                plugin.reloadMessages();
                sender.sendMessage(ChatColor.GREEN + plugin.getMessage("reload_success"));
            }
            return true;
        }
        sender.sendMessage("§b§l[L§3§lE] §7Unknown command. Available commands are:");
        sender.sendMessage(" §8▪ §3/levelcolor <color>");
        sender.sendMessage(" §8▪ §3/bracketcolor <color");
        sender.sendMessage(" §8▪ §3/brackets <bracket>");
        sender.sendMessage(" §8▪ §3/levelessentials reload [-a]");
        return true;
    }
}
