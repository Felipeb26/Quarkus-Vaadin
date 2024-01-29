package com.felipes.test.backend.service;

import com.felipes.test.backend.domain.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {
    List<PersonaEntity> findAll();

    PersonaEntity findById(String id);

    void save(PersonaEntity persona);

    PersonaEntity update(String id, PersonaEntity entity);

    void delete(String id);
}
