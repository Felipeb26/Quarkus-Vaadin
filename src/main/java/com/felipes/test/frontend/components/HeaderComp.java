package com.felipes.test.frontend.components;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class HeaderComp extends HorizontalLayout {

    public HeaderComp() {
        Header header = new Header();
        UnorderedList ul = new UnorderedList();

        ul.add("lista");

        header.add(ul);
        styleHeader(header);
        add(header);
    }

    private void styleHeader(Header header){
        header.setHeight(3f, Unit.EM);
        header.setWidthFull();
    }
}
