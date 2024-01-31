package com.felipes.test.frontend.components.notification;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NotificationComponent extends VerticalLayout {

    public NotificationComponent(String text) {
        Notification notification = Notification.show(text);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        defaultConfig(notification, text);
        notification.open();
    }

    public NotificationComponent(String text, NotificationTypes type) {
        Notification notification = Notification.show(text);
        defaultConfig(notification, text);

        switch (type) {
            case ALERT -> notification.addThemeVariants(NotificationVariant.LUMO_WARNING);
            case ERROR -> notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            default -> notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }
        notification.open();
    }

    public NotificationComponent(String text, NotificationTime time) {
        Notification notification = Notification.show(text);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        defaultConfig(notification, text, time.getTime());
        notification.open();
    }

    public NotificationComponent(String text, NotificationTypes type, NotificationTime time) {
        Notification notification = new Notification();

        switch (type) {
            case ALERT -> notification.addThemeVariants(NotificationVariant.LUMO_WARNING);
            case ERROR -> notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            default -> notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }

        defaultConfig(notification, text, time.getTime());
        notification.open();
    }

    private void defaultConfig(Notification notification, String text) {
        defaultConfig(notification, text, 5000);
    }

    private void defaultConfig(Notification notification, String text, int time) {
        notification.setPosition(Notification.Position.TOP_END);
        notification.setDuration(time);

        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
        var layout = new HorizontalLayout(new Text(text), closeButton);
        layout.setAlignItems(Alignment.CENTER);

        closeButton.addClickListener(click -> notification.close());

        notification.add(layout);
    }
}
