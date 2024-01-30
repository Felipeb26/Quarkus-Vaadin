package com.felipes.test.backend.domain.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "contatos_tb")
public class ContatoEntity extends PanacheMongoEntity {

     private Long agencia;
     private String banco;
     private Long conta;
     private String nome;
     private BigDecimal valor;

}
