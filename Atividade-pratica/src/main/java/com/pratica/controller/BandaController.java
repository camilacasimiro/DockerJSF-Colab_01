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

@Named("controller")
@SessionScoped
public class BandaController implements Serializable {

    private Banda banda = new Banda();
    private List<Banda> resultBandas = new ArrayList<>();
    private BandaInterface bandaInterface;
    private static final Logger logger = Logger.getLogger(BandaController.class.getName());

    public BandaController() {
        logger.log(Level.INFO, "Lista banda");
        this.bandaInterface = (BandaInterface) new BandaJDBC();
    }

    public List<Banda> listBanda() throws SQLException, ClassNotFoundException {

        return this.bandaInterface.listaBandas();

    }

    public void deleteBanda(Banda banda){
        logger.log(Level.INFO, "Banda " + banda.getNomeFantasia() );
        if(this.bandaInterface.removeBanda(banda)){
            String s = "/Banda/list?faces-redirect=true";
            logger.log(Level.INFO, "Banda " + banda.getNomeFantasia() + " removida com sucesso");
        }
    }

}
