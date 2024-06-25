package com.jotrorox.jonas

import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

suspend fun main() {
    val token =  System.getenv("DISCORD_TOKEN") ?: error("No token provided.")

    val bot = Kord(token)

    bot.createGuildChatInputCommand(
        Snowflake(1189560543091118170),
        "ping",
        "Checks the Ping of the bot for you"
    )

    bot.on<GuildChatInputCommandInteractionCreateEvent> {
        val command = interaction.command

        if (command.rootName.equals("ping")) {
            val response = interaction.deferEphemeralResponse()

            response.respond {
                content = "This hasn't been implemented yet"
            }
        }
    }

    bot.login {
        presence {
            playing("with you")
        }

        @OptIn(PrivilegedIntent::class)
        intents += Intent.Guilds
    }
}