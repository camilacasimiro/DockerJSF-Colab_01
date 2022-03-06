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

@Named("controler")
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
//        resultBandas.add(new Banda(1, "CZ", "SHow" ));
//        resultBandas.add(new Banda(2, "Triunfo", "Balada" ));
//        resultBandas.add(new Banda(3, "Ss", "Casa da diversão" ));
        logger.log(Level.INFO, "Lista banda");

        return this.bandaInterface.listaBandas();

    }

}
