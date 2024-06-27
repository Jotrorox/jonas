package com.jotrorox.jonas.commands;

import java.util.Random;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class RPSCommand extends Command {

    public RPSCommand() {
        super("rps", "Play Rock Paper Scissors against the computer");
        this.getData()
            .addOption(OptionType.STRING, "choice", "Your choice", true);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String userChoice = event.getOption("choice").getAsString();
        int computerChoice = (new Random()).nextInt(3);
        int result = -1;

        if (userChoice.equals("rock")) {
            if (computerChoice == 0) result = 0;
            if (computerChoice == 1) result = 1;
            if (computerChoice == 2) result = 2;
        }

        if (userChoice.equals("paper")) {
            if (computerChoice == 0) result = 2;
            if (computerChoice == 1) result = 0;
            if (computerChoice == 2) result = 1;
        }

        if (userChoice.equals("scissors")) {
            if (computerChoice == 0) result = 1;
            if (computerChoice == 1) result = 2;
            if (computerChoice == 2) result = 0;
        }

        if (result == -1) {
            EmbedBuilder rps_embed = new EmbedBuilder();
            rps_embed.setTitle("Rock Paper Scissors");
            rps_embed.setDescription("Invalid choice");
            rps_embed.setColor(0xff0000);
            rps_embed.setFooter(
                "Jonas - by jotrorox",
                "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
            );
            event
                .deferReply()
                .setEphemeral(true)
                .addEmbeds(rps_embed.build())
                .queue();

            return;
        }

        if (result == 0) {
            EmbedBuilder rps_embed = new EmbedBuilder();
            rps_embed.setTitle("Rock Paper Scissors");
            rps_embed.setDescription(
                "It's a tie!\nThe computer chose: " +
                (computerChoice == 0
                        ? "rock"
                        : computerChoice == 1 ? "paper" : "scissors") +
                ""
            );
            rps_embed.setColor(0xffff00);
            rps_embed.setFooter(
                "Jonas - by jotrorox",
                "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
            );
            event
                .deferReply()
                .setEphemeral(true)
                .addEmbeds(rps_embed.build())
                .queue();

            return;
        }

        if (result == 1) {
            EmbedBuilder rps_embed = new EmbedBuilder();
            rps_embed.setTitle("Rock Paper Scissors");
            rps_embed.setDescription(
                "You win!\nThe computer chose: " +
                (computerChoice == 0
                        ? "rock"
                        : computerChoice == 1 ? "paper" : "scissors") +
                ""
            );
            rps_embed.setColor(0x00ff00);
            rps_embed.setFooter(
                "Jonas - by jotrorox",
                "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
            );
            event
                .deferReply()
                .setEphemeral(true)
                .addEmbeds(rps_embed.build())
                .queue();

            return;
        }

        if (result == 2) {
            EmbedBuilder rps_embed = new EmbedBuilder();
            rps_embed.setTitle("Rock Paper Scissors");
            rps_embed.setDescription(
                "You lose!\nThe computer chose: " +
                (computerChoice == 0
                        ? "rock"
                        : computerChoice == 1 ? "paper" : "scissors") +
                ""
            );
            rps_embed.setColor(0xff0000);
            rps_embed.setFooter(
                "Jonas - by jotrorox",
                "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
            );
            event
                .deferReply()
                .setEphemeral(true)
                .addEmbeds(rps_embed.build())
                .queue();

            return;
        }

        EmbedBuilder rps_embed = new EmbedBuilder();
        rps_embed.setTitle("Rock Paper Scissors");
        rps_embed.setDescription("An error occurred");
        rps_embed.setColor(0xff0000);
        rps_embed.setFooter(
            "Jonas - by jotrorox",
            "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
        );
        event
            .deferReply()
            .setEphemeral(true)
            .addEmbeds(rps_embed.build())
            .queue();
    }
}
