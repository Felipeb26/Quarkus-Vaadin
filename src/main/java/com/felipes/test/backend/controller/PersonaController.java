package com.felipes.test.backend.controller;

import com.felipes.test.backend.domain.entity.PersonaEntity;
import com.felipes.test.backend.service.PersonaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/v1/persona")
public class PersonaController {

    @Inject
    private PersonaService personaService;


    @GET
    public List<PersonaEntity> findAll() {
        return personaService.findAll();
    }

    @GET
    @Path("/{id}")
    public PersonaEntity findById(@PathParam("id") String id) {
        return personaService.findById(id);
    }

    @POST
    public void savePersona(PersonaEntity persona) {
        personaService.save(persona);
    }

    @PUT
    public PersonaEntity updadePersona(@PathParam("id") String id,PersonaEntity entity) {
        return personaService.update(id, entity);
    }

    @DELETE
    public RestResponse<String> deletePersona(@PathParam("id") String id) {
        personaService.delete(id);
        return RestResponse.accepted();
    }

}
