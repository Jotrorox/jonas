package com.jotrorox.jonas.commands;

import com.google.gson.Gson;
import com.jotrorox.jonas.util.jsonInterfaces.MeowFactsResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class CatCommand extends Command {

    public CatCommand() {
        super("cat", "Provides all sorts of things about cats! 🐱");
        this.getData()
            .addOption(
                OptionType.BOOLEAN,
                "gif",
                "Whether to show a gif instead of a picture",
                false
            );
        this.getData()
            .addOption(
                OptionType.BOOLEAN,
                "fact",
                "Whether to show a cat fact",
                false
            );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder ping_embed = new EmbedBuilder();

        ping_embed.setTitle("🐱 Cat!");
        ping_embed.setFooter(
            "Jonas - by jotrorox",
            "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
        );

        try {
            boolean fact = event.getOption("fact").getAsBoolean();
            if (fact) {
                MeowFactsResponse meowFacts = getMeowFacts();
                if (meowFacts != null) ping_embed.setDescription(
                    meowFacts.getFact()
                );
                else ping_embed.setDescription("Failed to get a cat fact!");
                event
                    .deferReply()
                    .setEphemeral(true)
                    .addEmbeds(ping_embed.build())
                    .queue();
                return;
            }
        } catch (Exception e) {}

        try {
            boolean gif = event.getOption("gif").getAsBoolean();
            if (gif) ping_embed.setImage("https://cataas.com/cat/gif");
            else ping_embed.setImage("https://cataas.com/cat");
        } catch (Exception e) {
            ping_embed.setImage("https://cataas.com/cat");
        }

        event
            .deferReply()
            .setEphemeral(true)
            .addEmbeds(ping_embed.build())
            .queue();
    }

    private MeowFactsResponse getMeowFacts() {
        try {
            URL url = new URL("https://meowfacts.herokuapp.com/");
            HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            Gson gson = new Gson();
            return gson.fromJson(response.toString(), MeowFactsResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
