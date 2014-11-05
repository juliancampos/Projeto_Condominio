/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.facade;

import com.una.adm.controller.RN.CondominioRN;
import com.una.adm.model.Condominio;
import com.una.adm.model.DAO.CondominioDAO;
import com.una.adm.model.Usuario;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Daniel Lanna
 */
public class CondominioFacade {

    CondominioDAO condominioDAO = new CondominioDAO();
    CondominioRN condominioRN = new CondominioRN();

    public void incluir(Condominio condominio, Usuario pUsu, HttpServletRequest request) {
        condominioRN.incluir(condominio, pUsu, request);
    }

    public void excluir(int id, Usuario pUsu, HttpServletRequest request) {
        condominioRN.excluir(id, pUsu, request);
    }

    public void alterar(Condominio condominio, Usuario pUsu, HttpServletRequest request) {
        condominioRN.alterar(condominio, pUsu, request);
    }

    public Condominio obter(int id, Usuario pUsu, HttpServletRequest request) {
        return condominioRN.obter(id, pUsu, request);
    }
}
