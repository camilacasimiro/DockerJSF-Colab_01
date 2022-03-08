package com.pratica.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Integrante {
    private int id;
    private String nome;
    private Date dataDeNascimento;
    private String cpf;

    public Integrante() {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
    }

    public Integrante(int id, String nome, Date dataDeNascimento, String cpf) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Integrante that = (Integrante) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Integrante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                ", cpf=" + cpf +
                '}';
    }
}
