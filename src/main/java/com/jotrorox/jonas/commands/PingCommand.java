package com.jotrorox.jonas.commands;

public class PingCommand extends Command {
    public PingCommand() {
        super("ping", "Provides the bot's latency");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.getChannel().sendMessage("Pong!").queue();
    }
}
