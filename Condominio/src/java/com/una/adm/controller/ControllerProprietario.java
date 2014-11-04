/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller;

import com.google.gson.Gson;
import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.controller.criptografia.Hash;
import com.una.adm.controller.facade.ProprietarioFacade;
import com.una.adm.model.Apartamento;
import com.una.adm.model.Proprietario;
import com.una.adm.model.Usuario;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
@Path("/proprietario")
public class ControllerProprietario {

    ProprietarioFacade facade = new ProprietarioFacade();
    ProprietarioFacade facadeProprietario = new ProprietarioFacade();
    AdmUtil admUtil = new AdmUtil();
    @Context
    private HttpServletRequest request;

    /**
     ******************************************************************************************************************
     * @param proprietario
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response getProprietarios() {
        try {
            String json = null;
            Usuario usu = new Usuario();
            usu = admUtil.obterUsuarioLogado(request);
            Collection<Proprietario> lCollProprietario = facadeProprietario.obterTodos(usu.getCondominio().getIdCond());
            for (Proprietario prop : lCollProprietario) {
                for (Apartamento apart : prop.getApartamento()) {
                    apart.setBloco(null);
                    apart.setDespesa(null);
                    apart.setProprietario(null);
                }
                prop.setCondominio(null);
                prop.setSenha(null);
            }
            json = new Gson().toJson(lCollProprietario);
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
    public Response obterProprietario(@PathParam("id") Long id) {
        try {
            String json = "{}";
            Proprietario lProp = new Proprietario();
            lProp = facadeProprietario.obter(id);
            lProp.setApartamento(null);
            lProp.setCondominio(null);
            json = new Gson().toJson(lProp);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response inserirProp(String prop) throws Exception {
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            Proprietario proprietario = new Gson().fromJson(prop, Proprietario.class);
            proprietario.setSenha(new Hash().hash(proprietario.getCpf().getBytes()));
            proprietario.setCondominio(usu.getCondominio());
            facadeProprietario.incluirProprietario(proprietario);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response alterarProprietario(String prop) {
        try {
            String json = "{}";
            Proprietario proprietario = new Gson().fromJson(prop, Proprietario.class);
            Proprietario proprietarioAux = facadeProprietario.obter(proprietario.getId());
            proprietario.setApartamento(proprietarioAux.getApartamento());
            proprietario.setCondominio(admUtil.obterCondominioUsuario(request));
            facadeProprietario.alterar(proprietario);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response excluirProprietario(@PathParam("id") Long id) {
        try {
            String json = "{}";
            facadeProprietario.excluir(id);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
