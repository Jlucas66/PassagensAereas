package com.example.models;

import java.io.Serializable;

public class Passagem implements Reservavel, Serializable {
        private static final long seriaVersionUID = 1L;
    private static int contador = 1;
    private int id;
    private Passageiro passageiro;
    private Voo voo;

    public Passagem(Passageiro passageiro, Voo voo) {
        this.id = contador++;
        this.passageiro = passageiro;
        this.voo = voo;
    }

    public int getId() { return id; }
    public Passageiro getPassageiro() { return passageiro; }
    public Voo getVoo() { return voo; }

    @Override
    public double calcularPrecoTotal() {
        return voo.calcularPrecoFinal();
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Passagem.contador = contador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    
}
