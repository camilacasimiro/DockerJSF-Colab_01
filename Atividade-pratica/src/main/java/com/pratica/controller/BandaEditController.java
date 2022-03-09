package com.pratica.controller;

import com.pratica.domain.Banda;
import com.pratica.domain.BandaInterface;
import com.pratica.infra.BandaJDBC;
import com.sun.faces.application.resource.ResourceCache;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("controllerBandaEdit")
@SessionScoped
public class BandaEditController implements Serializable {

    private List<Banda> bandaLista;
    private Banda banda = new Banda();
    private BandaInterface bandaInterface;

    private static final Logger logger = Logger.getLogger(BandaEditController.class.getName());

    public BandaEditController() {
        if (FacesContext.getCurrentInstance().getExternalContext().getFlash() != null) {
            Object id = FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id");
            logger.log(Level.INFO, "Lista banda" +" "+ id);
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().keep("id");
            banda = new Banda();
            this.banda = bandaInterface.buscaBandaById((int) id);
            logger.log(Level.INFO, "Banda objeto : " + this.banda);

        } else {
            this.banda = new Banda();
            this.banda.setNomeFantasia("Joao");
        }
    }

    public void updateBanda(){
        logger.log(Level.INFO, "Banda " + banda.getNomeFantasia() );
        bandaInterface.atualizaBanda(this.banda);
        String s = "/Banda/list?faces-redirect=true";

    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }
}
