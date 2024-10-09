package com.jotrorox.jonas.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * A class providing utility methods for the configuration of the bot.
 * 
 * Utility methods include a range of tools for creating and reading configurations.
 * 
 * @see Config
 */
public class ConfigUtil {

    /**
     * The default path to the configuration file.
     */
    public static final Path DEFAULT_CONFIG_PATH = Path.of("config.properties");

    /**
     * Get the configuration of the bot from the environment.
     * 
     * The configuration is read from the environment variables DISCORD_TOKEN and DISCORD_STATUS.
     * 
     * @return The configuration of the bot.
     * @throws IllegalArgumentException If the DISCORD_TOKEN or DISCORD_STATUS environment variables are not set.
     */
    public static Config getConfigFromEnv() {
        String discordToken = System.getenv("DISCORD_TOKEN");
        String discordStatus = System.getenv("DISCORD_STATUS");

        if (discordToken == null) {
            throw new IllegalArgumentException("No token provided! Please provide a token in the DISCORD_TOKEN environment variable.");
        }

        if (discordStatus == null) {
            throw new IllegalArgumentException("No status provided! Please provide a status in the DISCORD_STATUS environment variable.");
        }

        return new Config(discordToken, discordStatus);
    }

    /**
     * Get the configuration of the bot from the command line arguments.
     * 
     * The configuration is read from the command line arguments.
     * 
     * @param args The command line arguments.
     * @return The configuration of the bot.
     * @throws IllegalArgumentException If the command line arguments are not set.
     */
    public static Config getConfigFromArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not enough arguments provided! Please provide a token and a status.");
        }

        return new Config(args[0], args[1]);
    }

    /**
     * Copy the default configuration file to the specified path.
     * 
     * The default configuration file is copied to the specified path if it does not exist.
     * 
     * @param path The path to copy the default configuration file to.
     * @throws RuntimeException If the default configuration file could not be copied.
     */
    private static void copyDefaultConfig(Path path) {
        if (Files.exists(path)) return;

        try {
            Files.copy(ConfigUtil.class.getResourceAsStream("/config.properties"), path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to copy default configuration file!", e);
        }
    }

    /**
     * Get the configuration of the bot from the specified file.
     * 
     * The configuration is read from the specified file.
     * 
     * @param path The path to the configuration file.
     * @return The configuration of the bot.
     * @throws IllegalArgumentException If the configuration file could not be read.
     */
    public static Config getConfigFromFile(Path path) {
        copyDefaultConfig(path);

        try {
            Properties properties = new Properties();

            properties.load(Files.newInputStream(path));

            String discordToken = properties.getProperty("discord.token");
            String discordStatus = properties.getProperty("discord.status");

            if (discordToken == null) {
                throw new IllegalArgumentException("No token provided in configuration file! Please provide a token in the DISCORD_TOKEN property.");
            }

            if (discordStatus == null) {
                throw new IllegalArgumentException("No status provided in configuration file! Please provide a status in the DISCORD_STATUS property.");
            }

            return new Config(discordToken, discordStatus);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read configuration file!", e);
        }
    }
}
