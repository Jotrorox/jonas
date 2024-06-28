package com.jotrorox.jonas.commands;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jotrorox.jonas.util.jsonInterfaces.DuckResponse;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class DuckCommand extends Command {

    private static final String API_URL = "https://random-d.uk/api/v2/quack";

    public DuckCommand() {
        super("duck", "Provides an image of a duck!");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder ping_embed = new EmbedBuilder();

        ping_embed.setTitle("Duck - Quack!");
        ping_embed.setFooter(
            "Jonas - by jotrorox",
            "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
        );

        try {
            DuckResponse response = fetchDuck();

            ping_embed.setImage(response.getUrl());
        } catch (Exception e) {
            e.printStackTrace();

            ping_embed.setColor(Color.RED);
            ping_embed.setDescription(
                "An error occurred while fetching the duck image!"
            );
        }

        event
            .deferReply()
            .setEphemeral(true)
            .addEmbeds(ping_embed.build())
            .queue();
    }

    public DuckResponse fetchDuck() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(con.getInputStream())
            )
        ) {
            Gson gson = new Gson();
            DuckResponse response = gson.fromJson(reader, DuckResponse.class);
            return response;
        } finally {
            con.disconnect();
        }
    }
}
