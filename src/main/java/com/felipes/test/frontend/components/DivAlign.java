package com.felipes.test.frontend.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class DivAlign extends Div {

    public DivAlign() {
    }

    public DivAlign(Component... components) {
        var style = getStyle();
        style.set("display", "flex")
                .set("align-itens", "baseline");

        add(components);
    }
}
