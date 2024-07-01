package com.jotrorox.jonas.buttons.settings;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class SettingsButtonHandler {
    public void handleSettingsButton(ButtonInteractionEvent event) {
        switch (event.getComponentId()) {
            case "jonas.settings.test" -> (new TestButton()).execute(event);
        }
    }
}