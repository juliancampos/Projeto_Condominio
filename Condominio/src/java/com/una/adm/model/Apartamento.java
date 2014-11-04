package com.una.adm.model;

import java.util.List;
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
@Table(name = "apartamento")
public class Apartamento {

    public Apartamento(int idApart) {
        this.idApart = idApart;

    }

    public Apartamento() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idApart;
    @Column(name = "obs_apart", nullable = false)
    private String obsApart;
    @Column(name = "num_apart", nullable = false)
    private int numApart;
    @OneToMany(mappedBy = "apartamento", fetch = FetchType.EAGER)
    private List<Despesa> despesa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bloco_id")
    private Bloco bloco;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proprietario_id", nullable = false)
    private Proprietario proprietario;

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

    public List<Despesa> getDespesa() {
        return despesa;
    }

    public void setDespesa(List<Despesa> despesa) {
        this.despesa = despesa;
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
}
