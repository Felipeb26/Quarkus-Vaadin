package com.felipes.test.backend.domain.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "contatos_tb")
public class ContatoEntity extends AbstractEntity<ContatoEntity> implements Serializable {

    private String nome;
    private String cpfCnpj;
    private String banco;
    private Long agencia;
    private Long conta;
    private BigDecimal valor;
    private String apelido;

}
