package com.jotrorox.jonas

import dev.kord.cache.api.data.description
import dev.kord.common.Color
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
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

        if (command.rootName.equals("ping")) {
            val response = interaction.deferEphemeralResponse()
            val ping = interaction.kord.gateway.averagePing

            var embed_color = Color(0, 0, 255)

            if (ping?.inWholeMilliseconds!! > 125) embed_color = Color(0, 255, 0)
            if (ping.inWholeMilliseconds > 250) embed_color = Color(255, 255, 0)
            if (ping.inWholeMilliseconds > 500) embed_color = Color(255, 0, 0)


            response.respond {
                embed {
                    color = embed_color
                    title = "Ping"
                    description = "The current ping is: " + ping.inWholeMilliseconds + "ms"
                    footer {
                        text = "Jonas - made by jotrorox"
                        icon = 
                    }
                }
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