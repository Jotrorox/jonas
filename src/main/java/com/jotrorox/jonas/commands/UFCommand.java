package com.jotrorox.jonas.commands;

import com.google.gson.Gson;
import com.jotrorox.jonas.util.jsonInterfaces.UFResponse;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class UFCommand extends Command {

    private static final String API_URL =
        "https://uselessfacts.jsph.pl/api/v2/facts/random";

    public UFCommand() {
        super("uselessfact", "Get a useless fact!");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder uf_embed = new EmbedBuilder();

        uf_embed.setTitle("Useless Fact!");
        uf_embed.setFooter(
            "Jonas - by jotrorox",
            "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
        );

        try {
            UFResponse response = fetchFact();

            uf_embed.setColor(Color.GREEN);
            uf_embed.setDescription(response.getText());
        } catch (Exception e) {
            e.printStackTrace();

            uf_embed.setColor(Color.RED);
            uf_embed.setDescription(
                "An error occurred while fetching the joke!"
            );
        }

        event
            .deferReply()
            .setEphemeral(true)
            .addEmbeds(uf_embed.build())
            .queue();
    }

    private UFResponse fetchFact() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(con.getInputStream())
            )
        ) {
            Gson gson = new Gson();
            return gson.fromJson(reader, UFResponse.class);
        } finally {
            con.disconnect();
        }
    }
}
