package com.una.adm.model;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Daniel Lanna
 */
@Entity
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idDesp;
    @Column(name = "descricao", nullable = false)
    private String descricaoDesp;
    @Column(name = "tipo", nullable = false)
    private String tipoDesp;
    @Column(name = "valor", nullable = false)
    private float valorDesp;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartamento_id")
    private Apartamento apartamento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bloco_id")
    private Bloco bloco;

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public int getIdDesp() {
        return idDesp;
    }

    public void setIdDesp(int idDesp) {
        this.idDesp = idDesp;
    }

    public String getDescricaoDesp() {
        return descricaoDesp;
    }

    public void setDescricaoDesp(String descricaoDesp) {
        this.descricaoDesp = descricaoDesp;
    }

    public String getTipoDesp() {
        return tipoDesp;
    }

    public void setTipoDesp(String tipoDesp) {
        this.tipoDesp = tipoDesp;
    }

    public float getValorDesp() {
        return valorDesp;
    }

    public void setValorDesp(float valorDesp) {
        this.valorDesp = valorDesp;
    }
}
