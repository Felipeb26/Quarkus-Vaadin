package com.felipes.test.frontend.components.cards;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VerticalCard extends VerticalLayout {

    public VerticalCard() {
        deafultStyle();
        HorizontalLayout layout = new HorizontalLayout();
        add(layout);
    }

    public VerticalCard(Component... components) {
        deafultStyle();
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(components);
        add(layout);
    }


    private void deafultStyle() {
        setWidthFull();
        addClassName("card");
        setAlignItems(Alignment.BASELINE);
    }
}
