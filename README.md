# 🔗 AgnAccountLinking - Advanced Minecraft & Discord Integration

A professional, high-performance account linking plugin designed for Spigot/Paper/Folia servers. This plugin bridges the gap between your Minecraft server and Discord community with 2FA security, role synchronization, and an advanced booster reward system.

## 🌟 Key Features
*   **Folia Support**: Fully optimized for multi-threaded region-based servers.
*   **Discord 2FA**: Protect player accounts by requiring Discord confirmation when logging in from a new IP.
*   **Role Sync**: Automatically synchronize LuckPerms groups with Discord roles.
*   **Advanced Booster System**: Reward your server supporters through a dedicated Discord panel.
*   **Multi-Language Support**: Complete translations for English, Turkish, German, French, Spanish, and Chinese.

---

## 🚀 Installation & Setup

### 1. Requirements
*   **LuckPerms**: Required for role synchronization.
*   **PlaceholderAPI**: Optional, but recommended for dynamic messages.
*   **Java 17 or higher**: Modern Java environment.

### 2. Creating a Discord Bot
1.  Go to the [Discord Developer Portal](https://discord.com/developers/applications).
2.  Create a **New Application** and give it a name.
3.  Navigate to the **Bot** tab on the left.
4.  Enable the following **Privileged Gateway Intents**:
    *   `Presence Intent`
    *   `Server Members Intent`
    *   `Message Content Intent`
5.  Copy your **Bot Token**.
6.  Go to the **OAuth2** -> **URL Generator** tab:
    *   Scopes: `bot`, `applications.commands`
    *   Permissions: `Administrator` (or specific permissions for roles/messages)
    *   Copy the URL and invite the bot to your server.

### 3. Plugin Configuration
1.  Place the `AgnHesapEsle.jar` into your `plugins` folder.
2.  Restart your server to generate the configuration files.
3.  Open `plugins/AgnHesapEsle/config.yml`.
4.  Replace `token: "DISCORD_BOT_TOKEN"` with your actual Bot Token.
5.  Fill in your `guild-id`, `log-channel-id`, and `information-channel-id`.
6.  Restart the server or use `/link reload`.

---

## 🎮 Commands

### 🧊 Minecraft Commands
*   `/link link` - Generate a unique linking code.
*   `/link confirm` - Confirm the pending linked account.
*   `/link 2fa <on/off>` - Enable or disable Discord security.
*   `/link reload` - (Admin) Reload configuration and messages.
*   `/link list` - (Admin) List all linked accounts.

### 💬 Discord Commands
*   `/link <code>` - Enter the code generated in Minecraft.
*   `/info <user>` - Check the linked Minecraft account of a user.
*   `/report <player> <reason>` - Report a player to the staff.

---

## 🛠️ Developer Support
If you encounter any issues or have questions, please reach out via our official support channels.

**Agnes Project © 2026**

---

## 📝 Update Notes (v1.2.4)

### 🛠 Improvements & Fixes
*   **Folia Stability**: Rewrote the scheduler system using reflection to ensure 100% compatibility with Folia, Paper, and Spigot.
*   **Booster System 2.0**: 
    *   Completely refactored booster logic for better performance.
    *   Added global in-game announcements when a booster claims rewards.
    *   Improved cooldown tracking and localizable status messages.
*   **Configuration Revamp**:
    *   `config.yml` now features professional English comments and a cleaner structure.
    *   Fixed a type mismatch warning for boolean configuration values.
*   **Placeholder Fixes**: Corrected variable formats in language files (from `{var}` to `%var%`) for consistent parsing.
*   **Soft Dependencies**: LuckPerms is now a `softdepend`. The plugin will load and function (except for role sync) even if LuckPerms is not installed.
*   **Auto-Documentation**: The plugin now automatically generates the `README.txt` file inside the plugin folder for quick setup reference.
*   **Bug Fixes**: 
    *   Fixed `UnsupportedOperationException` on Folia/Canvas servers caused by legacy scheduler calls.
    *   Fixed a crash issue where Folia timers were initialized with a 0-tick delay.
    *   Added missing localization keys for the booster panel and information messages.
