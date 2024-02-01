package com.felipes.test.frontend.utils;

import com.vaadin.flow.component.textfield.TextField;

public class NumberConverter {

    public NumberConverter(TextField textField) {
        var value = textField.getValue();
        if (value == null || value.isEmpty() || value.isBlank()) return;
        value = value.replaceAll("\\D", "");
        textField.setValue(value);
    }
}
