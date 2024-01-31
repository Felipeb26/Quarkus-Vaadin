package com.felipes.test.frontend.utils;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.textfield.TextField;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

@Slf4j
public class CurrencyConverter extends ComponentEvent<TextField> {

    private static final Locale LOCALE = Locale.getDefault();

    public CurrencyConverter(TextField textField, boolean fromClient) {
        super(textField, fromClient);
        var value = textField.getValue();

        value = formatToNumber(value);
        double amount = Double.parseDouble(value);

        String formattedCurrency = formatCurrency(amount);
        double parsedAmount = parseCurrency(formattedCurrency);

        log.info("Parsed amount: {} \t Parsed currency: {}", parsedAmount, formattedCurrency);
        textField.setValue(formattedCurrency);
    }

    private static String formatToNumber(String value) {
        value = value.isEmpty() ? "0.0" : value;
        value = value.replaceAll("\\D.\\D", "");
        value = value.replace("R$", "");
        value = value.replace("?", "");

        value = value.replace(",", ".");
        value = value.trim();
        return  value;
    }

    private static String formatCurrency(double amount) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(LOCALE);
        decimalFormat.setCurrency(Currency.getInstance(LOCALE));
        return decimalFormat.format(amount);
    }

    private static double parseCurrency(String input) {
        try {
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(LOCALE);
            return decimalFormat.parse(input).doubleValue();
        } catch (Exception e) {
            return 0d;
        }
    }

}
