package com.jotrorox.jonas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import com.jotrorox.jonas.listeners.SlashCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class App {

    public static void main(String[] args) throws IOException {
        String token = new String(Files.readAllBytes(Paths.get("token.env")));

        JDABuilder builder = JDABuilder.createLight(
            token,
            Collections.emptyList()
        );

        builder.addEventListeners(new SlashCommandListener());
        builder.setActivity(Activity.customStatus("Just vibin"));

        JDA bot = builder.build();
    }
}
