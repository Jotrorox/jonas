package com.jotrorox.jonas;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String token = System.getenv("DISCORD_TOKEN");

        if (token == null) {
            System.err.println("DISCORD_TOKEN environment variable not set");
            System.exit(1);
        }

        JDABuilder builder = JDABuilder.createDefault(token);

        JDA bot = builder.build();

        bot.awaitReady();
    }
}