/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller;

import com.google.gson.Gson;
import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.controller.facade.ApartamentoFacade;
import com.una.adm.controller.facade.BlocoFacade;
import com.una.adm.controller.facade.ProprietarioFacade;
import com.una.adm.model.Apartamento;
import com.una.adm.model.Bloco;
import com.una.adm.model.Proprietario;
import com.una.adm.model.Usuario;
import com.una.adm.model.Util;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Daniel Lanna
 */
@Path("/util")
public class ControllerUtil {

    ApartamentoFacade facadeApartamento = new ApartamentoFacade();
    ProprietarioFacade facadeProprietario = new ProprietarioFacade();
    BlocoFacade facadeBloco = new BlocoFacade();
    AdmUtil admUtil = new AdmUtil();
    @Context
    private HttpServletRequest request;

    /**
     * @return @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response getApartamento(@Context UriInfo info) {
        try {
            String identity = info.getQueryParameters().getFirst("destino");
            String json = null;
            Usuario usu = new Usuario();
            Util util = new Util();
            usu = admUtil.obterUsuarioLogado(request);
            if (identity != null && identity.equals("apartamento")) {
                Collection<Bloco> lCollBloco = facadeBloco.obterTodos(usu.getCondominio().getIdCond());
                Collection<Proprietario> lCollProprietario = facadeProprietario.obterTodos(usu.getCondominio().getIdCond());
                for (Bloco bloco : lCollBloco) {
                    bloco.setApartamento(null);
                    bloco.setCondominio(null);
                    bloco.setDespesa(null);
                }
                for (Proprietario proprietario : lCollProprietario) {
                    proprietario.setCondominio(null);
                    proprietario.setApartamento(null);
                }

                util.setBloco(lCollBloco);
                util.setProprietario(lCollProprietario);
                Collection<Util> lCollUtil = new ArrayList<Util>();
                lCollUtil.add(util);
                json = new Gson().toJson(lCollUtil);
//                String teste = new Gson().toJson(lCollProprietario);
//                json.concat(teste);
//                json += new Gson().toJson(lCollProprietario);
//                json += "}";

                return Response.status(Response.Status.OK).entity(json).build();

            }
            Collection<Apartamento> lCollApartamento = facadeApartamento.obterTodos(usu.getCondominio().getIdCond());
//            for (Apartamento apart : lCollApartamento) {
//                apart.setDespesa(null);
//                apart.setProprietario(null);
//                apart.setBloco(null);
//            }
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GET
    @Path("/{destino}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response getTodos(@PathParam("destino") String destino, @Context UriInfo info) {
        try {
            String identity = info.getQueryParameters().getFirst("destino");
            String json = null;
            Usuario usu = new Usuario();
            Util util = new Util();
            usu = admUtil.obterUsuarioLogado(request);
//            Collection<Apartamento> lCollApartamento = facadeApartamento.obterTodos(usu.getCondominio().getIdCond());
            Collection<Bloco> lCollBloco = facadeBloco.obterTodos(usu.getCondominio().getIdCond());
            Collection<Proprietario> lCollProprietario = facadeProprietario.obterTodos(usu.getCondominio().getIdCond());
            for (Bloco bloco : lCollBloco) {
                bloco.setApartamento(null);
                bloco.setCondominio(null);
                bloco.setDespesa(null);
            }
//            for (Apartamento apart : lCollApartamento) {
//                apart.setDespesa(null);
//                apart.setProprietario(null);
//                apart.setBloco(null);
//            }
            util.setBloco(lCollBloco);
            util.setProprietario(lCollProprietario);
            json = new Gson().toJson(lCollBloco);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
