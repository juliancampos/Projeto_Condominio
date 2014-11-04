/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.model;

import java.util.Collection;

/**
 *
 * @author Daniel Lanna
 */
public class Util {

    private Collection<Bloco> bloco;
    private Collection<Proprietario> proprietario;
    private Collection<Apartamento> apartamento;
    private Collection<Despesa> despesa;

    public Collection<Bloco> getBloco() {
        return bloco;
    }

    public void setBloco(Collection<Bloco> bloco) {
        this.bloco = bloco;
    }

    public Collection<Proprietario> getProprietario() {
        return proprietario;
    }

    public void setProprietario(Collection<Proprietario> proprietario) {
        this.proprietario = proprietario;
    }

    public Collection<Apartamento> getApartamento() {
        return apartamento;
    }

    public void setApartamento(Collection<Apartamento> apartamento) {
        this.apartamento = apartamento;
    }

    public Collection<Despesa> getDespesa() {
        return despesa;
    }

    public void setDespesa(Collection<Despesa> despesa) {
        this.despesa = despesa;
    }
}
