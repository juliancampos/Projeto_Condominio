package com.condominio.view;

import com.una.adm.controller.facade.ProprietarioFacade;
import com.una.adm.model.Proprietario;
import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.model.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "proprietario")
public class ProprietarioMB {

    ProprietarioFacade facadeProp;
    Proprietario proprietario;
    AdmUtil admUtil = new AdmUtil();

    private Long id;
    private String nome;
    private String cpf;
    private String senha;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
    private Long cel;
    private Long tel;
    //private List<Apartamento> apartamento;
    //private Condominio condominio;
    //private List<EventoMobile> evento;

    public String Cadastrar() {
        FacesMessage message = null;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Usuario usu = null;

        try {
            usu = admUtil.obterUsuarioLogado(request);
            //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        } catch (Exception ex) {
            Logger.getLogger(BlocoMB.class.getName()).log(Level.SEVERE, null, ex);
        }

        proprietario = new Proprietario();
        proprietario.setNome(nome);
        proprietario.setCpf(this.cpf);
        proprietario.setTel(this.tel);
        proprietario.setCel(this.cel);
        proprietario.setEmail(this.email);
        proprietario.setCondominio(usu.getCondominio());

        try {
            facadeProp = new ProprietarioFacade();
            facadeProp.incluirProprietario(proprietario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proprietario Incluído com Sucesso!", this.nome);
            FacesContext.getCurrentInstance().addMessage("Deu Certo!", message);
            return "/restrito/home.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            ex.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", ex.getMessage().toString());
            FacesContext.getCurrentInstance().addMessage("Erro!", message);

        }
        //    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível incluir bloco!!", this.NomeBloco);
        FacesContext.getCurrentInstance().addMessage("Erro!", message);
        return "";

    }

}
