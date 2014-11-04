/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.facade;

import com.una.adm.model.Bloco;
import com.una.adm.model.DAO.BlocoDAO;
import java.util.Collection;

/**
 *
 * @author Daniel Lanna
 */
public class BlocoFacade {

    BlocoDAO blocoDAO = new BlocoDAO();

    public void incluirBloco(Bloco bloco) {
        blocoDAO.incluirBloco(bloco);
    }

    public void excluir(Long id) {
        blocoDAO.excluir(id);
    }

    public void alterar(Bloco bloco) {
        blocoDAO.alterar(bloco);
    }

    public Bloco obter(Long id) {
        return blocoDAO.obter(id);
    }

    public Collection<Bloco> obterTodos(int idCond) {
        return blocoDAO.obterTodos(idCond);
    }

    public Collection<Bloco> obterBlocosPorIdCond(Bloco bloco) {
        return blocoDAO.obterBlocosPorIdCond(bloco);
    }
}
