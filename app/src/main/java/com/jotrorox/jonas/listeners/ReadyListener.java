package com.jotrorox.jonas.listeners;

import org.jetbrains.annotations.NotNull;

import com.jotrorox.jonas.config.Config;
import com.jotrorox.jonas.config.ConfigUtil;

import ch.qos.logback.classic.Logger;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyListener implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (!(genericEvent instanceof ReadyEvent)) return;
        
        ReadyEvent event = (ReadyEvent) genericEvent;

        Logger logger = ListenerLogger.getLogger();
        logger.info("Bot is ready and logged in as {}", event.getJDA().getSelfUser().getAsTag());

        Config config = ConfigUtil.getConfigFromFile(ConfigUtil.DEFAULT_CONFIG_PATH);

        event.getJDA().getPresence().setActivity(Activity.customStatus(config.getDiscordStatus()));
        logger.info("Set the bot's activity to '{}'", config.getDiscordStatus());
        
    }
}
