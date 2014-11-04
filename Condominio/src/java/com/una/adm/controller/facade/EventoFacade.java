/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.facade;

import com.una.adm.model.DAO.EventoDAO;
import com.una.adm.model.EventoMobile;
import java.util.Collection;

/**
 *
 * @author Daniel Lanna
 */
public class EventoFacade {

    EventoDAO eventoDAO = new EventoDAO();

    public void incluir(EventoMobile pEvento) {
        eventoDAO.incluir(pEvento);
    }

    public void excluir(EventoMobile pEvento) {
        eventoDAO.excluir(pEvento);
    }

    public void alterar(EventoMobile pEvento) {
        eventoDAO.alterar(pEvento);
    }

    public EventoMobile obter(Long id) {
        return eventoDAO.obterEvento(id);
    }

    public Collection<EventoMobile> obterEventoPorProprietario(EventoMobile pEvento) {
        return eventoDAO.obterEventosProprietario(pEvento);
    }

    public Collection<EventoMobile> obterEventoPorCondominio(EventoMobile pEvento) {
        return eventoDAO.obterEventosCondominio(pEvento);
    }

}
