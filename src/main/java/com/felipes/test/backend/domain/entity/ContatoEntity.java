package com.felipes.test.backend.domain.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "contatos_tb")
public class ContatoEntity extends PanacheMongoEntity {

     private String agencia;
     private String banco;
     private String conta;
     private String nome;
     private String valor;

}
