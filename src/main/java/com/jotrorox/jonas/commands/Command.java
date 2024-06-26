package com.jotrorox.jonas.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

abstract class Command {

    private static String name;
    private static String description;

    public void execute(SlashCommandInteractionEvent event) {
        return;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
