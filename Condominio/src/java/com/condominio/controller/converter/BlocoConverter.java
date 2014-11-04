/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.condominio.controller.converter;

import com.una.adm.controller.facade.BlocoFacade;
import com.una.adm.model.Bloco;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Daniel Lanna
 */
@FacesConverter(value = "blocoConverter", forClass = Bloco.class)
public class BlocoConverter implements Converter {

    BlocoFacade blocoFacade = new BlocoFacade();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

        if (value == null || value.isEmpty()) {
            return "";
        }

        Bloco bloco = blocoFacade.obter(Long.valueOf(value));
        bloco.setApartamento(null);
        bloco.setCondominio(null);
        bloco.setDespesa(null);
        return bloco;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {

        if (value == null || value.equals("")) {
            return "";
        }

        String id = ((Bloco) value).getIdBloco().toString();
        return (id != null) ? id.toString() : "";
    }

}
