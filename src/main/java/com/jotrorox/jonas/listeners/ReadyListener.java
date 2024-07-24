package com.jotrorox.jonas.listeners;

import ch.qos.logback.classic.Logger;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class ReadyListener implements EventListener {



    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (!(genericEvent instanceof ReadyEvent)) return;

        Logger logger = ListenerLogger.getLogger();
        logger.info("Bot is ready and logged in as {}", ((ReadyEvent) genericEvent).getJDA().getSelfUser().getAsTag());
    }
}
