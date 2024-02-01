package com.felipes.test.backend.service.impl;

import com.felipes.test.backend.domain.entity.ContatoEntity;
import com.felipes.test.backend.repository.ContatoRepository;
import com.felipes.test.backend.service.ContatoService;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class ContatoServiceImpl implements ContatoService {

    @Inject
    ContatoRepository contatoRepository;

    @CacheResult(cacheName = "CONTATOS")
    @Override
    public List<ContatoEntity> findAll() {
        return contatoRepository.findAll().stream().toList();
    }

    @CacheResult(cacheName = "CONTATOS_ID")
    @Override
    public ContatoEntity findById(@CacheKey String id) {
        return contatoRepository.findById(new ObjectId(id));
    }

    @CacheInvalidateAll(cacheName = "CONTATOS")
    @Override
    public void save(ContatoEntity persona) {
        contatoRepository.persist(persona);
    }

    @Override
    public ContatoEntity update(String id, ContatoEntity entity) {
        return null;
    }

    @Override
    @CacheInvalidate(cacheName = "CONTATOS_ID")
    public void delete(@CacheKey String id) {
        var contato = findById(id);
        contatoRepository.delete(contato);
    }

    @CacheResult(cacheName = "CONTATOS_ID")
    @Override
    public ContatoEntity findById(@CacheKey ObjectId id) {
        return contatoRepository.findById(id);
    }

    @CacheInvalidate(cacheName = "CONTATOS_ID")
    @Override
    public void delete(@CacheKey ObjectId id) {
        var contato = findById(id);
        contatoRepository.delete(contato);
    }

}
