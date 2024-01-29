package com.felipes.test.backend.domain.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "transferencias_tb")
public class TransferenciaEntity extends PanacheMongoEntity {

    private PersonaEntity contaEnvio;
    private BigDecimal valorEnviado;
    private ContatoEntity contatoDestino;
    private Boolean efetuada;
    private LocalDate dataTransferencia;
}
