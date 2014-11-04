/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.RN;

import com.una.adm.model.Usuario;
import com.una.adm.model.DAO.UsuarioDAO;
import java.util.Collection;

/**
 *
 * @author Daniel Lanna
 */
public class UsuarioRN {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void incluir(Usuario usuario) {
        usuarioDAO.incluirUsuario(usuario);
    }

    public Usuario obterUsuarioLogin(Usuario usuario) throws Exception {
        Usuario usu = usuarioDAO.obterUsuarioLogin(usuario);
        if (usu == null) {
            throw new Exception("Não Foi possível fazer o Login. Verifique seus dados se estão Corretos");
        }
        return usu;
    }

    public Usuario obterUsuarioLogado(Usuario usuario) throws Exception {
        Usuario usu = usuarioDAO.obterUsuarioLogado(usuario);
        if (usu == null) {
            return null;
        }
        return usu;
    }
}
