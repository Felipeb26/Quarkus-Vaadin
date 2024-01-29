package com.felipes.test.backend.controller;

import com.felipes.test.backend.domain.entity.TransferenciaEntity;
import com.felipes.test.backend.service.TransferenciaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/v1/transferencia")
public class TransferenciaController {

    @Inject
    private TransferenciaService transferenciaService;

    @GET
    public List<TransferenciaEntity> findAll() {
        return transferenciaService.findAll();
    }

    @GET
    @Path("/{id}")
    public TransferenciaEntity findById(@PathParam("id") String id) {
        return transferenciaService.findById(id);
    }

    @POST
    public void saveTransferencia(TransferenciaEntity persona) {
        transferenciaService.save(persona);
    }

    @PUT
    public TransferenciaEntity updateTransferencia(@PathParam("id") String id,TransferenciaEntity entity) {
        return transferenciaService.update(id, entity);
    }

    @DELETE
    public RestResponse<String> deleteTransferencia(@PathParam("id") String id) {
        transferenciaService.delete(id);
        return RestResponse.accepted();
    }


}
