/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.condominio.view;

import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.controller.facade.CondominioFacade;
import com.una.adm.model.Condominio;
import com.una.adm.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Daniel Lanna
 */
@ManagedBean(name = "condominio")
@RequestScoped
public class CondominioMB {

    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    CondominioFacade facadeCondominio = new CondominioFacade();
    AdmUtil admUtil = new AdmUtil();

    private int idCond;
    private String NomeCond;
    private String Rua;
    private int Numero;
    private String Bairro;
    private String Cep;
    private String Cidade;
    private String Estado;
    private String conta;
    private int digitoConta;
    private String agencia;
    private int digitoAgencia;
    private String identificao;

    private List<Condominio> condominios;

    public List<Condominio> getCondominios() {
        return condominios;
    }

    public void setCondominios(List<Condominio> condominios) {
        this.condominios = condominios;
    }

    @ManagedProperty("#{cond}")
    private Condominio cond;

    private boolean isEditar = false;

    public boolean isIsEditar() {
        return isEditar;
    }

    public void setIsEditar(boolean isEditar) {
        this.isEditar = isEditar;
    }

    public Condominio getCond() {
        return cond;
    }

    public void setCond(Condominio cond) {
        this.cond = cond;
    }

    public int getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(int digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public int getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(int digito) {
        this.digitoConta = digito;
    }

    public int getIdCond() {
        return idCond;
    }

    public void setIdCond(int idCond) {
        this.idCond = idCond;
    }

    public String getNomeCond() {
        return NomeCond;
    }

    public void setNomeCond(String NomeCond) {
        this.NomeCond = NomeCond;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getIdentificao() {
        return identificao;
    }

    public void setIdentificao(String identificao) {
        this.identificao = identificao;
    }

    @PostConstruct
    public void init() {
        List<Condominio> condominio = new ArrayList<Condominio>();
        try {
            Usuario usu = admUtil.obterUsuarioLogado(request);
            Condominio condom = (Condominio) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("codom");
            if (condom != null) {
                setIsEditar(true);
                setAgencia(condom.getAgencia());
                setBairro(condom.getBairro());
                setCep(condom.getCep());
                setCidade(condom.getCidade());
                setConta(condom.getConta());
                setDigitoAgencia(condom.getDigitoAgencia());
                setDigitoConta(condom.getDigitoConta());
                setEstado(condom.getEstado());
                setIdentificao(condom.getIdentificao());
                setNomeCond(condom.getNomeCond());
                setNumero(condom.getNumero());
                setRua(condom.getRua());
            }
            if (usu != null && usu.getCondominio() != null) {
                usu.getCondominio().setCondominio_bloco(null);
                usu.getCondominio().setCondominio_despesa(null);
            }
            condominio.add(usu.getCondominio());
            condominios = condominio;
        } catch (Exception e) {
        }
    }

    public String salvar() throws Exception {
        FacesMessage message = null;
        Usuario usu = admUtil.obterUsuarioLogado(request);
        Condominio condominio = new Condominio();
        condominio.setAgencia(getAgencia());
        condominio.setBairro(getBairro());
        condominio.setCep(getCep());
        condominio.setCidade(getCidade());
        condominio.setConta(getConta());
        condominio.setDigitoAgencia(getDigitoAgencia());
        condominio.setDigitoConta(getDigitoConta());
        condominio.setEstado(getEstado());
        condominio.setIdentificao(getIdentificao());
        condominio.setNomeCond(getNomeCond());
        condominio.setNumero(getNumero());
        condominio.setRua(getRua());
        try {
            facadeCondominio.incluir(condominio, usu, request);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Condomínio Inserido com Sucesso!", getNomeCond());
            FacesContext.getCurrentInstance().addMessage("Deu Certo!", message);
            return "/restrito/home.xhtml?faces-redirect=true";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Collection<Condominio> listCondominio() throws Exception {
        Collection<Condominio> lCollCond = new ArrayList<Condominio>();
        try {
            request.getAttribute("Condominio");
            Usuario usu = admUtil.obterUsuarioLogado(request);
            if (usu != null && usu.getCondominio() != null) {
                usu.getCondominio().setCondominio_bloco(null);
                usu.getCondominio().setCondominio_despesa(null);
            }
            lCollCond.add(usu.getCondominio());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return lCollCond;
    }

    public String editar(Condominio cond) {
        setIsEditar(true);
        FacesMessage message = null;
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("codom", cond);
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Condomínio Inserido com Sucesso!", NomeCond);
        FacesContext.getCurrentInstance().addMessage("Deu Certo!", message);

        return "/restrito/Condominio/cadastro.xhtml?faces-redirect=true";
    }

    public void excluir(Condominio cond) {
        Usuario usu;
        try {
            usu = admUtil.obterUsuarioLogado(request);
            FacesMessage message = null;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Condomínio Exckuído com Sucesso!", getNomeCond());
            FacesContext.getCurrentInstance().addMessage("Deu Certo!", message);
            facadeCondominio.excluir(cond.getIdCond(), usu, request);
        } catch (Exception ex) {
            Logger.getLogger(CondominioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
