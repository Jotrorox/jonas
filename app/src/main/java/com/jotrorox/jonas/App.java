package com.jotrorox.jonas;

import java.util.Collections;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class App {
    public static void main(String[] args) {
        String token = System.getenv("DISCORD_TOKEN");

        JDABuilder builder = JDABuilder.createLight(token, Collections.emptyList());

        JDA jda = builder.build();
    }
}
