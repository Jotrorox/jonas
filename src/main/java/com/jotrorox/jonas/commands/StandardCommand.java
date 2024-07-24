package com.jotrorox.jonas.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class StandardCommand {
    private final String name;
    private final String description;

    public StandardCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void execute(SlashCommandInteractionEvent event) {
        CommandLogger.getLogger().warn("Command {} does not have an implemented execute method!", name);
        return;
    }

    public SlashCommandData getSlashCommandData() {
        return Commands.slash(name, description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
