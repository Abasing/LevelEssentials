package me.abasing.levelessentials.expansion.papi;

import me.abasing.levelessentials.LevelEssentials;
import me.abasing.levelessentials.utils.PlayerData;
import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LevelEssentialsExpansion extends PlaceholderExpansion {
    private final LevelEssentials plugin;

    public LevelEssentialsExpansion(LevelEssentials plugin) {
        this.plugin = plugin;
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
    public @NotNull String getVersion() {
        return "1.0.2";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player == null || !player.isOnline()) {
            return "";
        }

        PlayerData playerData = plugin.getPlayerData();
        FileConfiguration config = playerData.getPlayerConfig(player.getPlayer());

        String bracketColor = config.getString("bracketcolor", "§f");
        String levelColor = config.getString("levelcolor", "§f");
        String leftBracket = config.getString("brackets.left", "<");
        String rightBracket = config.getString("brackets.right", ">");
        String levelBold = config.getBoolean("level.bold", false) ? "&l" : "";
        String bracketsBold = config.getBoolean("brackets.bold", false) ? "&l" : "";
        String levelItalic = config.getBoolean("level.italic", false) ? "&o" : "";
        String bracketsItalic = config.getBoolean("brackets.italic", false) ? "&o" : "";
        String levelUnderline = config.getBoolean("level.underline", false) ? "&n" : "";
        String bracketsUnderline = config.getBoolean("brackets.underline", false) ? "&n" : "";

        switch (params.toLowerCase()) {
            case "bracketcolor":
                return bracketColor;
            case "levelcolor":
                return levelColor;
            case "leftbracket":
                return leftBracket;
            case "rightbracket":
                return rightBracket;
            case "level_bold":
                return levelBold;
            case "brackets_bold":
                return bracketsBold;
            case "level_italic":
                return levelItalic;
            case "brackets_italic":
                return bracketsItalic;
            case "level_underline":
                return levelUnderline;
            case "brackets_underline":
                return bracketsUnderline;
            default:
                return null;
        }
    }
}
