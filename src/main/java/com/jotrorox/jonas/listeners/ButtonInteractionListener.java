package com.jotrorox.jonas.listeners;

import com.jotrorox.jonas.buttons.settings.SettingsButtonHandler;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import okhttp3.internal.http2.Settings;

public class ButtonInteractionListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponent().getId().startsWith("jonas.settings"))
            (new SettingsButtonHandler()).handleSettingsButton(event);
    }
}
