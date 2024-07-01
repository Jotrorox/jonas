package com.jotrorox.jonas.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class PlayCommand extends Command {

    public PlayCommand() {
        super("play", "Plays some music in your current vc");

        this.getData().setGuildOnly(true);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member user = event.getInteraction().getMember();

        if (!event.isFromGuild() || user == null) {
            event
                .deferReply()
                .setEphemeral(true)
                .addEmbeds(sendError("Something went wrong there. Please report this to the Developer!").build())
                .queue();
            return;
        }

        if (!user.getVoiceState().inAudioChannel()) {
            event
                .deferReply()
                .setEphemeral(true)
                .addEmbeds(sendError("To use this you have to go into a vc!").build())
                .queue();
            return;
        }

        Guild guild = event.getGuild();
        VoiceChannel channel = guild.getVoiceChannelsByName(user.getVoiceState().getChannel().getName(), true).get(0);
        AudioManager manager = guild.getAudioManager();


        manager.openAudioConnection(channel);

        event
            .deferReply()
            .setEphemeral(true)
            .addEmbeds(sendError("This hasn't been implemented yet!").build())
            .queue();
        return;
    }

    private EmbedBuilder sendError(String errMsg) {
        EmbedBuilder error_embed = new EmbedBuilder();

        error_embed.setTitle("Play - Error");
        error_embed.setFooter(
            "Jonas - by jotrorox",
            "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
        );

        error_embed.setDescription(errMsg);
        error_embed.setThumbnail("https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/cat_sad.jpg");

        return error_embed;
    }
}