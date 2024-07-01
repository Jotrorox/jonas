package com.jotrorox.jonas.commands;

import com.google.gson.Gson;
import com.jotrorox.jonas.util.jsonInterfaces.CNResponse;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CNJokeCommand extends Command {

    private static final String API_URL =
        "https://api.chucknorris.io/jokes/random";

    public CNJokeCommand() {
        super("chuckjoke", "Get a random Chuck Norris joke");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder cnjoke_embed = new EmbedBuilder();

        cnjoke_embed.setTitle("Chuck Norris Joke");
        cnjoke_embed.setFooter(
            "Jonas - by jotrorox",
            "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
        );

        try {
            CNResponse response = fetchJoke();

            cnjoke_embed.setColor(Color.GREEN);
            cnjoke_embed.setDescription(response.getValue());
            cnjoke_embed.setThumbnail(response.getIcon_url());
        } catch (Exception e) {
            e.printStackTrace();

            cnjoke_embed.setColor(Color.RED);
            cnjoke_embed.setDescription(
                "An error occurred while fetching the joke!"
            );
        }

        event
            .deferReply()
            .setEphemeral(true)
            .addEmbeds(cnjoke_embed.build())
            .queue();
    }

    /**
     * Fetches a random Chuck Norris joke from the API.
     *
     * @return The response from the API.
     * @throws Exception If an error occurs while fetching the joke.
     */
    private CNResponse fetchJoke() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(con.getInputStream())
            )
        ) {
            Gson gson = new Gson();
            return gson.fromJson(reader, CNResponse.class);
        } finally {
            con.disconnect();
        }
    }
}
