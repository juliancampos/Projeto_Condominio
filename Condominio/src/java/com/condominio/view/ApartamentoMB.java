/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.condominio.view;

import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.controller.facade.ApartamentoFacade;
import com.una.adm.controller.facade.BlocoFacade;
import com.una.adm.controller.facade.ProprietarioFacade;
import com.una.adm.model.Apartamento;
import com.una.adm.model.Bloco;
import com.una.adm.model.Proprietario;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Daniel Lanna
 */
@ManagedBean(name = "apartamento")
@ViewScoped
public class ApartamentoMB implements Serializable {

    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    ApartamentoFacade facadeApartamento = new ApartamentoFacade();
    BlocoFacade facadeBloco = new BlocoFacade();
    ProprietarioFacade facadeProprietario = new ProprietarioFacade();
    AdmUtil admUtil = new AdmUtil();

    private int idApart;
    private String obsApart;
    private int numApart;
    private Bloco bloco;
    private Proprietario proprietario;

    private boolean isEditar = false;

    private boolean isCadastrar = false;

    private boolean isListar = false;

    public boolean isIsCadastrar() {
        return isCadastrar;
    }

    public void setIsCadastrar(boolean isCadastrar) {
        this.isCadastrar = isCadastrar;
    }

    public boolean isIsListar() {
        return isListar;
    }

    public void setIsListar(boolean isListar) {
        this.isListar = isListar;
    }

    private List<Proprietario> proprietarios;

    public List<Proprietario> getProprietarios() {
        return proprietarios;
    }

    public void setProprietarios(List<Proprietario> proprietarios) {
        this.proprietarios = proprietarios;
    }

    private List<Bloco> blocos;

    public List<Bloco> getBlocos() {
        return blocos;
    }

    public void setBlocos(List<Bloco> blocos) {
        this.blocos = blocos;
    }

    private List<Apartamento> apartamentos;

    public List<Apartamento> getApartamentos() {
        return apartamentos;
    }

    public void setApartamentos(List<Apartamento> apartamentos) {
        this.apartamentos = apartamentos;
    }

    private Apartamento apart;

    public Apartamento getApart() {
        return apart;
    }

    public void setApart(Apartamento apart) {
        this.apart = apart;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public int getNumApart() {
        return numApart;
    }

    public void setNumApart(int numApart) {
        this.numApart = numApart;
    }

    public int getIdApart() {
        return idApart;
    }

    public void setIdApart(int idApart) {
        this.idApart = idApart;
    }

    public String getObsApart() {
        return obsApart;
    }

    public void setObsApart(String obsApart) {
        this.obsApart = obsApart;
    }

    public boolean isIsEditar() {
        return isEditar;
    }

    public void setIsEditar(boolean isEditar) {
        this.isEditar = isEditar;
    }

    @PostConstruct
    public void init() {
        try {
            apart = new Apartamento();
            Collection<Apartamento> lCollApart = facadeApartamento.obterTodos(admUtil.obterUsuarioLogado(request).getCondominio().getIdCond());
            for (Apartamento apart : lCollApart) {
                apart.setDespesa(null);
            }
            List<Proprietario> lCollProp = (List<Proprietario>) facadeProprietario.obterTodos(admUtil.obterUsuarioLogado(request).getCondominio().getIdCond());
            for (Proprietario prop : lCollProp) {
                prop.setApartamento(null);
                prop.setCondominio(null);
                prop.setEvento(null);
            }
            List<Bloco> lCollBloco = (List<Bloco>) facadeBloco.obterTodos(admUtil.obterUsuarioLogado(request).getCondominio().getIdCond());
            for (Bloco bloc : lCollBloco) {
                bloc.setApartamento(null);
                bloc.setCondominio(null);
                bloc.setDespesa(null);
            }
            setApartamentos((List<Apartamento>) lCollApart);
            setBlocos(lCollBloco);
            setProprietarios(lCollProp);
        } catch (Exception e) {
            Logger.getLogger(ApartamentoMB.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void salvar() throws Exception {
        FacesMessage message = null;
//        Usuario usu = admUtil.obterUsuarioLogado(request);
        try {
            if (isIsEditar()) {
                facadeApartamento.alterar(getApart());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Apartamento Alterado com Sucesso!", getApart().getObsApart());
            } else {
                facadeApartamento.incluir(getApart());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Apartamento Inserido com Sucesso!", getApart().getObsApart());
            }
            FacesContext.getCurrentInstance().addMessage("Deu Certo!", message);
            apart = new Apartamento();
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro!", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("Erro!", message);
            throw new Exception(ex.getMessage());
        }
    }

    public void editar(Apartamento pApart) {
        setIsEditar(true);
        setApart(pApart);
    }

    public void excluir(Apartamento pApart) throws Exception {
        FacesMessage message = null;
        try {
            facadeApartamento.excluir(pApart);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Apartamento Excluído com Sucesso!", obsApart);
            FacesContext.getCurrentInstance().addMessage("Deu Certo!", message);
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro!", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("Erro!", message);
            throw new Exception(ex.getMessage());
        }
    }
}
