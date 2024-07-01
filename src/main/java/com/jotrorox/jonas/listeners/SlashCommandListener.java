package com.jotrorox.jonas.listeners;

import com.jotrorox.jonas.commands.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) (new PingCommand()).execute(event);
        if (event.getName().equals("rps")) (new RPSCommand()).execute(event);
        if (event.getName().equals("duck")) (new DuckCommand()).execute(event);
        if (event.getName().equals("dog")) (new DogCommand()).execute(event);
        if (event.getName().equals("cat")) (new CatCommand()).execute(event);
        if (event.getName().equals("chuckjoke")) (new CNJokeCommand()).execute(
                event
            );
        if (event.getName().equals("uselessfact")) (new UFCommand()).execute(
                event
            );
        if (event.getName().equals("settings")) (new SettingsCommand()).execute(
                event
            );
        if (event.getName().equals("play")) (new PlayCommand()).execute(event);
    }
}
