package com.jotrorox.jonas.commands;

import com.google.gson.Gson;
import com.jotrorox.jonas.util.jsonInterfaces.DogResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class DogCommand extends Command {

    private static final String API_URL =
        "https://dog.ceo/api/breeds/image/random";

    public DogCommand() {
        super("dog", "Provides an image of a dog!");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder dog_embed = new EmbedBuilder();

        dog_embed.setTitle("Dog! 🐶");
        dog_embed.setFooter(
            "Jonas - by jotrorox",
            "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
        );

        try {
            DogResponse response = fetchDog();

            dog_embed.setImage(response.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

            dog_embed.setColor(java.awt.Color.RED);
            dog_embed.setDescription(
                "An error occurred while fetching the dog image!"
            );
        }

        event
            .deferReply()
            .setEphemeral(true)
            .addEmbeds(dog_embed.build())
            .queue();
    }

    private DogResponse fetchDog() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(con.getInputStream())
            )
        ) {
            Gson gson = new Gson();
            return gson.fromJson(reader, DogResponse.class);
        } finally {
            con.disconnect();
        }
    }
}
