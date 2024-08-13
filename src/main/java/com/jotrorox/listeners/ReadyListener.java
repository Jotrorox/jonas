package com.jotrorox.listeners;

import ch.qos.logback.classic.Logger;
import com.jotrorox.Jonas;
import com.jotrorox.db.DBQueries;
import com.jotrorox.db.DatabaseLogger;
import com.jotrorox.db.UserUtil;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

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
            Jonas.getDatabaseManager().executeUpdate(DBQueries.CREATE_TABLE.getQuery());
            dbLogger.info("Created the table in the database!");

            List<Guild> guilds = genericEvent.getJDA().getGuilds();
            List<Member> members = new ArrayList<>();
            for (Guild guild : guilds) members.addAll(guild.loadMembers().get());

            for (Member member : members)
                UserUtil.insertUser(member.getId(), member.getEffectiveName(), member.getUser().getAvatarUrl(), member.getGuild().getId());

            Jonas.getDatabaseManager().disconnect();
        });
    }
}