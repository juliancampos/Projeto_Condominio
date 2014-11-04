/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller;

import com.una.adm.controller.facade.DespesaFacade;
import com.google.gson.Gson;
import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.model.Despesa;
import com.una.adm.model.Usuario;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel Lanna
 */
@Path("/despesa")
public class ControllerDespesa {

    DespesaFacade facadeDespesa = new DespesaFacade();
    AdmUtil admUtil = new AdmUtil();
    @Context
    private HttpServletRequest request;

    /**
     * *****************************************************************************************************************
     * @param despesa
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response getDespesa() {
        try {
            Usuario usu = new Usuario();
            usu = admUtil.obterUsuarioLogado(request);
            Collection<Despesa> resultado3 = facadeDespesa.obterTodos(usu.getCondominio().getIdCond());
            for (Despesa desp : resultado3) {
                desp.setApartamento(null);
                desp.setBloco(null);
            }
            String json = new Gson().toJson(resultado3);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response obterObjetoDespesa(@PathParam("id") int id) {
        try {
            String json = "{}";
            Despesa obj = new Despesa();
            obj = facadeDespesa.obter(id);
            json = new Gson().toJson(obj);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response inserirDesp(String desp) throws Exception {
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            Despesa despesa = new Despesa();
            despesa = new Gson().fromJson(desp, Despesa.class);
            facadeDespesa.incluir(despesa);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response alterarDespesa(String despesa) {
        try {
            String json = "{}";
            Despesa desp = new Gson().fromJson(despesa, Despesa.class);
            facadeDespesa.alterar(desp);
            return Response.status(Response.Status.OK)
                    .entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response excluirDespesa(@PathParam("id") int id) {
        try {
            String json = "{}";
            facadeDespesa.excluir(id);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @HEAD
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response isCondominioCadastrado() {
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            return usu.getCondominio() != null ? Response.status(Response.Status.OK).build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
