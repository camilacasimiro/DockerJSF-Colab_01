package com.pratica.controller;

import com.pratica.domain.Banda;
import com.pratica.domain.BandaInterface;
import com.pratica.domain.CPF;
import com.pratica.domain.Integrante;
import com.pratica.infra.BandaJDBC;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named("controller")
@SessionScoped
public class BandaController implements Serializable {

    private Banda banda = new Banda();
    private List<Banda> resultBandas = new ArrayList<>();
    private BandaInterface bandaInterface;
    private List<Banda> integranteList = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(BandaController.class.getName());
    private String local;

    public BandaController() throws SQLException, ClassNotFoundException {
        logger.log(Level.INFO, "Lista banda");
        this.bandaInterface = new BandaJDBC();

    }

    public List<Banda> listBanda() throws SQLException, ClassNotFoundException {
        List<Banda> listaBanda = this.bandaInterface.listaBandas();
        logger.log(Level.INFO, "Integrantes " + integranteList );
        return listaBanda;
    }

    public List<Banda> listaIntegrantes(List<Banda> banda){
        this.integranteList.addAll(banda);
        return this.integranteList;
    }

    public String updateBanda(Banda banda){
        logger.log(Level.INFO, "Banda update " + banda.getNomeFantasia() );
        this.banda = banda;
        return "/Banda/edit?faces-redirect=true";
    }

    public void deleteBanda(Banda banda){
        logger.log(Level.INFO, "Banda " + banda.getNomeFantasia() );
        if(this.bandaInterface.removeBanda(banda)){
            String s = "/Banda/list?faces-redirect=true";
        }
    }

    public List<Banda> searchBandas() {
        this.resultBandas = this.bandaInterface.buscaBanda(this.local);
        return resultBandas;
    }


    public String inserirBanda() throws SQLException, ClassNotFoundException {
        if(this.banda.getId() > 0){
            this.bandaInterface.atualizaBanda(this.banda);
        } else{
            logger.log(Level.INFO, "Banda inserir" + this.banda);
            this.bandaInterface.adicionaBanda(this.banda);
        }
        this.banda = new Banda();
        return "/Banda/list?faces-redirect=true";
    }

    public List<Integrante> listarIntegrantePorIdBanda(int idBanda){
        List<Integrante> integrante = bandaInterface.localizarIntegranteComId(idBanda);
        return integrante;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Banda> getIntegranteList() {
        return integranteList;
    }

    public void setIntegranteList(List<Banda> integranteList) {
        this.integranteList = integranteList;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }
}
