package com.una.adm.controller;

import com.google.gson.Gson;
import com.una.adm.controller.Enum.TipoUsuarioEnum;
import com.una.adm.controller.RN.UsuarioRN;
import com.una.adm.controller.autenticacao.UserProfile;
import com.una.adm.controller.criptografia.Hash;
import com.una.adm.model.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuario")
public class ControllerUsuario {

    @Context
    private HttpServletRequest request;

    /**
     ******************************************************************************************************************
     * @param usuario
     * @return
     * @throws Exception
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirUsuario(String usuario) throws Exception {
        try {
            Hash senhaHash = new Hash();
            Usuario usu = new Gson().fromJson(usuario, Usuario.class);
            usu.setSenha(senhaHash.hash(usu.getSenha().getBytes()));
            new UsuarioRN().incluir(usu);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Usuario obterUsuarioLogado() throws Exception {
        UserProfile a = (UserProfile) request.getSession().getAttribute("userProfile");
        Usuario usuario = new Usuario();
        usuario.setEmail(a.getUserName());
        Usuario usu = new UsuarioRN().obterUsuarioLogado(usuario);
        if (usu == null) {
            return null;
        }
        return usu;
    }
}
