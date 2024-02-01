package com.felipes.test.frontend.style.Button;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ButtonMode {

    ERROR(0),
    SUCESS(1);

    private final int mode;
}
