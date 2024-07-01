package com.jotrorox.jonas;

import com.jotrorox.jonas.commands.*;
import com.jotrorox.jonas.listeners.ButtonInteractionListener;
import com.jotrorox.jonas.listeners.SlashCommandListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumSet;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class App {

    public static void main(String[] args) throws IOException {
        String token = new String(Files.readAllBytes(Paths.get("token.env")));

        JDABuilder builder = JDABuilder.createLight(
            token
        );

        EnumSet<GatewayIntent> intents = EnumSet.of(
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.GUILD_VOICE_STATES
        );

        builder.enableIntents(intents);

        builder.addEventListeners(new SlashCommandListener());
        builder.addEventListeners(new ButtonInteractionListener());
        
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.customStatus("Just vibin"));

        builder.enableCache(CacheFlag.VOICE_STATE);

        JDA bot = builder.build();

        CommandListUpdateAction commands = bot.updateCommands();

        commands.addCommands(
            new PingCommand().getData(),
            new RPSCommand().getData(),
            new DuckCommand().getData(),
            new DogCommand().getData(),
            new CatCommand().getData(),
            new CNJokeCommand().getData(),
            new UFCommand().getData(),
            new SettingsCommand().getData(),
            new PlayCommand().getData()
        );

        commands.queue();
    }
}
