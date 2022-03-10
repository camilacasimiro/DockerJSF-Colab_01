package com.pratica.controller;

import com.pratica.domain.Banda;
import com.pratica.domain.BandaInterface;
import com.pratica.domain.CPF;
import com.pratica.domain.Integrante;
import com.pratica.infra.BandaJDBC;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
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

    public BandaController() {
        logger.log(Level.INFO, "Lista banda");
        this.bandaInterface = (BandaInterface) new BandaJDBC();
    }

    public List<Banda> listBanda() throws SQLException, ClassNotFoundException {
        List<Banda> listaBanda = this.bandaInterface.listaBandas();
//      listaBanda.stream().forEach(obj -> this.integranteList.addAll((obj.getIntegrantes())));
        logger.log(Level.INFO, "Integrantes " + integranteList );

        return listaBanda;
    }


//    public List<Banda> listBanda() throws SQLException, ClassNotFoundException {
//        List<Banda> bandas = new ArrayList<>();
//        List<Integrante> integrantes = new ArrayList<>();
//
//        integrantes.add(new Integrante(1, "Jhey", null, "11111111111") );
//        integrantes.add(new Integrante(2, "Camila", null, "22222222222"));
//        integrantes.add(new Integrante(2, "Camila", null, "33333333333"));
//
//        bandas.add(new Banda(1, "IF", "DAC", integrantes) );
//        logger.log(Level.INFO, "Banda " + bandas);
//
//        return Collections.unmodifiableList(bandas);
//
//    }

    public List<Banda> listaIntegrantes(List<Banda> banda){
        this.integranteList.addAll(banda);
        return this.integranteList;
    }


    public void deleteBanda(Banda banda){
        logger.log(Level.INFO, "Banda " + banda.getNomeFantasia() );
        if(this.bandaInterface.removeBanda(banda)){
            String s = "/Banda/list?faces-redirect=true";
            logger.log(Level.INFO, "Banda " + banda.getNomeFantasia() + " removida com sucesso");
        }
    }

    public List<Banda> searchBandas() {
        this.resultBandas = this.bandaInterface.buscaBanda(this.local);
//        logger.log(Level.INFO, "Local de Origem" + resultBandas);
        return resultBandas;
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
}
