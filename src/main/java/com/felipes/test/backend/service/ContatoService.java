package com.felipes.test.backend.service;

import com.felipes.test.backend.domain.entity.ContatoEntity;

import java.util.List;

public interface ContatoService {
    List<ContatoEntity> findAll();

    ContatoEntity findById(String id);

    void save(ContatoEntity persona);

    ContatoEntity update(String id, ContatoEntity entity);

    void delete(String id);
}
