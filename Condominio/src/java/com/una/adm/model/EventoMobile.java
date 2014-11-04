/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Daniel Lanna
 */
@Entity
@Table(name = "evento")
public class EventoMobile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sq_evento")
    private Long idEvento;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "dt_inicio", nullable = false)
    private Date dataInicio;
    @Column(name = "dt_fim", nullable = false)
    private Date dataFim;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proprietario_id", nullable = false)
    private Proprietario proprietario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "condominio_id", nullable = false)
    private Condominio condominio;

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

}
