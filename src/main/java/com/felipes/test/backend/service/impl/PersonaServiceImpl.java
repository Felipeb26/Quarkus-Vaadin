package com.felipes.test.backend.service.impl;

import com.felipes.test.backend.domain.entity.PersonaEntity;
import com.felipes.test.backend.repository.PersonaRepository;
import com.felipes.test.backend.service.PersonaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class PersonaServiceImpl implements PersonaService {

    @Inject
    PersonaRepository personaRepository;

    @Override
    public List<PersonaEntity> findAll() {
        return personaRepository.findAll().stream().toList();
    }

    @Override
    public PersonaEntity findById(String id) {
        return personaRepository.findById(new ObjectId(id));
    }

    @Override
    public void save(PersonaEntity persona) {
        personaRepository.persist(persona);
    }

    @Override
    public PersonaEntity update(String id, PersonaEntity entity) {
        return null;
    }

    @Override
    public void delete(String id) {
        var persona = findById(id);
        personaRepository.delete(persona);
    }
}
