package com.jotrorox.jonas.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class RPSCommand extends Command {
    
    public RPSCommand() {
        super("rps", "Play Rock Paper Scissors against the computer");
    }
    
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().addContent("Not implemented yet").queue();
    }
    
}