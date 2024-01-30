package com.felipes.test.frontend.views;

import com.felipes.test.backend.domain.entity.ContatoEntity;
import com.felipes.test.backend.service.ContatoService;
import com.felipes.test.frontend.GreetService;
import com.felipes.test.frontend.HeaderLayout;
import com.felipes.test.frontend.components.HorizontalCard;
import com.felipes.test.frontend.components.Loading;
import com.felipes.test.frontend.utils.AsyncFillComponent;
import com.felipes.test.frontend.utils.CurrencyConverter;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
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
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@Slf4j
@Route(value = "", layout = HeaderLayout.class)
@PageTitle("home")
public class MainView extends VerticalLayout {

    @Inject
    private transient AsyncFillComponent<ContatoEntity, String> fillComponent;
    @Inject
    private transient GreetService greetService;
    @Inject
    private ContatoService contatoService;
    private static List<ContatoEntity> contatoEntities;
    private final Select<String> contatos;
    private final Select<String> agenciaConta;
    private Boolean loadingProgress = true;

    MainView() {
        addClassName("main");
        setAlignItems(Alignment.CENTER);
        Loading loading = new Loading(loadingProgress);


        contatos = new Select<>();
        contatos.setLabel("Contato");
        contatos.setEmptySelectionAllowed(true);

        agenciaConta = new Select<>();
        agenciaConta.setLabel("Agencia - Conta");
        agenciaConta.setEmptySelectionAllowed(true);

        TextField valorTransferencia = new TextField("Valor transferencia", "BRL");
        valorTransferencia.setValueChangeMode(ValueChangeMode.LAZY);
        valorTransferencia.addClassName("input");
        valorTransferencia.setPrefixComponent(new Span("R$"));
        valorTransferencia.addInputListener(inputEvent -> new CurrencyConverter(valorTransferencia, false));

        Button transferirBtn = new Button("CONFIRMAR");
        transferirBtn.addClickListener(click -> infoCard(contatoEntities.get(0)));
        transferirBtn.addThemeVariants(ButtonVariant.MATERIAL_OUTLINED, ButtonVariant.LUMO_SUCCESS);
        transferirBtn.addClickShortcut(Key.ENTER);

        DatePicker datePicker = new DatePicker("Data trasferencia", LocalDate.now());
        datePicker.setMin(LocalDate.now());

        HorizontalCard horizontalCard = new HorizontalCard();
        horizontalCard.add(contatos, agenciaConta, datePicker, valorTransferencia, transferirBtn);
        horizontalCard.setFlexGrow(1, contatos, agenciaConta, datePicker, valorTransferencia, transferirBtn);
        add(horizontalCard);
        log.info("DATA QUE INICIOU " + LocalDateTime.now());

        delayFunction(1);
    }

    private void delayFunction(Integer delay) {
        var ui = UI.getCurrent();
        var scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(() -> {
            log.info("DATA QUE ROUDOU " + LocalDateTime.now());
            int limit = 1;
            try {
                contatoEntities = contatos().get();
                ui.access(() -> {
                    contatos.setItems(contatoEntities.stream().map(ContatoEntity::getNome).toList());
                    agenciaConta.setItems(contatoEntities.stream().map(it -> String.format("%s-%s", it.getAgencia(), it.getConta())).toList());
                });
            } catch (Exception e) {
                while (limit <= 3) {
                    limit++;
                    delayFunction(limit);
                }
                log.error(e.getMessage());
            }
            loadingProgress = false;
        }, delay == null ? 1 : delay, TimeUnit.SECONDS);
    }

    private HorizontalCard infoCard(Object... params) {
        HorizontalCard infoCard = new HorizontalCard();

        System.out.println(params[0]);
        String resume = "O valor de R$ %s com taxa de %s\n".formatted(Optional.of(params[0]).orElse(" "), Optional.of(params[1]).orElse(" ")) +
                "da conta s\n" +
                "será enviado para (o/a) %s conta %s\n".formatted(Optional.of(params[2]).orElse(" "), Optional.of(params[3]).orElse(" ")) +
                "no inicio do dia %s\n".formatted(Optional.of(params[4]).orElse(" ")) +
                "com previssão máxima para ás 17hrs\n";

        TextArea area = new TextArea();
        area.setValue(resume);

        area.setWidthFull();
        infoCard.add(area);
        return infoCard;
    }

    private Future<List<ContatoEntity>> contatos() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(() -> {
            Future<List<ContatoEntity>> future = CompletableFuture.completedFuture(contatoService.findAll());
            return future.get();
        });
    }

}
