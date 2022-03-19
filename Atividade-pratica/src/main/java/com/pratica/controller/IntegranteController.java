package com.pratica.controller;

import com.pratica.domain.Integrante;
import com.pratica.domain.IntegranteInterface;
import com.pratica.infra.IntegranteJDBC;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


@Named("controllerInteg")
@SessionScoped
public class IntegranteController  implements Serializable {

    private IntegranteInterface integranteInterface;
    private List<Integrante> integranteList = new ArrayList<>();
    private String cpf;
    private Integrante integrante = new Integrante();
    private static final Logger logger = Logger.getLogger(IntegranteController.class.getName());

    public IntegranteController() {
        this.integranteInterface = (IntegranteInterface) new IntegranteJDBC();
    }

    public List<Integrante> listIntegrantes(){
        List<Integrante> listIntegrante = this.integranteInterface.listaIntegrantes();

        return listIntegrante;
    }

    public String salvarIntegrante(){
        logger.log(Level.INFO, "Lista integrante no controller"+ this.integrante);
        if(Objects.isNull(this.integrante.getId())){
            this.integranteInterface.adicionaIntegrante(this.integrante);
        } else{
            this.integranteInterface.atualizaIntegrante(this.integrante);
        }
        this.integrante = new Integrante();

        return "/Integrante/list?faces-redirect=true";
    }

    public String updateIntegrante( Integrante integrante){
        logger.log(Level.INFO, "Integrante " + integrante.getNome() );
        this.integrante = integrante;
        return "/Integrante/edit?faces-redirect=true";

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

}
