package com.felipes.test.frontend;

import com.felipes.test.frontend.views.ContatosView;
import com.felipes.test.frontend.views.LoginView;
import com.felipes.test.frontend.views.MainView;
import com.felipes.test.frontend.views.TransferenciaView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class HeaderLayout extends AppLayout {

    public HeaderLayout() {
        setDrawerOpened(false);
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 title = new H1("Quarkus Vaadin");

        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassName("pointer");


        HorizontalLayout header = new HorizontalLayout(toggle, title);
        header.setWidth("100%");

        header.addClassName("header");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink routerLink = new RouterLink();
        routerLink.setHighlightCondition(HighlightConditions.sameLocation());


        VerticalLayout layout = new VerticalLayout(routerLink,
                new RouterLink("HOME", MainView.class),
                new RouterLink("TRANSFERENCIA", TransferenciaView.class),
                new RouterLink("LOGIN", LoginView.class),
                new RouterLink("CONTATOS", ContatosView.class)
        );

        layout.setSizeFull();
        layout.addClassName("drawer");
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        addToDrawer(layout);
    }

}
