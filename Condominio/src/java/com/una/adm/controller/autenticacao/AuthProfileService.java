/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.autenticacao;

import com.google.gson.Gson;
import com.una.adm.controller.criptografia.Hash;
import com.una.adm.model.DAO.UsuarioDAO;
import com.una.adm.model.Usuario;
import java.util.Enumeration;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Daniel Lanna
 */
@ManagedBean(name = "login")
public class AuthProfileService {

    @Context
    HttpServletRequest request;
    HttpSession sessao;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        UserProfile profile = new UserProfile();
        FacesMessage message = null;
        // faz a consulta no bd
        Hash senhaHash = new Hash();
        Usuario usuario = new Usuario();
        usuario.setEmail(getUsername());
        usuario.setSenha(senhaHash.hash(getPassword().getBytes()));
        try {
            Usuario lusu = new UsuarioDAO().obterUsuarioLogin(usuario);
            usuario = lusu;
            if (lusu != null) {
                profile.setRole(Role.ADMIN);
            } else {
                profile = null;
            }
            if (profile != null) {
                RequestContext context = RequestContext.getCurrentInstance();
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                // Por segurança a senha nunca é retornada para o cliente.....
                profile.setPassword("");
                session.setAttribute("userProfile", profile);
                session.setAttribute("Usuario", usuario);
                if (usuario.getCondominio() != null) {
                    session.setAttribute("Condominio", usuario.getCondominio());
                }
                session.setMaxInactiveInterval(-1);
                setSessao(session);
                // An interval value of zero or less indicates that the session should never timeout
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo!", username);
                FacesContext.getCurrentInstance().addMessage("Deu Certo!", message);
                return "/restrito/home.xhtml?faces-redirect=true";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Deu Erro!", ex.getCause().toString());
            FacesContext.getCurrentInstance().addMessage("Deu Erro!", message);
        }
        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou Senha Inválidos!", "Digite Novamente");
        FacesContext.getCurrentInstance().addMessage("Deu Erro!", message);
        return "";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorization() {
        HttpSession session = request.getSession(false);
        boolean validated = true;
        UserProfile profile = null;

        if (session == null) {
            validated = false;
        } else {
            profile = (UserProfile) session.getAttribute("userProfile");
            if (profile == null) {
                validated = false;
            }
        }

        if (!validated) {
            profile = new UserProfile();
            profile.setUserName("");
            profile.setPassword("");
            profile.setRole(Role.ANONIMO);
        }

        String status = new Gson().toJson(profile);
        return Response.status(Response.Status.OK).entity(status).build();
    }

    @DELETE
    public Response logout() {
        HttpSession session = request.getSession(false);
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            session.removeAttribute(attributeNames.nextElement());
        }
        session.invalidate();
        return Response.status(Response.Status.OK).build();
    }

    public HttpSession getSessao() {
        return sessao;
    }

    public void setSessao(HttpSession pSession) {
        sessao = pSession;
    }
}
