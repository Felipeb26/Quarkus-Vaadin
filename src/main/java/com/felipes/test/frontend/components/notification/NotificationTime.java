package com.felipes.test.frontend.components.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationTime {

    SHORT(5000),
    LONG(10000);

    private final int time;

}
