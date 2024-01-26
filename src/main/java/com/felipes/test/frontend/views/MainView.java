package com.felipes.test.frontend.views;

import com.felipes.test.frontend.GreetService;
import com.felipes.test.frontend.HeaderLayout;
import com.felipes.test.frontend.components.HorizontalCard;
import com.felipes.test.frontend.utils.CurrencyConverter;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "", layout = HeaderLayout.class)
@PageTitle("home")
public class MainView extends VerticalLayout {

    @Inject
    private transient GreetService greetService;
    private HorizontalCard horizontalCard = new HorizontalCard();

    public MainView() {
        addClassName("main");
        setAlignItems(Alignment.CENTER);

        Select<String> contato = new Select<>();
        contato.setLabel("Contato");
        List<String> names = List.of("felipe", "felip", "feli", "felip", "felipes");
        contato.setEmptySelectionAllowed(true);
        contato.setItems(names);

        Select<String> agenciaConta = new Select<>();
        agenciaConta.setLabel("Agencia - Conta");
        agenciaConta.setEmptySelectionAllowed(true);

        TextField valorTransferencia = new TextField("Valor transferencia", "BRL");
        valorTransferencia.setValueChangeMode(ValueChangeMode.LAZY);
        valorTransferencia.addClassName("input");
        valorTransferencia.setPrefixComponent(new Span("R$"));
        valorTransferencia.addInputListener(inputEvent -> new CurrencyConverter(valorTransferencia, false));

        Button transferirBtn = new Button("Say hello");
        transferirBtn.addClickListener(click -> {
            add(infoCard(valorTransferencia.getValue()));
//            transferirBtn.setEnabled(false);
        });
        transferirBtn.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        transferirBtn.addClickShortcut(Key.ENTER);

        DatePicker datePicker = new DatePicker("Data trasferencia", LocalDate.now());
        datePicker.setMin(LocalDate.now());

        horizontalCard.add(contato, agenciaConta, datePicker, valorTransferencia, transferirBtn);
        horizontalCard.setFlexGrow(1, contato, agenciaConta, datePicker, valorTransferencia, transferirBtn);
        add(horizontalCard);
    }

    private HorizontalCard infoCard(Object ...params) {
        HorizontalCard infoCard = new HorizontalCard();

        String resume = "O valor de R$ %s com taxa de %s\n".formatted(Optional.of(params[0]).orElse(" "), Optional.of(params[1]).orElse(" "))+
                "da conta s\n"+
                "será enviado para (o/a) %s conta %s\n".formatted(Optional.of(params[2]).orElse(" "), Optional.of(params[3]).orElse(" "))+
                "no inicio do dia %s\n".formatted(Optional.of(params[4]).orElse(" "))+
                "com previssão máxima para ás 17hrs\n";

        TextArea area = new TextArea();
        area.setValue(resume);

        area.setWidthFull();
        infoCard.add(area);
        return infoCard;
    }

}
