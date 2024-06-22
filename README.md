## LevelEssentials

LevelEssentials is a Minecraft plugin designed to enhance your server with customizable level display brackets and color options.

## How to Use?

Let's take an example of CyberLevel, a leveling plugin. It has a placeholder that shows the player's current level: `%clv_player_level%`. The output of this placeholder is the player's current level, like 0.

LevelEssentials improves this by adding color. You can use the placeholder `%levelessentials_levelcolor%` before the level number to change its color to what the player has selected. It also adds left and right brackets with customizable colors, making the leveling system look better.

You can use LevelEssentials with any leveling plugin. If used correctly, it will show levels in chat like this:

![Example](https://i.imgur.com/6Tv8Ohv.png)

**Image Information:**
- BracketType: Square
- BracketColor: Yellow
- LevelColor: Aqua

## Features
- **Custom Bracket Colors:** Players can change the color of their brackets with a simple command.
- **Custom Level Colors:** Players can personalize the color of their levels to match their style.
- **Bracket Types:** Choose from various bracket types (angle, square, brace) for a unique display.
- **PlaceholderAPI Integration:** Supports PlaceholderAPI for easy placeholder usage in other plugins.
- **Easy Configuration:** Simple and intuitive configuration file for quick setup.
- **Permissions:** Granular permission system to control who can change colors and brackets.
- **Reload Commands:** Quickly reload configuration files and player data without restarting the server.

## Commands
```
/bracketcolor <color> - Change the color of your brackets.
/levelcolor <color> - Change the color of your level.
/brackets <type> - Change the type of your brackets.
/levelessentials reload [-a] - Reload the plugin configuration. Use -a to reload all files including player data.
```

## Permissions
```
levelessentials.reload - Allows reloading the plugin configuration.
levelessentials.levelcolor.<color> - Allows changing the level color to the specified color.
levelessentials.bracketcolor.<color> - Allows changing the bracket color to the specified color.
levelessentials.bracket.<type> - Allows changing the bracket type to the specified type.
```

<details>
  <summary>All Permissions</summary>

  ### Brackets
  ```
  levelessentials.bracket.square
  levelessentials.bracket.angle
  levelessentials.bracket.brace
  ```

  ### Bracket Colors
  ```
  levelessentials.bracketcolor.black
  levelessentials.bracketcolor.darkblue
  levelessentials.bracketcolor.darkgreen
  levelessentials.bracketcolor.darkaqua
  levelessentials.bracketcolor.darkred
  levelessentials.bracketcolor.darkpurple
  levelessentials.bracketcolor.gold
  levelessentials.bracketcolor.gray
  levelessentials.bracketcolor.darkgray
  levelessentials.bracketcolor.blue
  levelessentials.bracketcolor.green
  levelessentials.bracketcolor.aqua
  levelessentials.bracketcolor.red
  levelessentials.bracketcolor.lightpurple
  levelessentials.bracketcolor.yellow
  levelessentials.bracketcolor.white
  ```

  ### Level Colors
  ```
  levelessentials.levelcolor.black
  levelessentials.levelcolor.darkblue
  levelessentials.levelcolor.darkgreen
  levelessentials.levelcolor.darkaqua
  levelessentials.levelcolor.darkred
  levelessentials.levelcolor.darkpurple
  levelessentials.levelcolor.gold
  levelessentials.levelcolor.gray
  levelessentials.levelcolor.darkgray
  levelessentials.levelcolor.blue
  levelessentials.levelcolor.green
  levelessentials.levelcolor.aqua
  levelessentials.levelcolor.red
  levelessentials.levelcolor.lightpurple
  levelessentials.levelcolor.yellow
  levelessentials.levelcolor.white
  ```
</details>

<details>
  <summary>Placeholders</summary>

  ```
  Left Bracket: %levelessentials_leftbracket%
  Right Bracket: %levelessentials_rightbracket%
  Bracket Color: %levelessentials_bracketcolor%
  Level Color: %levelessentials_levelcolor%
  ```
</details>

<details>
  <summary>Installation</summary>

  1. Download the LevelEssentials plugin.
  2. Place the `LevelEssentials.jar` file into your server's `plugins` directory.
  3. Restart your server to generate the default configuration files.
  4. Configure the plugin as needed in `config.yml` and `messages.properties`.
  5. Enjoy the enhanced customization options for your players!
</details>

<details>
  <summary>Configuration</summary>

  `config.yml` - Located in the plugin's folder, this file allows you to customize various settings.

  `messages.properties` - Located in the plugin's folder, this file contains all the messages used by the plugin, allowing you to customize the language and format.
</details>

## Support

For any issues, suggestions, or help, please visit our [Github page](https://github.com/yourgithub) or join our [Discord server](https://discord.gg/yourdiscord).

[**Buy me a coffee!**](https://buymeacoffe.com/would)
