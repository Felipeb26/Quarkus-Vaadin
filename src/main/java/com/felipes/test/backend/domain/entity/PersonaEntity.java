package com.felipes.test.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "persona_tb")
public class PersonaEntity extends AbstractEntity<PersonaEntity> {

    private String nome;
    private String sobrenome;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento;

}