package com.jotrorox.jonas.config;

/**
 * A class to store the configuration of the bot.
 */
public class Config {

    /**
     * The Discord token of the bot.
     */
    private static String DISCORD_TOKEN;
    
    /**
     * The status of the bot.
     * 
     * The status gets displayed in the bot's profile on Discord.
     */
    private static String DISCORD_STATUS;

    /**
     * Create a new configuration.
     * 
     * @param discordToken The Discord token of the bot.
     * @param discordStatus The status of the bot.
     */
    public Config(String discordToken, String discordStatus) {
        Config.DISCORD_TOKEN = discordToken;
        Config.DISCORD_STATUS = discordStatus;
    }

    /**
     * Get the Discord token of the bot.
     * 
     * @return The Discord token of the bot.
     */
    public String getDiscordToken() {
        return DISCORD_TOKEN;
    }

    /**
     * Get the status of the bot.
     * 
     * @return The status of the bot.
     */
    public String getDiscordStatus() {
        return DISCORD_STATUS;
    }
}
