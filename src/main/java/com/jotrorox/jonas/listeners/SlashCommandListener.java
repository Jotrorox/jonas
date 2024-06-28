package com.jotrorox.jonas.listeners;

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
    }
}
