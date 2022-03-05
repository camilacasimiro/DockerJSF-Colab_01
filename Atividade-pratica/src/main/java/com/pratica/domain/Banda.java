package com.pratica.domain;

import java.util.List;
import java.util.Objects;


public class Banda {
    private int id;
    private String localDeOrigem;
    private String nomeFantasia;
    private List<Integrante> integrantes;

    public Banda() {

    }
    public Banda(int id, String localDeOrigem, String nomeFantasia, List<Integrante> integrantes) {
        this.id = id;
        this.localDeOrigem = localDeOrigem;
        this.nomeFantasia = nomeFantasia;
        this.integrantes = integrantes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalDeOrigem() {
        return localDeOrigem;
    }

    public void setLocalDeOrigem(String localDeOrigem) {
        this.localDeOrigem = localDeOrigem;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banda banda = (Banda) o;
        return id == banda.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Banda{" +
                "id=" + id +
                ", localDeOrigem='" + localDeOrigem + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", integrantes=" + integrantes +
                '}';
    }
}
