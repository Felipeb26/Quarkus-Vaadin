package com.felipes.test.backend.repository;

import com.felipes.test.backend.domain.entity.PersonaEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonaRepository implements PanacheMongoRepository<PersonaEntity> {
}
