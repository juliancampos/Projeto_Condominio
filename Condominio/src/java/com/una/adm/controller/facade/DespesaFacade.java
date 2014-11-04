/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.facade;

import com.una.adm.model.Apartamento;
import com.una.adm.model.Despesa;
import com.una.adm.model.DAO.DespesaDAO;
import java.util.Collection;

/**
 *
 * @author Daniel Lanna
 */
public class DespesaFacade {

    DespesaDAO despesaDAO = new DespesaDAO();

    public void incluir(Despesa despesa) {
        despesaDAO.incluirDespesa(despesa);
    }

    public void excluir(int id) {
        despesaDAO.excluir(id);
    }

    public void alterar(Despesa despesa) {
        despesaDAO.alterar(despesa);
    }

    public Despesa obter(int id) {
        return despesaDAO.obter(id);
    }

    public Collection<Despesa> obterTodos(int idCond) {
        return despesaDAO.obterTodos(idCond);
    }
}
