/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.facade;

import com.una.adm.model.Apartamento;
import com.una.adm.model.DAO.ApartamentoDAO;
import java.util.Collection;

/**
 *
 * @author Daniel Lanna
 */
public class ApartamentoFacade {

    ApartamentoDAO apartamentoDAO = new ApartamentoDAO();

    public void incluir(Apartamento apartamento) {
        apartamentoDAO.incluirApartamento(apartamento);
    }

    public void excluir(Apartamento apartamento) {
        apartamentoDAO.excluir(apartamento);
    }

    public void alterar(Apartamento apartamento) {
        apartamentoDAO.alterar(apartamento);
    }

    public Apartamento obter(int id) {
        return apartamentoDAO.obter(id);
    }

    public Collection<Apartamento> obterTodos(int idCond) {
        return apartamentoDAO.obterTodos(idCond);
    }
}
