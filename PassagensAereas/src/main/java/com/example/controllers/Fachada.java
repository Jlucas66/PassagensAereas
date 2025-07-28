package com.example.controllers;

import java.util.List;

import com.example.models.Voo;

public class Fachada {

    private final VooController vooController = new VooController();

    public void cadastrarVoo(Voo voo) throws IllegalArgumentException {
        vooController.cadastrarVoo(voo);
    } 

    public List<Voo> listarVoos() {
        return vooController.listarVoos();
    }

    public Voo buscarVooPorId(int id) {
        return vooController.buscarVooPorId(id).orElse(null);
    }

    public boolean removerVoo(int id) {
        return vooController.removerVoo(id);
    }

    public boolean vooExiste(int id) {
        return vooController.vooExiste(id);
    }

    public int contarVoos() {
        return vooController.contarVoos();
    }
    
}
