package com.pratica.domain;

import java.util.List;

public interface IntegranteInterface {

    public List<Integrante> listaIntegrantes();

    public void adicionaIntegrante (Integrante integrante);

    public void atualizaIntegrante (Integrante integrante);

    public Boolean removeIntegrante (Integrante integrante);

    public List<Integrante> buscaIntegrante (String cpf);

    public Integrante buscaIntegranteById(int id);
}
