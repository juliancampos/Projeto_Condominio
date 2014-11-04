package com.una.adm.model;

import org.jrimum.bopepo.Boleto;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

/**
 *
 * @author Daniel Lanna
 */
public class BoletoModel {

    private Cedente cedente;
    private ContaBancaria contaBancaria;
    private Endereco endereco;
    private Titulo titulo;
    private Boleto boleto;
    private Sacado sacado;

    private Double valorCondominio;
    private Double valorBloco;
    private Double valorApartamento;
    
    private Condominio condominio;
    private Bloco bloco;
    private Apartamento apartamento;

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

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

    public Double getValorCondominio() {
        return valorCondominio;
    }

    public void setValorCondominio(Double valorCondominio) {
        this.valorCondominio = valorCondominio;
    }

    public Double getValorBloco() {
        return valorBloco;
    }

    public void setValorBloco(Double valorBloco) {
        this.valorBloco = valorBloco;
    }

    public Double getValorApartamento() {
        return valorApartamento;
    }

    public void setValorApartamento(Double valorApartamento) {
        this.valorApartamento = valorApartamento;
    }

    public Sacado getSacado() {
        return sacado;
    }

    public void setSacado(Sacado sacado) {
        this.sacado = sacado;
    }

    public Cedente getCedente() {
        return cedente;
    }

    public void setCedente(Cedente cedente) {
        this.cedente = cedente;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }
}
