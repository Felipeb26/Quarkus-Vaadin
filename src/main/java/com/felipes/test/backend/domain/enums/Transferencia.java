package com.felipes.test.backend.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Transferencia {

    TED("TED"),
    TED_AGENDADA("TED_AGENDADA"),
    DOC("DOC");

    private final String tipo;
}
