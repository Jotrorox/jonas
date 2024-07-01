package com.jotrorox.jonas.buttons.settings;

import com.jotrorox.jonas.buttons.ButtonType;
import com.jotrorox.jonas.buttons.CustomButton;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class TestButton extends CustomButton {

    public TestButton() {
        super(ButtonType.Primary, "jonas.settings.test", "This is a test");
    }
    
    public void execute(ButtonInteractionEvent event) {
        return;
    }
}