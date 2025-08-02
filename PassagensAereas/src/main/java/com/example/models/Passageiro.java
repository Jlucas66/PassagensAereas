package com.example.models;

import java.io.Serializable;

public class Passageiro implements Serializable {
    private static final long seriaVersionUID = 1L;

    private static int contador = 1;
    private int id;
    private String nome;
    private String cpf;

    public Passageiro(String nome, String cpf) {
        this.id = contador++;
        this.nome = nome;
        this.cpf = cpf;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
}
