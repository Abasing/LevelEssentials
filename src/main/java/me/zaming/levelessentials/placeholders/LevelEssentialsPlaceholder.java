package me.zaming.levelessentials.placeholders;

import me.zaming.levelessentials.LevelEssentials;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LevelEssentialsPlaceholder extends PlaceholderExpansion {
    private LevelEssentials plugin;

    public LevelEssentialsPlaceholder(LevelEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean canRegister() {
        return (this.plugin = (LevelEssentials) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "levelessentials";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Abasing";
    }

    @Override
    public @NotNull String getRequiredPlugin() {
        return "LevelEssentials";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String identifier) {
        if (player == null || !player.isOnline()) {
            return "";
        }

        switch (identifier) {
            case "brackets_bracket_left":
                return plugin.getConfig().getString(player.getUniqueId() + ".bracket_left", "<");
            case "brackets_bracket_right":
                return plugin.getConfig().getString(player.getUniqueId() + ".bracket_right", ">");
            case "bracketcolor_color":
                return plugin.getConfig().getString(player.getUniqueId() + ".bracketcolor", "&f");
            case "levelcolor_color":
                return plugin.getConfig().getString(player.getUniqueId() + ".levelcolor", "&f");
            default:
                return null;
        }
    }
}