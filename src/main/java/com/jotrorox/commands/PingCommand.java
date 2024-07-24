package com.jotrorox.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;

public class PingCommand extends StandardCommand {
    public PingCommand() {
        super("ping", "Pings the bot to check if it is online and provides the latency.");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue();

        int latency = (int) event.getJDA().getGatewayPing();

        EmbedBuilder pingEmbed = new EmbedBuilder();

        pingEmbed.setTitle("Pong!");
        pingEmbed.setDescription("The bot is online and the latency is " + latency + "ms.");

        Color color = Color.CYAN;
        if (latency > 125) color = Color.GREEN;
        if (latency > 250) color = Color.YELLOW;
        if (latency > 500) color = Color.RED;
        pingEmbed.setColor(color);

        pingEmbed.setFooter("Jonas - By Jotrorox", event.getJDA().getSelfUser().getAvatarUrl());

        event.getHook().editOriginalEmbeds(pingEmbed.build()).queue();
    }
}
