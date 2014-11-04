/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller;

import com.google.gson.Gson;
import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.controller.boleto.GerarBoleto;
import com.una.adm.controller.facade.ApartamentoFacade;
import com.una.adm.controller.facade.BlocoFacade;
import com.una.adm.controller.facade.BoletoFacade;
import com.una.adm.controller.facade.DespesaFacade;
import com.una.adm.model.Apartamento;
import com.una.adm.model.Bloco;
import com.una.adm.model.BoletoModel;
import com.una.adm.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel Lanna
 */
@Path("/boleto")
public class ControllerBoleto {

    BoletoFacade facadeBoleto = new BoletoFacade();
    DespesaFacade facadeDespesa = new DespesaFacade();
    AdmUtil admUtil = new AdmUtil();
    @Context
    private HttpServletRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response getValor() {
        try {
            Collection<BoletoModel> lBoletos = new ArrayList<BoletoModel>();

            Usuario usu = admUtil.obterUsuarioLogado(request);
            Bloco lBloco = new Bloco();
            lBloco.setCondominio(usu.getCondominio());
            Collection<Bloco> lBlocos = new BlocoFacade().obterBlocosPorIdCond(lBloco);
            for (Bloco bloco : lBlocos) {
                if (!bloco.getDespesa().isEmpty()) {
                    BoletoModel lBoleto = new BoletoModel();
                    lBoleto.setBloco(bloco);
                    lBoletos.add(lBoleto);
                }
            }
//            BoletoModel lBoleto = facadeBoleto.obterValorDespesaCondominio(usu.getCondominio());
//            for (Bloco bloco : lBlocos) {
//                lBoleto = facadeBoleto.obterValorDespesaBloco(usu.getCondominio(), bloco);
//                lBoletos.add(lBoleto);
//            }
            Collection<Apartamento> lApartamentos = new ApartamentoFacade().obterTodos(usu.getCondominio().getIdCond());
            for (Apartamento apartamento : lApartamentos) {
                if (!apartamento.getDespesa().isEmpty()) {
                    BoletoModel lBoleto = new BoletoModel();
                    lBoleto.setApartamento(apartamento);
                    lBoletos.add(lBoleto);
                }
            }
            String json = new Gson().toJson(lBoletos);
            new GerarBoleto().gerarBoleto();
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "MANAGEMENT"})
    public Response gerarBoleto() {
        String json = "{}";
        new GerarBoleto().gerarBoleto();
        return Response.status(Response.Status.OK).entity(json).build();
    }

}
