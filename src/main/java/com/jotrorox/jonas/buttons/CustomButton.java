package com.jotrorox.jonas.buttons;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public abstract class CustomButton {
    private static Button button;
    
    public CustomButton(ButtonType type, String id, String label) {
        switch (type) {
            case Primary -> button = Button.primary(id, label);
            case Success -> button = Button.success(id, label);
            case Secondary -> button = Button.secondary(id, label);
        }
    }
    
    public void execute(ButtonInteractionEvent event) {
        return;
    }
    
    public Button getButton() {
        return button;
    }
}