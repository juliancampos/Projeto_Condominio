/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller;

import com.google.gson.Gson;
import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.controller.facade.BlocoFacade;
import com.una.adm.model.Bloco;
import com.una.adm.model.Condominio;
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
@Path("/bloco")
public class ControllerBloco {

    BlocoFacade facadeBloco = new BlocoFacade();
    AdmUtil admUtil = new AdmUtil();
    @Context
    private HttpServletRequest request;

    /**
     ******************************************************************************************************************
     * @param bloco
     * @return
     * @throws Exception
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response inserirBloco(String bloc) throws Exception {
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            Bloco bloco = new Gson().fromJson(bloc, Bloco.class);
            bloco.setCondominio(usu.getCondominio());
            facadeBloco.incluirBloco(bloco);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response getBloco() {
        try {
            String json;
            Usuario usu = new Usuario();
            usu = admUtil.obterUsuarioLogado(request);
            Bloco bloco = new Bloco();
            bloco.setCondominio(usu.getCondominio());
            Collection<Bloco> resultado3 = facadeBloco.obterBlocosPorIdCond(bloco);
            for (Bloco lBlocos : resultado3) {
                lBlocos.setDespesa(null);
                lBlocos.setApartamento(null);
                lBlocos.setCondominio(null);
            }
            json = new Gson().toJson(resultado3);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response obterObjetoBloco(@PathParam("id") Long id) {
        try {
            String json = "{}";
            Bloco lBlocos = new Bloco();
            lBlocos = facadeBloco.obter(id);
            lBlocos.setDespesa(null);
            lBlocos.setCondominio(null);
            lBlocos.setApartamento(null);
            json = new Gson().toJson(lBlocos);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response alterarBloco(String bloco) {
        try {
            Condominio cond = admUtil.obterCondominioUsuario(request);
            String json = "{}";
            Bloco bloc = new Gson().fromJson(bloco, Bloco.class);
            bloc.setCondominio(cond);
            facadeBloco.alterar(bloc);
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
    public Response excluirBloco(@PathParam("id") Long id) {
        try {
            String json = "{}";
            facadeBloco.excluir(id);
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
