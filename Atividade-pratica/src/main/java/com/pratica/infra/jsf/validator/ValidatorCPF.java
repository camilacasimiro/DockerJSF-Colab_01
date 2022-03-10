package com.pratica.infra.jsf.validator;

import com.pratica.domain.CPF;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validator.cpf")
public class ValidatorCPF implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        CPF cpf = (CPF) value;
        if(cpf.valido()) return;

        throw new ValidatorException(
                new FacesMessage("CPF inválido (tamanho incorreto)")
        );

    }
}