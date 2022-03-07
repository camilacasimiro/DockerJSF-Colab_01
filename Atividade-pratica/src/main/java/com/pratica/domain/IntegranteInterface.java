package com.pratica.domain;

import java.util.List;

public interface IntegranteInterface {

    public List<Integrante> listaIntegrantes();

    public void adicionaIntegrante (Integrante integrante);

    public void atualizaIntegrante (Integrante integrante);

    public void removeIntegrante (Integrante integrante);

    public Integrante buscaIntegrante (String cpf);
}
