package com.pratica.infra.jsf.converter;

import com.pratica.domain.CPF;
import com.pratica.domain.Integrante;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "convert.integrante", forClass = Integrante.class)
public class ConverterIntegrante implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        Integrante integrante = new Integrante();
        return integrante;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) return null;
        Integrante integrante = (Integrante) value;

        return (integrante.getId().toString());
    }

}