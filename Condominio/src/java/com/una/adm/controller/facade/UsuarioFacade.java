package com.una.adm.controller.facade;

import com.una.adm.controller.RN.UsuarioRN;
import com.una.adm.model.Usuario;
import com.una.adm.model.DAO.UsuarioDAO;
import java.util.Collection;

/**
 *
 * @author Daniel Lanna
 */
public class UsuarioFacade {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    UsuarioRN usuarioRN = new UsuarioRN();

    public void excluir(int id) {
        usuarioDAO.excluir(Long.valueOf(id));
    }

    public void alterar(Usuario usuario) {
        usuarioDAO.alterar(usuario, true);
    }

    public Usuario obter(int id) {
        return usuarioDAO.obter(Long.valueOf(id));
    }

    public Collection<Usuario> obterTodos() {
        return usuarioDAO.obterTodos();
    }

    public Usuario obterUsuarioLogin(Usuario usuario) throws Exception {
        try {

            return usuarioRN.obterUsuarioLogin(usuario);
        } catch (Exception e) {
            throw new Exception("Não Foi possível fazer o Login. Verifique seus dados se estão Corretos");
        }
    }
//    public Usuario verificaUsuarioCondominio(Usuario usuario) {
//        return usuarioDAO.verificaUsuarioCondominio("dalai");
//    }
}
