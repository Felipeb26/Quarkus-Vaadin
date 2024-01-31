package com.felipes.test.frontend.components.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationTypes {

    ALERT(1),
    ERROR(2),
    CONFIRM(3);

    private final int type;
}
