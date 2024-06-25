package com.jotrorox.jonas

import dev.kord.core.Kord
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

suspend fun main() {
    val token =  System.getenv("DISCORD_TOKEN") ?: error("No token provided.")

    val bot = Kord(token)

    bot.login {
        presence {
            playing("with you")
        }

        @OptIn(PrivilegedIntent::class)
        intents += Intent.Guilds
    }
}