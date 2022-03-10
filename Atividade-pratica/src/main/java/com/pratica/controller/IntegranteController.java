package com.pratica.controller;

import com.pratica.domain.CPF;
import com.pratica.domain.Integrante;
import com.pratica.domain.IntegranteInterface;
import com.pratica.infra.IntegranteJDBC;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Named("controllerInteg")
@SessionScoped
public class IntegranteController  implements Serializable {

    private IntegranteInterface integranteInterface;
    private List<Integrante> integranteList = new ArrayList<>();
    private String cpf;
    private Integrante integrante;

    public IntegranteController() {
        this.integranteInterface = (IntegranteInterface) new IntegranteJDBC();
    }


    public List<Integrante> listIntegrantes(){
        List<Integrante> listIntegrante = this.integranteInterface.listaIntegrantes();

        return listIntegrante;
    }

    public List<Integrante> searchIntegrante() {
        this.integranteList = this.integranteInterface.buscaIntegrante(this.cpf);
        return integranteList;
    }

    public void deleteIntegrante(Integrante integrante){
        if(this.integranteInterface.removeIntegrante(integrante)){
            String s = "/Integrante/list?faces-redirect=true";
        }
    }
    public String salvarIntegrante(){
        if(this.integrante.getId() > 0){
            this.integranteInterface.atualizaIntegrante(this.integrante);
        } else{
            this.integranteInterface.adicionaIntegrante(this.integrante);
        }
        this.integrante = new Integrante();
        integranteInterface.adicionaIntegrante(this.integrante);

        return "/integrantes/list?faces-redirect=true";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }
    //3, "maria", null, "156651-60"
}
