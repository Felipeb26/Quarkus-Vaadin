package com.felipes.test.backend.service.impl;

import com.felipes.test.backend.domain.entity.ContatoEntity;
import com.felipes.test.backend.repository.ContatoRepository;
import com.felipes.test.backend.service.ContatoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class ContatoServiceImpl implements ContatoService {

    @Inject
    private ContatoRepository contatoRepository;


    @Override
    public List<ContatoEntity> findAll() {
        return contatoRepository.findAll().stream().toList();
    }

    @Override
    public ContatoEntity findById(String id) {
        return contatoRepository.findById(new ObjectId(id));
    }

    @Override
    public void save(ContatoEntity persona) {
        contatoRepository.persist(persona);
    }

    @Override
    public ContatoEntity update(String id, ContatoEntity entity) {
        return null;
    }

    @Override
    public void delete(String id) {
        var contato = findById(id);
        contatoRepository.delete(contato);
    }
}
