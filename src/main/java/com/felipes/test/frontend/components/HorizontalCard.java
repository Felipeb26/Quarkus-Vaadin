package com.felipes.test.frontend.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HorizontalCard extends HorizontalLayout {

    public HorizontalCard() {
        deafultStyle();
        HorizontalLayout layout = new HorizontalLayout();
        add(layout);
    }

    public HorizontalCard(Component... components) {
        deafultStyle();
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(components);
        add(layout);
    }


    private void deafultStyle() {
        setWidth("95%");
        addClassName("card");
        setAlignItems(Alignment.BASELINE);
    }

}
