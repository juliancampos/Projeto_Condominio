/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.RN;

import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.model.Condominio;
import com.una.adm.model.DAO.CondominioDAO;
import com.una.adm.model.Usuario;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Daniel Lanna
 */
public class CondominioRN {

    CondominioDAO condominioDAO = new CondominioDAO();
    AdmUtil admUtil = new AdmUtil();

    public void incluir(Condominio condominio, Usuario usu, HttpServletRequest request) {
        try {
            condominioDAO.incluir(condominio, usu);
            usu.setCondominio(condominio);
            admUtil.inserirCondominioSessao(request, usu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id, Usuario pUsu, HttpServletRequest request) {
        try {
            condominioDAO.excluir(id);
            pUsu.setCondominio(null);
            admUtil.inserirCondominioSessao(request, pUsu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar(Condominio condominio, Usuario pUsu, HttpServletRequest request) {
        try {
            condominioDAO.alterar(condominio);
            pUsu.setCondominio(condominio);
            admUtil.inserirCondominioSessao(request, pUsu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Condominio obter(int id, Usuario pUsu, HttpServletRequest request) {
        try {
            pUsu.setCondominio(condominioDAO.obter(id));
            admUtil.inserirCondominioSessao(request, pUsu);
            return pUsu.getCondominio();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
