package com.una.adm.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Daniel Lanna
 */
@Entity
@Table(name = "condominio")
public class Condominio {

    public Condominio(int idCond) {
        this.idCond = idCond;
    }

    public Condominio() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sq_condominio")
    private int idCond;
    @Column(name = "nome", nullable = false)
    private String NomeCond;
    @Column(name = "rua", nullable = false)
    private String Rua;
    @Column(name = "numero", nullable = false)
    private int Numero;
    @Column(name = "bairro", nullable = false)
    private String Bairro;
    @Column(name = "cep", nullable = false)
    private String Cep;
    @Column(name = "cidade", nullable = false)
    private String Cidade;
    @Column(name = "estado", nullable = false)
    private String Estado;
    @Column(name = "conta", nullable = false)
    private String conta;
    @Column(name = "digito_conta", nullable = false)
    private int digitoConta;
    @Column(name = "agencia", nullable = false)
    private String agencia;
    @Column(name = "digito_agencia", nullable = false)
    private int digitoAgencia;
    @Column(name = "identificao", nullable = false)
    private String identificao;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "condominio_id")
    private List<Bloco> bloco;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "condominio_id")
    private List<Despesa> despesa;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "condominio_id")
    private List<EventoMobile> evento;

    public List<EventoMobile> getEvento() {
        return evento;
    }

    public void setEvento(List<EventoMobile> evento) {
        this.evento = evento;
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

    public List<Bloco> getCondominio_bloco() {
        return bloco;
    }

    public void setCondominio_bloco(List<Bloco> bloco) {
        this.bloco = bloco;
    }

    public List<Despesa> getCondominio_despesa() {
        return despesa;
    }

    public void setCondominio_despesa(List<Despesa> despesa) {
        this.despesa = despesa;
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
}
