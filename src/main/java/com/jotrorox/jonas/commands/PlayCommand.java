package com.jotrorox.jonas.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PlayCommand extends Command {

    public PlayCommand() {
        super("play", "Plays some music in your current vc");
        
        this.getData().setGuildOnly(true);
    }
    
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (!event.isFromGuild()) {
            EmbedBuilder error_embed = new EmbedBuilder();

            error_embed.setTitle("Play - Error");
            error_embed.setFooter(
                "Jonas - by jotrorox",
                "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
            );
            
            error_embed.setDescription("Something went wrong there. Please report this to the Developer!");
            
            event
                .deferReply()
                .setEphemeral(true)
                .addEmbeds(error_embed.build())
                .queue();
        }
        }
    }
}