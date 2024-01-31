package com.felipes.test.frontend.views;

import com.felipes.test.backend.domain.entity.ContatoEntity;
import com.felipes.test.backend.domain.entity.TransferenciaEntity;
import com.felipes.test.backend.service.ContatoService;
import com.felipes.test.frontend.HeaderLayout;
import com.felipes.test.frontend.components.Loading;
import com.felipes.test.frontend.components.cards.HorizontalCard;
import com.felipes.test.frontend.components.cards.VerticalCard;
import com.felipes.test.frontend.components.notification.NotificationComponent;
import com.felipes.test.frontend.components.notification.NotificationTypes;
import com.felipes.test.frontend.utils.AsyncFillComponent;
import com.felipes.test.frontend.utils.Checks;
import com.felipes.test.frontend.utils.CurrencyConverter;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Double.parseDouble;

@Slf4j
@PageTitle("home")
@Route(value = "", layout = HeaderLayout.class)
@CssImport("./themes/styles/styles.css")
public class MainView extends VerticalLayout {

    @Inject
    private transient AsyncFillComponent<ContatoEntity, String> fillComponent;
    @Inject
    private ContatoService contatoService;
    private transient List<ContatoEntity> contatoEntities;
    private Select<String> contatos;
    private Select<String> agenciaConta;
    private Button transferirBtn;
    private Boolean loadingProgress = true;


    MainView() {
        addClassName("main");
        setSizeFull();

        Loading loading = new Loading(loadingProgress);
        HorizontalCard horizontalCard = new HorizontalCard();
        createComponents(horizontalCard);

        add(horizontalCard);
        delayFunction(1);
    }

    private void createComponents(HorizontalCard horizontalCard) {
        contatos = new Select<>();
        contatos.setLabel("Contato");
        contatos.setEmptySelectionAllowed(true);

        agenciaConta = new Select<>();
        agenciaConta.setLabel("Agencia - Conta");
        agenciaConta.setEmptySelectionAllowed(true);

        TextField valorTransferencia = new TextField("Valor transferencia", "BRL");
        valorTransferencia.setValueChangeMode(ValueChangeMode.LAZY);
        valorTransferencia.addClassName("input");
        valorTransferencia.addInputListener(inputEvent -> new CurrencyConverter(valorTransferencia, true));

        transferirBtn = new Button("CONFIRMAR");

        transferirBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        transferirBtn.addClickShortcut(Key.ENTER);

        DatePicker datePicker = new DatePicker("Data trasferencia", LocalDate.now());
        datePicker.setMin(LocalDate.now());


        horizontalCard.add(contatos, agenciaConta, datePicker, valorTransferencia, transferirBtn);
        horizontalCard.setFlexGrow(1, contatos, agenciaConta, datePicker, valorTransferencia, transferirBtn);

        transferirBtn.addClickListener(click -> {
            if (new Checks().isNull(contatos.getValue(), agenciaConta.getValue(), valorTransferencia.getValue(), datePicker.getValue())) {
                new NotificationComponent("Todos os campos precisam ser preenchidos!", NotificationTypes.ERROR);
                return;
            }
            add(infoCard(convertToTransferencia(contatos.getValue(), agenciaConta.getValue(), valorTransferencia.getValue(), datePicker.getValue())));
            transferirBtn.setEnabled(false);
        });
    }

    private void delayFunction(Integer delay) {
        var ui = UI.getCurrent();
        log.trace("DATA QUE INICIOU: {}", LocalDateTime.now());
        var scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(() -> {
            log.trace("DATA QUE ROUDOU: {}", LocalDateTime.now());
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

    private Future<List<ContatoEntity>> contatos() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(() -> {
            Future<List<ContatoEntity>> future = CompletableFuture.completedFuture(contatoService.findAll());
            return future.get();
        });
    }

    private VerticalCard infoCard(TransferenciaEntity transferencia) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        VerticalCard infoCard = new VerticalCard();

        String resume = "O valor de R$ %s com taxa de %s\n".formatted(transferencia.getValorEnviado(), "2.5%") +
                "da conta s\n" +
                "será enviado para (o/a) %s conta %s\n".formatted("Felipe", "4151") +
                "no inicio do dia %s\n".formatted(transferencia.getDataTransferencia().format(formatter)) +
                "com previssão máxima para ás 17hrs\n";

        TextArea area = new TextArea();
        area.setWidthFull();
        area.setValue(resume);
        area.setReadOnly(true);

        area.getElement().getStyle().set("background", "var(--gray)");

        Div actions = new Div();
        actions.setWidthFull();
        actions.addClassName("actions");

        Button cancelButton = new Button("CANCELAR");
        Button confirmButton = new Button("CONFIRMAR");

        cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        cancelButton.addClickListener(click -> {
            remove(infoCard);
            transferirBtn.setEnabled(true);
        });

        confirmButton.addClickListener(click -> {
        });

        actions.add(cancelButton, confirmButton);
        infoCard.add(area, actions);
        return infoCard;
    }

    private TransferenciaEntity convertToTransferencia(String contato, String agencia, String valor, LocalDate dataTransferencia) {
        valor = valor.replaceAll("\\D.\\D", "");

        return TransferenciaEntity.builder()
                .valorEnviado(BigDecimal.valueOf(parseDouble(valor)))
                .dataTransferencia(dataTransferencia)
                .build();
    }

}
