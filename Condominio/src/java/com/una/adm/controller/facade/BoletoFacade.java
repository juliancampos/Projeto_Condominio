/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.facade;

import com.una.adm.model.Apartamento;
import com.una.adm.model.Bloco;
import com.una.adm.model.BoletoModel;
import com.una.adm.model.Condominio;
import com.una.adm.model.DAO.BoletoDAO;
import java.util.Collection;

/**
 *
 * @author Daniel Lanna
 */
public class BoletoFacade {

    public BoletoModel obterValorDespesaCondominio(Condominio pCond) {
        BoletoModel lBoleto = new BoletoModel();
        Double valor = new BoletoDAO().obterValorPorCondominio(pCond);
        lBoleto.setValorCondominio(valor);

        return lBoleto;

    }

    public BoletoModel obterValorDespesaBloco(Condominio pCond, Bloco pBloco) {
        BoletoModel lBoleto = new BoletoModel();
        Double valor = new BoletoDAO().obterValorPorBloco(pCond, pBloco);
        lBoleto.setValorBloco(valor);

        return lBoleto;

    }

    public BoletoModel obterValorDespesaApartamento(Condominio pCond, Apartamento pApartamento) {
        BoletoModel lBoleto = new BoletoModel();
        Double valor = new BoletoDAO().obterValorPorApartamento(pCond, pApartamento);
        lBoleto.setValorApartamento(valor);

        return lBoleto;

    }
    
    public BoletoModel verificarApartamentoDespesa(Condominio pCond, Apartamento pApartamento) {
        BoletoModel lBoleto = new BoletoModel();
        Double valor = new BoletoDAO().obterApartamento(pCond, pApartamento);
        lBoleto.setValorApartamento(valor);

        return lBoleto;

    }

}
