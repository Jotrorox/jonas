package com.jotrorox.jonas

import dev.kord.common.Color
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.rest.builder.message.embed

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

        if (command.rootName == "ping") {
            val response = interaction.deferEphemeralResponse()
            val ping = interaction.kord.gateway.averagePing

            var embedColor = Color(0, 0, 255)

            if (ping?.inWholeMilliseconds!! > 125) embedColor = Color(0, 255, 0)
            if (ping.inWholeMilliseconds > 250) embedColor = Color(255, 255, 0)
            if (ping.inWholeMilliseconds > 500) embedColor = Color(255, 0, 0)


            response.respond {
                embed {
                    color = embedColor
                    title = "Ping"
                    description = "The current ping is: " + ping.inWholeMilliseconds + "ms"
                    footer {
                        text = "Jonas - made by jotrorox"
                        icon = "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
                    }
                }
            }
        }
    }

    bot.login {
        presence {
            playing("with you")
        }

        intents += Intent.Guilds
    }
}