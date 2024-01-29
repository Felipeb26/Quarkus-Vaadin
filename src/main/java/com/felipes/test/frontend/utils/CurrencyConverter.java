package com.felipes.test.frontend.utils;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.textfield.TextField;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CurrencyConverter extends ComponentEvent<TextField> {

    public CurrencyConverter(TextField textField, boolean fromClient) {
        super(textField, fromClient);
        var value = textField.getValue();
        value = value.replaceAll("\\D", "");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00", symbols);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setParseBigDecimal(true);
        var valuem = BigDecimal.valueOf(Double.parseDouble(value));
        textField.setValue(decimalFormat.format(valuem));
    }

}
