package com.jotrorox.listeners;

import ch.qos.logback.classic.Logger;
import com.jotrorox.Jonas;
import com.jotrorox.db.DBQueries;
import com.jotrorox.db.DatabaseLogger;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadyListener implements EventListener {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (!(genericEvent instanceof ReadyEvent)) return;

        Logger logger = ListenerLogger.getLogger();
        logger.info("Bot is ready and logged in as {}", genericEvent.getJDA().getSelfUser().getAsTag());

        genericEvent.getJDA().getPresence().setActivity(Activity.customStatus("This bot is still in development! Please be patient!"));
        logger.info("Set the bot's activity to 'This bot is still in development! Please be patient!'");

        executorService.submit(() -> {
            Logger dbLogger = DatabaseLogger.getLogger();
            Jonas.getDatabaseManager().connect();
            dbLogger.info("Connected to the database!");
            Jonas.getDatabaseManager().executeUpdate(DBQueries.CREATE_TABLE.getQuery());
            dbLogger.info("Created the table in the database!");

            List<Guild> guilds = genericEvent.getJDA().getGuilds();
            List<Member> members = new ArrayList<>();
            for (Guild guild : guilds) {
                members.addAll(guild.loadMembers().get());
            }

            for (Member member : members) {
                try (PreparedStatement preparedStatement = Jonas.getDatabaseManager().prepareStatement(DBQueries.INSERT_USER.getQuery())) {
                    preparedStatement.setString(1, member.getId());
                    preparedStatement.setString(2, member.getUser().getName());
                    preparedStatement.setString(3, member.getUser().getAvatarUrl());
                    preparedStatement.setString(4, member.getGuild().getId());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    dbLogger.error("Failed to execute the prepared statement!", e);
                }
            }
            Jonas.getDatabaseManager().disconnect();
            dbLogger.info("Disconnected from the database!");
        });
    }
}