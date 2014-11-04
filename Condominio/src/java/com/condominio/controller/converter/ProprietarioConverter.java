/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.condominio.controller.converter;

import com.una.adm.controller.facade.ProprietarioFacade;
import com.una.adm.model.Proprietario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Daniel Lanna
 */
@FacesConverter(value = "proprietarioConverter", forClass = Proprietario.class)
public class ProprietarioConverter implements Converter {

    ProprietarioFacade propFacade = new ProprietarioFacade();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

        if (value == null || value.isEmpty()) {
            return "";
        }

        Proprietario prop = propFacade.obter(Long.valueOf(value));
        prop.setApartamento(null);
        prop.setCondominio(null);
        prop.setEvento(null);
        return prop;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {

        if (value == null || value.equals("")) {
            return "";
        }
        String id = ((Proprietario) value).getId().toString();
        return (id != null) ? id.toString() : null;
    }

}
