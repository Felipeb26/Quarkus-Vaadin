package com.felipes.test.frontend.views;

import com.felipes.test.backend.domain.entity.ContatoEntity;
import com.felipes.test.backend.service.ContatoService;
import com.felipes.test.frontend.HeaderLayout;
import com.felipes.test.frontend.components.notification.NotificationComponent;
import com.felipes.test.frontend.components.notification.NotificationTypes;
import com.felipes.test.frontend.style.Button.ButtonMode;
import com.felipes.test.frontend.style.Button.ButtonStyle;
import com.felipes.test.frontend.utils.Checks;
import com.felipes.test.frontend.utils.NumberConverter;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import java.util.Locale;

@Slf4j
@PageTitle("Contatos | BatsWorks")
@Route(value = "contatos", layout = HeaderLayout.class)
@CssImport("./themes/styles/contatos.css")
public class ContatosView extends VerticalLayout {

    private static final Locale LOCALE = Locale.getDefault();
    @Inject
    private ContatoService contatoService;
    private final ButtonStyle buttonStyle = new ButtonStyle();

    private TextField nome;
    private TextField apelido;
    private TextField cpfCnpj;
    private TextField banco;
    private TextField agencia;
    private TextField conta;
    private Dialog dialog;

    ContatosView() {
        Icon plus = VaadinIcon.PLUS_CIRCLE.create();
        Button addContatoButton = new Button(plus);
        styleAddButton(addContatoButton);
        plus.setColor("var(--dark-blue");

        addContatoButton.addClickListener(click -> add(openDialog()));
        add(addContatoButton);
        loadContacts();
    }

    private void loadContacts() {
        Div contatoMain = new Div();
        contatoMain.setWidthFull();
        contatoMain.addClassName("contato_main");
        addAttachListener(event -> contatoService.findAll().forEach(it -> {
            Div div = new Div();
            div.getStyle()
                    .set("box-shadow", "0 0 0.2rem var(--dark-blue)")
                    .set("border-radius", "0.3rem")
                    .set("flex-direction", "column")
                    .set("padding", "0 1rem")
                    .set("display", "flex");

            VerticalLayout verticalCard = new VerticalLayout();

            Span apelidoSpan = new Span("Apelido: " + it.getApelido());
            Span nomeSpan = new Span("Nome: " + it.getNome());
            Span agenciaBancoSpan = new Span(it.getAgencia() + " - " + it.getBanco().toUpperCase(LOCALE));
            Span cpfCnpjSpan = new Span("CpfCnpj: " + it.getCpfCnpj());
            Span contaSpan = new Span("Conta: " + it.getConta().toString());

            Div layout = new Div();
            layout.setWidthFull();
            layout.setHeight("5rem");
            layout.addClassName("classe");

            Button deleteContato = new Button("delete", e -> deleteContato(it.id, div));
            buttonStyle.style(deleteContato, ButtonMode.ERROR);
            layout.add(deleteContato);

            if (!new Checks().isNull(it.getApelido())) {
                verticalCard.add(apelidoSpan, nomeSpan, agenciaBancoSpan, cpfCnpjSpan, contaSpan);
            } else {
                verticalCard.add(nomeSpan, agenciaBancoSpan, cpfCnpjSpan, contaSpan);
            }
            styleCard(verticalCard);
            div.add(verticalCard, layout);
            contatoMain.add(div);
        }));
        add(contatoMain);
    }

    private Dialog openDialog() {
        dialog = new Dialog();
        dialog.setHeaderTitle("Adicionar Contato");

        Button closeButton = new Button(new Icon("lumo", "cross"), e -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dialog.getHeader().add(closeButton);

        VerticalLayout dialogLayout = dialogContent();

        Button saveButton = new Button("CONFIRM", e -> saveContato());
        Button cancelButton = new Button("CANCEL", e -> dialog.close());

        buttonStyle.style(saveButton);
        buttonStyle.style(cancelButton, ButtonMode.ERROR);

        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(saveButton);

        dialog.setCloseOnOutsideClick(false);
        dialog.add(dialogLayout);
        dialog.open();
        return dialog;
    }

    private VerticalLayout dialogContent() {
        VerticalLayout layout = new VerticalLayout();

        nome = new TextField("Nome");
        apelido = new TextField("Apelido");
        cpfCnpj = new TextField("CPF | CNPJ");
        banco = new TextField("Banco");
        agencia = new TextField("Agencia");
        conta = new TextField("Conta");

        cpfCnpj.setMaxLength(13);

        agencia.addInputListener(listener -> new NumberConverter(agencia));
        conta.addInputListener(listener -> new NumberConverter(conta));
        conta.setValueChangeMode(ValueChangeMode.LAZY);
        agencia.setValueChangeMode(ValueChangeMode.LAZY);

        layout.add(nome, apelido, cpfCnpj, banco, agencia, conta);
        styleCard(layout);
        return layout;
    }

    private void saveContato() {
        if (new Checks().isNull(nome.getValue(), banco.getValue(), agencia.getValue(), conta.getValue(), cpfCnpj.getValue())) {
            new NotificationComponent("Todos os campos precisam ser preenchidos!", NotificationTypes.ERROR);
            return;
        }
        var contato = ContatoEntity.builder()
                .nome(nome.getValue())
                .apelido(apelido.getValue())
                .banco(banco.getValue())
                .cpfCnpj(cpfCnpj.getValue().replaceAll("\\D", ""))
                .agencia(Long.parseLong(agencia.getValue()))
                .conta(Long.parseLong(conta.getValue()))
                .build();
        contatoService.save(contato);
        dialog.close();
        UI.getCurrent().getPage().reload();
        new NotificationComponent("Contato %s salvo com sucesso".formatted(nome.getValue()));
    }

    private void deleteContato(ObjectId id, Div card) {
        try {
            contatoService.delete(id);
            card.getStyle().clear();
            card.removeAll();
            new NotificationComponent("Contato Deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            new NotificationComponent("Um Erro ocorreu: %s".formatted(e.getMessage()), NotificationTypes.ERROR);
        }
    }

    private void styleCard(VerticalLayout card) {
        card.getStyle()
                .set("display", "grid")
                .set("grid-template-columns", "1fr 1fr")
                .set("gap", "0.3rem")
                .set("flex", "1");
    }

    private void styleAddButton(Button button) {
        button.setWidth("1rem");
        button.setHeight("2.3rem");
        button.getStyle()
                .set("border", "2px solid var(--dark-blue)")
                .set("border-radius", "50%");
    }

}
