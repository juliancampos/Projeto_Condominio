package com.una.adm.model;

import java.util.List;
import java.util.Objects;
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
@Table(name = "bloco")
public class Bloco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBloco;
    @Column(name = "nome_bloco", nullable = false)
    private String NomeBloco;
    @Column(name = "obs", nullable = false)
    private String obsBloco;
    @ManyToOne
    @JoinColumn(name = "condominio_id",
            insertable = true, updatable = true,
            nullable = false)
    private Condominio condominio;
    @OneToMany(mappedBy = "bloco", fetch = FetchType.EAGER)
    private List<Despesa> despesa;
    @OneToMany(mappedBy = "bloco", fetch = FetchType.EAGER)
    private List<Apartamento> apartamento;

    public List<Apartamento> getApartamento() {
        return apartamento;
    }

    public void setApartamento(List<Apartamento> apartamento) {
        this.apartamento = apartamento;
    }

    public List<Despesa> getDespesa() {
        return despesa;
    }

    public void setDespesa(List<Despesa> despesa) {
        this.despesa = despesa;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
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

    public Long getIdBloco() {
        return idBloco;
    }

    public void setIdBloco(Long idBloco) {
        this.idBloco = idBloco;
    }
//    public Long getidCondBloco() {
//        return idCondBloco;
//    }
//
//    public void setidCondBloco(Long idCondBloco) {
//        this.idCondBloco = idCondBloco;
//    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.condominio);
        hash = 67 * hash + Objects.hashCode(this.despesa);
        hash = 67 * hash + Objects.hashCode(this.apartamento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.equals("")) {
            return false;
        }
        Bloco bloco = (Bloco) obj;
        if (getClass() != bloco.getClass()) {
            return false;
        }
        return true;
    }

}
