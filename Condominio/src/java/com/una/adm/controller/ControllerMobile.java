/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller;

import com.google.gson.Gson;
import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.controller.facade.EventoFacade;
import com.una.adm.controller.facade.ProprietarioFacade;
import com.una.adm.model.Apartamento;
import com.una.adm.model.Condominio;
import com.una.adm.model.EventoMobile;
import com.una.adm.model.Proprietario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel Lanna
 */
@Path("/mobile")
public class ControllerMobile {

    ProprietarioFacade facade = new ProprietarioFacade();
    ProprietarioFacade facadeProprietario = new ProprietarioFacade();
    EventoFacade facadeEvento = new EventoFacade();
    AdmUtil admUtil = new AdmUtil();
    @Context
    private HttpServletRequest request;

    @POST
    @Path("/proprietario")
    public Response obterProprietario(String prop) {
        try {
            Proprietario proprietario = new Gson().fromJson(prop, Proprietario.class);
            proprietario = facadeProprietario.obterProprietarioMobile(proprietario.getCpf());
            if (proprietario != null) {
                for (Apartamento lApart : proprietario.getApartamento()) {
                    lApart.setObsApart(null);
                    lApart.setProprietario(null);
                    lApart.setBloco(null);
                    lApart.setDespesa(null);
                }
                proprietario.setCondominio(new Condominio(proprietario.getCondominio().getIdCond()));
                for (EventoMobile lCollEvento : proprietario.getEvento()) {
                    lCollEvento.setCondominio(null);
                    lCollEvento.setProprietario(null);
                }
                String propJson = new Gson().toJson(proprietario);
                new AdmUtil().inserirProprietarioSessaoMobile(request, proprietario);
                return Response.status(Response.Status.OK).entity(propJson).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/evento")
    public Response obterEventos(String event) {
        try {
            Proprietario prop = new AdmUtil().obterProprietarioSessaoMobile(request);
//            EventoMobile lEvento = new Gson().fromJson(event, EventoMobile.class);
            EventoMobile lEvento = new EventoMobile();
            lEvento.setCondominio(prop.getCondominio());
            Collection<EventoMobile> lCollEvento = new ArrayList<EventoMobile>();
            lCollEvento = facadeEvento.obterEventoPorCondominio(lEvento);
            if (!lCollEvento.isEmpty()) {
                for (EventoMobile lEventos : lCollEvento) {
                    DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
                    String lDataInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lEventos.getDataInicio());
                    Date lDataInicioFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(lDataInicio);
                    String lDataFim = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lEventos.getDataInicio());
                    Date lDataFimFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(lDataFim);
                    lEventos.setDataInicio(lDataInicioFormatada);
                    lEventos.setDataFim(lDataFimFormatada);
                    lEventos.setCondominio(null);
                    lEventos.setProprietario(null);
                }
                String lEventos = new Gson().toJson(lCollEvento);
                return Response.status(Response.Status.OK).entity(lEventos).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/evento")
    public Response inserirEvento(String event) {
        try {
            Proprietario prop = new AdmUtil().obterProprietarioSessaoMobile(request);
            EventoMobile evento = new Gson().fromJson(event, EventoMobile.class);
            evento.setCondominio(new Condominio(prop.getCondominio().getIdCond()));
            evento.setProprietario(new Proprietario(prop.getId()));
            facadeEvento.incluir(evento);
            return Response.status(Response.Status.OK).entity(event).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
