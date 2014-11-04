package com.condominio.view;

import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.controller.facade.BlocoFacade;
import com.una.adm.model.Bloco;
import com.una.adm.model.Condominio;
import com.una.adm.model.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "bloco")
public class BlocoMB {

    BlocoFacade blocoFacade = new BlocoFacade();
    AdmUtil admUtil = new AdmUtil();

    private Long idBloco;
    private String NomeBloco;
    private String obsBloco;
    private Condominio condominio;

    private HttpServletRequest request;

    public Long getIdBloco() {
        return idBloco;
    }

    public void setIdBloco(Long idBloco) {
        this.idBloco = idBloco;
    }

    public String getNomeBloco() {
        return NomeBloco;
    }

    public void setNomeBloco(String NomeBloco) {
        this.NomeBloco = NomeBloco;
    }

    public String getObsBloco() {
        return obsBloco;
    }

    public void setObsBloco(String obsBloco) {
        this.obsBloco = obsBloco;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public String Cadastrar() {

        Bloco bloco;
        FacesMessage message = null;
        Usuario usu = null;

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            usu = admUtil.obterUsuarioLogado(request);
            //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        } catch (Exception ex) {
            Logger.getLogger(BlocoMB.class.getName()).log(Level.SEVERE, null, ex);
        }

        //usu = (Usuario) session.getAttribute("Usuario");
        bloco = new Bloco();
        bloco.setNomeBloco(this.NomeBloco);
        bloco.setObsBloco(this.obsBloco);
        bloco.setCondominio(this.condominio);
        bloco.setCondominio(usu.getCondominio());

        try {
            blocoFacade.incluirBloco(bloco);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bloco Incluído com Sucesso!", this.NomeBloco);
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
