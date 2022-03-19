package com.pratica.domain;

import java.sql.SQLException;
import java.util.List;

public interface BandaInterface {

    public List<Banda> listaBandas() throws ClassNotFoundException, SQLException;

    public void adicionaBanda (Banda banda);

    public void atualizaBanda (Banda banda);

    public Boolean removeBanda (Banda banda);

    public List<Banda> buscaBanda(String local);

    public  Banda buscaBandaById(int id);

    public List<Integrante> localizarIntegranteComId(int idBanda);
}
