package com.jotrorox.jonas.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SettingsCommand extends Command {

    public SettingsCommand() {
        super("settings", "Allows you to tweak the bot to your liking");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder settings_embed = new EmbedBuilder();

        settings_embed.setTitle("Settings");
        settings_embed.setFooter(
            "Jonas - by jotrorox",
            "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
        );

        event
            .deferReply()
            .setEphemeral(true)
            .addEmbeds(settings_embed.build())
            .addActionRow()
            .queue();
    }
}
