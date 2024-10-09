package com.jotrorox.jonas;

import org.slf4j.LoggerFactory;

import com.jotrorox.jonas.listeners.ReadyListener;

import ch.qos.logback.classic.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class App {

    private static final Logger mainLogger = (Logger) LoggerFactory.getLogger(App.class);

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
