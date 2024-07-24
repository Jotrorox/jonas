package com.jotrorox.jonas;

import ch.qos.logback.classic.Logger;
import com.jotrorox.jonas.listeners.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger mainLogger = (Logger) LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        String token = System.getenv("DISCORD_TOKEN");

        if (token == null) {
            mainLogger.error("No token provided! Please provide a token in the DISCORD_TOKEN environment variable.");
            System.exit(1);
        }

        JDABuilder builder = JDABuilder.createDefault(token);

        builder.addEventListeners(new ReadyListener());

        JDA bot = builder.build();

        bot.awaitReady();
    }
}