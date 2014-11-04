/*
 * To change this template, choose Tools | Templates
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
import com.una.adm.model.Despesa;
import com.una.adm.model.Proprietario;
import com.una.adm.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Daniel Lanna
 */
@Path("/apartamento")
public class ControllerApartamento {

    ApartamentoFacade facadeApartamento = new ApartamentoFacade();
    AdmUtil admUtil = new AdmUtil();
    @Context
    private HttpServletRequest request;

    /**
     ******************************************************************************************************************
     * @param apartamento
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response getApartamento(@Context UriInfo info) {
        try {
            String idApart = info.getQueryParameters().getFirst("idApart");
            if (idApart != null && !idApart.equals("")) {
                return obterObjetoApart(Integer.valueOf(idApart));
            }
            String json = null;
            Usuario usu = new Usuario();
            usu = admUtil.obterUsuarioLogado(request);
            Collection<Apartamento> lCollApartamento = facadeApartamento.obterTodos(usu.getCondominio().getIdCond());
            for (Apartamento apart : lCollApartamento) {
                apart.setDespesa(null);
                if (apart.getProprietario() != null) {
                    apart.getProprietario().setCondominio(null);
                    apart.getProprietario().setApartamento(null);
                }
                apart.setBloco(null);
            }
            json = new Gson().toJson(lCollApartamento);
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
    public Response obterObjetoApart(@PathParam("id") int id) {
        try {
            String json = "{}";
            Apartamento lApart = new Apartamento();
            lApart = facadeApartamento.obter(id);
            lApart.setDespesa(null);
            lApart.getProprietario().setCondominio(null);
            lApart.getProprietario().setApartamento(null);
            lApart.getBloco().setApartamento(null);
            lApart.getBloco().setCondominio(null);
            lApart.getBloco().setDespesa(null);
            json = new Gson().toJson(lApart);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response inserirAp(String ap) throws Exception {
        try {
            String aux = ap;
            Long idBloco = Long.valueOf(aux.substring(21, 23));
            Long idProp = Long.valueOf(aux.substring(48, 50));
            Bloco lBloco = new BlocoFacade().obter(idBloco);
            Proprietario lProp = new ProprietarioFacade().obter(idProp);
            lProp.setApartamento(null);
            lProp.setCondominio(null);
            lBloco.setApartamento(null);
            lBloco.setCondominio(null);
            lBloco.setDespesa(null);
            aux = "{";
            aux += ap.substring(53);
            Apartamento apartamento = new Gson().fromJson(aux, Apartamento.class);
            apartamento.setBloco(lBloco);
            apartamento.setProprietario(lProp);
            facadeApartamento.incluir(apartamento);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response alterarApartamento(String apartamento) {
        try {
            String json = "{}";
            Apartamento apart = new Gson().fromJson(apartamento, Apartamento.class);
            if (apart.getDespesa() == null) {
                List<Despesa> lListDesp = new ArrayList<Despesa>();
                apart.setDespesa(lListDesp);
            }
            facadeApartamento.alterar(apart);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response excluirApartamento(@PathParam("id") int id) {
        try {
            String json = "{}";
            facadeApartamento.excluir(id);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DELETE
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response excluirApart(@Context UriInfo info) {
        try {
            String idApart = info.getQueryParameters().getFirst("idApart");
            int id = Integer.valueOf(idApart);
            String json = "{}";
            facadeApartamento.excluir(id);
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

    @GET
    @Path("/destino/alvo")
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response obterBlocosApartamento(@QueryParam("destino") int destino) {
        try {
            String json;
            Usuario usu = new Usuario();
            usu = admUtil.obterUsuarioLogado(request);
            Collection<Bloco> resultado2 = new BlocoFacade().obterTodos(usu.getCondominio().getIdCond());
            Collection<Apartamento> lCollAp = new ArrayList<Apartamento>();
            for (Bloco bloco : resultado2) {
                Apartamento ap = new Apartamento();
                bloco.setApartamento(null);
                bloco.setCondominio(null);
                bloco.setDespesa(null);
                ap.setBloco(bloco);
                lCollAp.add(ap);
            }
            json = new Gson().toJson(resultado2);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
