package com.felipes.test.frontend.views;

import com.felipes.test.frontend.HeaderLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Contatos")
@Route(value = "contatos", layout = HeaderLayout.class)
public class ContatosView extends VerticalLayout {
}
