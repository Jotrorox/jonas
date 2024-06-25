package com.jotrorox.jonas

import dev.kord.common.Color
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.entity.Message
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.message.ReactionAddEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.rest.builder.interaction.integer
import dev.kord.rest.builder.interaction.role
import dev.kord.rest.builder.interaction.string
import dev.kord.rest.builder.message.embed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseHelper(private val dbName: String) {

    private var connection: Connection? = null

    fun connect(): Boolean {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:$dbName")
            println("Connected to $dbName")
            return true
        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        }
    }

    fun disconnect() {
        try {
            connection?.close()
            println("Disconnected from $dbName")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun executeStatement(sql: String): Boolean {
        return try {
            connection?.createStatement()?.execute(sql)
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }

    fun getConnection(): Connection? {
        return connection
    }
}

suspend fun main() {
    val token =  System.getenv("DISCORD_TOKEN") ?: error("No token provided.")

    val dbHelper = DatabaseHelper("main.db")
    if (!dbHelper.connect()) return

    val createTableSql = """
        CREATE TABLE IF NOT EXISTS reaction_roles (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            server_id TEXT NOT NULL,
            message_id TEXT NOT NULL,
            emoji TEXT NOT NULL,
            role_id TEXT NOT NULL
        )
    """.trimIndent()

    dbHelper.executeStatement(createTableSql)

    val bot = Kord(token)

    bot.createGuildChatInputCommand(
        Snowflake(1189560543091118170),
        "ping",
        "Checks the Ping of the bot for you"
    )

    bot.createGuildChatInputCommand(
        Snowflake(1189560543091118170),
        "rr",
        "Adds a reaction role listener to a message"
    ) {
        integer("channelID", "The ID of the channel the message was sent in") {
            required = false
        }
        integer("messageID", "The ID of the message to add the listener to") {
            required = false
        }
        string("emoji", "The emoji the user should be the emoji that should be listened on") {
            required = true
        }
        role("role", "The role to add to the user") {
            required = true
        }
    }

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
                    description = "The current ping is: ${ping.inWholeMilliseconds}ms"
                    footer {
                        text = "Jonas - made by jotrorox"
                        icon = "https://raw.githubusercontent.com/Jotrorox/jonas/main/rsc/images/avatar.png"
                    }
                }
            }
        }

        if (command.rootName == "rr") {
            val response = interaction.deferEphemeralResponse()

            val channelID =  command.integers["channelID"] ?: interaction.channelId.value.toLong()
            val messaageID = command.integers["messageID"] ?: interaction.channel.messages.last().id.value.toLong()
            val emoji = command.strings["emoji"]!!
            val roleID = command.roles["role"]!!

            
        }
    }

    bot.on<ReactionAddEvent> {
        val querySql = "SELECT * FROM reaction_roles"
        val resultSet = dbHelper.getConnection()?.createStatement()?.executeQuery(querySql)
        while (resultSet?.next() == true) {
            println("ID: ${resultSet.getInt("id")}, Server ID: ${resultSet.getString("server_id")}, Message ID: ${resultSet.getString("message_id")}, Emoji: ${resultSet.getString("emoji")}, Role ID: ${resultSet.getString("role_id")}")
        }
    }

    bot.login {
        presence {
            playing("with you")
        }

        intents += Intent.Guilds
    }

    dbHelper.disconnect()
}