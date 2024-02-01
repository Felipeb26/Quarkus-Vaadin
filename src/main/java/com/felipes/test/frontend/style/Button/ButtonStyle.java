package com.felipes.test.frontend.style.Button;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

public class ButtonStyle {

    public Button style(Button button) {
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        return button;
    }

    public Button style(Button button, ButtonMode mode) {
        switch (mode) {
            case ERROR -> button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            case SUCESS -> button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        }
        return button;
    }

}
