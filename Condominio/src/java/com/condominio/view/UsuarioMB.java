package com.condominio.view;

import com.una.adm.controller.RN.UsuarioRN;
import com.una.adm.controller.criptografia.Hash;
import com.una.adm.model.Condominio;
import com.una.adm.model.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "usuario")
public class UsuarioMB {
    
    private int id;
    private String nome;
    private String senha;
    private String email;
    private Long cel;
    private Long tel;
    private Condominio condominio;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Long getCel() {
        return cel;
    }
    
    public void setCel(Long cel) {
        this.cel = cel;
    }
    
    public Long getTel() {
        return tel;
    }
    
    public void setTel(Long tel) {
        this.tel = tel;
    }
    
    public Condominio getCondominio() {
        return condominio;
    }
    
    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
    
    public String Cadastrar() {
        Usuario usuario = new Usuario();
        FacesMessage message = null;
        // faz a consulta no bd
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setTel(this.tel);
        usuario.setCel(this.cel);
        usuario.setSenha(new Hash().hash(getSenha().getBytes()));
        
        try {
            UsuarioRN usuRN = new UsuarioRN();
            usuRN.incluir(usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário Incluído com Sucesso!", nome);
            FacesContext.getCurrentInstance().addMessage("Deu Certo!", message);
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            ex.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", ex.getMessage().toString());
            FacesContext.getCurrentInstance().addMessage("Erro!", message);
        }
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível incluir usuário!!", nome);
        FacesContext.getCurrentInstance().addMessage("Erro!", message);
        return "";
    }
}
