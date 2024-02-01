package com.felipes.test.frontend.components.cards;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VerticalCard extends VerticalLayout {

    public VerticalCard() {
        deafultStyle();
        VerticalLayout layout = new VerticalLayout();
        add(layout);
    }

    public VerticalCard(Component... components) {
        deafultStyle();
        VerticalLayout layout = new VerticalLayout();
        layout.add(components);
        add(layout);
    }


    private void deafultStyle() {
        setWidthFull();
        addClassName("card");
        setAlignItems(Alignment.BASELINE);
    }
}
