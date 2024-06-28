package com.jotrorox.jonas.listeners;

import com.jotrorox.jonas.commands.CNJokeCommand;
import com.jotrorox.jonas.commands.CatCommand;
import com.jotrorox.jonas.commands.DogCommand;
import com.jotrorox.jonas.commands.DuckCommand;
import com.jotrorox.jonas.commands.PingCommand;
import com.jotrorox.jonas.commands.RPSCommand;
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
    }
}
