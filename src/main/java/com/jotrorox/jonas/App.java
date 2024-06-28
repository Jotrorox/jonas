package com.jotrorox.jonas;

import com.jotrorox.jonas.commands.DogCommand;
import com.jotrorox.jonas.commands.DuckCommand;
import com.jotrorox.jonas.commands.PingCommand;
import com.jotrorox.jonas.commands.RPSCommand;
import com.jotrorox.jonas.listeners.SlashCommandListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

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

        CommandListUpdateAction commands = bot.updateCommands();

        commands.addCommands(
            new PingCommand().getData(),
            new RPSCommand().getData(),
            new DuckCommand().getData(),
            new DogCommand().getData()
        );

        commands.queue();
    }
}
