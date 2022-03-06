package com.pratica.domain;

import java.util.List;

public interface BandaInterface {

    public List<Banda> listaBandas();

    public void adicionaBanda (Banda banda);

    public void atualizaBanda (Banda banda);

    public void removeBanda (Banda banda);

    public List<Banda> buscaBanda(String localDeOrigem);
}
