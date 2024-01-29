package com.felipes.test.backend.controller;

import com.felipes.test.backend.domain.entity.ContatoEntity;
import com.felipes.test.backend.service.ContatoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/v1/contato")
public class ContatoController {
    
    @Inject
    private ContatoService contatoService;

    @GET
    public List<ContatoEntity> findAll() {
        return contatoService.findAll();
    }

    @GET
    @Path("/{id}")
    public ContatoEntity findById(@PathParam("id") String id) {
        return contatoService.findById(id);
    }

    @POST
    public void savePersona(ContatoEntity persona) {
        contatoService.save(persona);
    }

    @PUT
    public ContatoEntity updadePersona(@PathParam("id") String id,ContatoEntity entity) {
        return contatoService.update(id, entity);
    }

    @DELETE
    public RestResponse<String> deletePersona(@PathParam("id") String id) {
        contatoService.delete(id);
        return RestResponse.accepted();
    }

}
