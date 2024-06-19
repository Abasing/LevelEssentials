# LevelEssentials

LevelEssentials is a Minecraft plugin designed to enhance your server with customizable level display brackets and color options. 

## Features
- Customizable level brackets (square, angle, brace).
- Customizable bracket and level colors.
- Permissions for each bracket type and color.
- Integration with PlaceholderAPI for custom placeholders.
- Easy-to-configure settings via config.yml.

## Permissions
### Brackets
- `levelessentials.bracket.square`
- `levelessentials.bracket.angle`
- `levelessentials.bracket.brace`

### Bracket Colors
- `levelessentials.bracketcolor.black`
- `levelessentials.bracketcolor.darkblue`
- `levelessentials.bracketcolor.darkgreen`
- `levelessentials.bracketcolor.darkaqua`
- `levelessentials.bracketcolor.darkred`
- `levelessentials.bracketcolor.darkpurple`
- `levelessentials.bracketcolor.gold`
- `levelessentials.bracketcolor.gray`
- `levelessentials.bracketcolor.darkgray`
- `levelessentials.bracketcolor.blue`
- `levelessentials.bracketcolor.green`
- `levelessentials.bracketcolor.aqua`
- `levelessentials.bracketcolor.red`
- `levelessentials.bracketcolor.lightpurple`
- `levelessentials.bracketcolor.yellow`
- `levelessentials.bracketcolor.white`

### Level Colors
- `levelessentials.levelcolor.black`
- `levelessentials.levelcolor.darkblue`
- `levelessentials.levelcolor.darkgreen`
- `levelessentials.levelcolor.darkaqua`
- `levelessentials.levelcolor.darkred`
- `levelessentials.levelcolor.darkpurple`
- `levelessentials.levelcolor.gold`
- `levelessentials.levelcolor.gray`
- `levelessentials.levelcolor.darkgray`
- `levelessentials.levelcolor.blue`
- `levelessentials.levelcolor.green`
- `levelessentials.levelcolor.aqua`
- `levelessentials.levelcolor.red`
- `levelessentials.levelcolor.lightpurple`
- `levelessentials.levelcolor.yellow`
- `levelessentials.levelcolor.white`

## Commands
- `/levelessentials reload` - Reload the plugin configuration.
  - Permission: `levelessentials.reload`
- `/bracket <square|angle|brace>` - Set your level bracket.
  - Permission: `levelessentials.bracket.<type>`
- `/bracketcolor <color>` - Set your bracket color.
  - Permission: `levelessentials.bracketcolor.<color>`
- `/levelcolor <color>` - Set your level color.
  - Permission: `levelessentials.levelcolor.<color>`

## Installation and Configuration
### Download and Install
1. Download the plugin jar file.
2. Place it in your server's `plugins` folder.
3. Restart your server.

### Configuration
1. Open the generated `config.yml` file in the `plugins/LevelEssentials` folder.
2. Customize the settings as needed.
3. Use `/levelessentials reload` to apply changes without restarting the server.

### Using PlaceholderAPI
1. Download PlaceholderAPI if not already installed.
2. Download the LevelEssentials expansion using `/papi ecloud download levelessentials`.
3. Reload PlaceholderAPI with `/papi reload`.

### Placeholders
- `%levelessentials_brackets_bracket_left%` - Left bracket.
- `%levelessentials_brackets_bracket_right%` - Right bracket.
- `%levelessentials_bracketcolor_color%` - Bracket color.
- `%levelessentials_levelcolor_color%` - Level color.

## Support
For any issues or feature requests, please visit the [GitHub repository](https://github.com/yourusername/LevelEssentials) or the plugin's discussion page on SpigotMC.
