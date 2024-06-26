package com.jotrorox.jonas.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;

public class PingCommand extends Command {
    public PingCommand() {
        super("ping", "Provides the bot's latency");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        long ping = event.getJDA().getGatewayPing();

        EmbedBuilder ping_embed = new EmbedBuilder();

        ping_embed.setTitle("Ping - Pong");
        ping_embed.setFooter("Jonas - by jotrorox", "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png");

        ping_embed.setDescription("The current gateway ping of this Bot is: " + ping + "ms!");

        Color ping_embed_color;
        if (ping > 500) ping_embed_color = Color.RED;
        else if (ping > 250) ping_embed_color = Color.YELLOW;
        else if (ping > 125) ping_embed_color = Color.GREEN;
        else ping_embed_color = Color.BLUE;
        ping_embed.setColor(ping_embed_color);

        event.deferReply().setEphemeral(true).addEmbeds(ping_embed.build()).queue();
    }
}
