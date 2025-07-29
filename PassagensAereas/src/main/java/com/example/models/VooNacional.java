package com.example.models;

import java.time.LocalDate;

public class VooNacional extends Voo {
    private String estadoOrigem;
    private String estadoDestino;

    public VooNacional(String origem, String destino, LocalDate dataPartida, int capacidade, String estadoOrigem, String estadoDestino, double precoBase) {
        super(origem, destino, dataPartida, capacidade, precoBase);
        this.estadoOrigem = estadoOrigem;
        this.estadoDestino = estadoDestino;
    }

    public String getEstadoOrigem() {
        return estadoOrigem;
    }

    public void setEstadoOrigem(String estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(String estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    @Override
    public double calcularTaxa() {
        return 100.0; 
    }
    
}
