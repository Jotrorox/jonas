package com.jotrorox.jonas.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

abstract class Command {
    private static String name;
    private static SlashCommandData data;

    public Command(String name, String description) {
        Command.name = name;
        data = Commands.slash(name, description);
    }

    public void execute(SlashCommandInteractionEvent event) {
        return;
    }

    public SlashCommandData getData() {
        return data;
    }

    public String getName() {
        return name;
    }
}
