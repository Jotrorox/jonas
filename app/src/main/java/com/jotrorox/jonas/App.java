package com.jotrorox.jonas;

import org.slf4j.LoggerFactory;

import com.jotrorox.jonas.config.Config;
import com.jotrorox.jonas.config.ConfigUtil;
import com.jotrorox.jonas.listeners.ReadyListener;

import ch.qos.logback.classic.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class App {

    @SuppressWarnings("unused")
    private static final Logger mainLogger = (Logger) LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException {
        Config config = ConfigUtil.getConfigFromFile(ConfigUtil.DEFAULT_CONFIG_PATH);

        JDABuilder builder = JDABuilder.createDefault(config.getDiscordToken());

        builder.addEventListeners(new ReadyListener());

        JDA bot = builder.build();

        bot.awaitReady();
    }
}
