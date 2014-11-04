/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.facade;

import com.una.adm.model.DAO.ProprietarioDAO;
import com.una.adm.model.Proprietario;
import java.util.Collection;

/**
 *
 * @author Daniel Lanna
 */
public class ProprietarioFacade {

    ProprietarioDAO proprietarioDAO = new ProprietarioDAO();

    public void incluirProprietario(Proprietario bloco) {
        proprietarioDAO.incluirProprietario(bloco);
    }

    public void excluir(Long id) {
        proprietarioDAO.excluir(id);
    }

    public void alterar(Proprietario bloco) {
        proprietarioDAO.alterar(bloco);
    }

    public Proprietario obter(Long id) {
        return proprietarioDAO.obter(id);
    }

    public Collection<Proprietario> obterTodos(int idCond) {
        return proprietarioDAO.obterTodos(idCond);
    }

    public Proprietario obterProprietarioMobile(String cpf) {
        return proprietarioDAO.obterProprietarioMobile(cpf);
    }

}
