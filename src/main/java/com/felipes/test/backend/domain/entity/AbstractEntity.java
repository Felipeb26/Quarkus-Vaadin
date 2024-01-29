package com.felipes.test.backend.domain.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity<T> extends PanacheMongoEntity {

    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)
    protected LocalDateTime dataCriacao;
    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)
    protected LocalDateTime dataAtualizacao;

    @PrePersist
    void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
