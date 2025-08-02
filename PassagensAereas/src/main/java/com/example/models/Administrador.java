package com.example.models;

import java.io.Serializable;

public class Administrador implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int contadorId = 1;

    private int id;
    private String nome;
    private String email;
    private String senha; 

    public Administrador(String nome, String email, String senha) {
        this.id = contadorId++;
        this.nome = nome;
        this.email = email;
        this.senha = senha; 
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
