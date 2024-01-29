package com.felipes.test.backend.service;

import com.felipes.test.backend.domain.entity.TransferenciaEntity;

import java.util.List;

public interface TransferenciaService {
    List<TransferenciaEntity> findAll();

    TransferenciaEntity findById(String id);

    void save(TransferenciaEntity persona);

    TransferenciaEntity update(String id, TransferenciaEntity entity);

    void delete(String id);
}
