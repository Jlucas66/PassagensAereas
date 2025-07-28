package com.example.models;

import java.time.LocalDate;

public abstract class Voo {
    private static int contadorId = 1;

    private int id;
    private String origem;
    private String destino;
    private LocalDate dataPartida;
    private int capacidade;

    public Voo(String origem, String destino, LocalDate dataPartida, int capacidade) {
        this.id = contadorId++;
        this.origem = origem;
        this.destino = destino;
        this.dataPartida = dataPartida;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public abstract double calcularTaxa();


    
}
