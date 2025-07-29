package com.example.models;

public class Passageiro {
    private static int contador = 1;
    private int id;
    private String nome;
    private String cpf;

    public Passageiro(String nome, String cpf) {
        this.id = contador++;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
}
