package com.example.models;

import java.time.LocalDate;

public class VooInternacional extends Voo {
    private String paisOrigem;
    private String paisDestino;

    public VooInternacional(String origem, String destino, LocalDate dataPartida, int capacidade, String paisOrigem, String paisDestino, double precoBase) {
        super(origem, destino, dataPartida, capacidade, precoBase);
        this.paisOrigem = paisOrigem;
        this.paisDestino = paisDestino;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    @Override
    public double calcularTaxa() {
        return 200.0; 
    }
    
}
