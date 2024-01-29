package com.felipes.test.backend.service.impl;

import com.felipes.test.backend.domain.entity.TransferenciaEntity;
import com.felipes.test.backend.repository.TransferenciaRepository;
import com.felipes.test.backend.service.TransferenciaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class TransferenciaServiceImpl implements TransferenciaService {

    @Inject
    private TransferenciaRepository transferenciaRepository;

    @Override
    public List<TransferenciaEntity> findAll() {
        return transferenciaRepository.findAll().stream().toList();
    }

    @Override
    public TransferenciaEntity findById(String id) {
        return transferenciaRepository.findById(new ObjectId(id));
    }

    @Override
    public void save(TransferenciaEntity persona) {
        transferenciaRepository.persist(persona);
    }

    @Override
    public TransferenciaEntity update(String id, TransferenciaEntity entity) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
