package com.felipes.test.backend.domain.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity<T> extends PanacheMongoEntity {

    @Getter(AccessLevel.PUBLIC)
    protected LocalDateTime dataCriacao;
    @Getter(AccessLevel.PUBLIC)
    protected LocalDateTime dataAtualizacao;


    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = LocalDateTime.now();
    }

    @PrePersist
    void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PostPersist
    void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
