/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller;

import com.google.gson.Gson;
import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.controller.facade.CondominioFacade;
import com.una.adm.model.Condominio;
import com.una.adm.model.Usuario;
import java.util.ArrayList;
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
@Path("/condominio")
public class ControllerCondominio {

    CondominioFacade facadeCondominio = new CondominioFacade();
    AdmUtil admUtil = new AdmUtil();
    @Context
    private HttpServletRequest request;

    /**
     * *****************************************************************************************************************
     * @param condominio
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response getCondominio() {
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            if (usu != null && usu.getCondominio() != null) {
                usu.getCondominio().setCondominio_bloco(null);
                usu.getCondominio().setCondominio_despesa(null);
            }
            Collection<Condominio> lCollCond = new ArrayList<Condominio>();
            lCollCond.add(usu.getCondominio());
            String json = new Gson().toJson(lCollCond);
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
    public Response obterObjetoCondominio(@PathParam("id") int id) {
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            String json = "{}";
            Condominio lCondominio = new Condominio();
            lCondominio = facadeCondominio.obter(id, usu, request);
            lCondominio.setCondominio_bloco(null);
            lCondominio.setCondominio_despesa(null);
            json = new Gson().toJson(lCondominio);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response inserirCondominio(String cond) throws Exception {
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            Condominio condominio = new Gson().fromJson(cond, Condominio.class);
            facadeCondominio.incluir(condominio, usu, request);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response alterarCondominio(String condominio) {
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            String json = "{}";
            Condominio cond = new Gson().fromJson(condominio, Condominio.class);
            facadeCondominio.alterar(cond, usu, request);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response excluirCondominio(@PathParam("id") int id) {
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            String json = "{}";
            facadeCondominio.excluir(id, usu, request);
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
            if (usu == null) {
                return Response.status(Response.Status.OK).build();
            }
            return usu.getCondominio() == null ? Response.status(Response.Status.OK).build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
