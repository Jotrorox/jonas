package com.jotrorox;

import ch.qos.logback.classic.Logger;
import com.jotrorox.commands.PingCommand;
import com.jotrorox.db.DatabaseManager;
import com.jotrorox.listeners.ReadyListener;
import com.jotrorox.listeners.SlashCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import org.slf4j.LoggerFactory;

public class Jonas {

    private static final Logger mainLogger = (Logger) LoggerFactory.getLogger(Jonas.class);
    private static final DatabaseManager databaseManager = new DatabaseManager("jdbc:sqlite:jonas.db");

    public static void main(String[] args) throws InterruptedException {
        String token = System.getenv("DISCORD_TOKEN");

        if (token == null) {
            mainLogger.error("No token provided! Please provide a token in the DISCORD_TOKEN environment variable.");
            System.exit(1);
        }

        JDABuilder builder = JDABuilder.createDefault(token);

        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new SlashCommandListener());

        JDA bot = builder.build();

        bot.awaitReady();

        bot.updateCommands().addCommands(
            (new PingCommand()).getSlashCommandData()
        ).queue();
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}