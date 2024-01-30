package com.felipes.test.frontend.components;

import com.vaadin.flow.component.html.Div;

public class Loading extends Div {

    public Loading(Boolean show) {
        Div div = new Div();
        div.setWidth("103.6em");
        div.setMaxWidth("120em");
        div.setHeight("50.6em");
        div.setMaxHeight("50.6em");
        div.getElement().getStyle().set("position", "absolute");
        div.getElement().getStyle().set("top", "0");
        div.getElement().getStyle().set("left", "0");
        div.getElement().getStyle().set("margin", "auto");
        div.getElement().getStyle().set("display", "flex");
        div.getElement().getStyle().set("justify-content", "center");
        div.getElement().getStyle().set("align-itens", "center");
        div.getElement().getStyle().set("background", "var(--gray)");
        div.getElement().getStyle().set("overflow", "hidden");

        Div span = new Div();

        span.setWidth("250px");
        span.getElement().getStyle().set("border-radius", "50%");
        span.getElement().getStyle().set("height", "250px");
        span.getElement().getStyle().set("border", "5px solid var(--gray)");
        span.getElement().getStyle().set("border-top", "2px solid blue");
        span.getElement().getStyle().set("background", "transparent");
        span.getElement().getStyle().set("z-index", "3");
        span.addClassName("progress-bar");

        div.add(span);
        div.setVisible(show);
        add(div);
    }
}
