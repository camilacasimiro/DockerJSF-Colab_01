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

    public List<Banda> getIntegranteList() {
        return integranteList;
    }

    public void setIntegranteList(List<Banda> integranteList) {
        this.integranteList = integranteList;
    }
}
