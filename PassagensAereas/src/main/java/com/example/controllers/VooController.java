package com.example.controllers;

import java.util.List;
import java.util.Optional;

import com.example.models.Voo;
import com.example.repositories.IVooRepository;
import com.example.repositories.VooRepository;

public class VooController {

    private IVooRepository vooRepository;

    public VooController() {
        this.vooRepository = new VooRepository(); 
    }

    public void cadastrarVoo(Voo voo) throws IllegalArgumentException {
        if (voo == null) {
            throw new IllegalArgumentException("Voo não pode ser nulo.");
        }

        if (voo.getOrigem().isBlank() || voo.getDestino().isBlank()) {
            throw new IllegalArgumentException("Origem e destino são obrigatórios.");
        }

        vooRepository.adicionar(voo);
    }

    public List<Voo> listarVoos() {
        return vooRepository.listarTodos();
    }

    public Optional<Voo> buscarVooPorId(int id) {
        return vooRepository.buscarPorId(id);
    }

    public boolean removerVoo(int id) {
        return vooRepository.remover(id);
    }

    public boolean vooExiste(int id) {
        return vooRepository.existe(id);
    }

    public int contarVoos() {
        return vooRepository.contar();
    }
}
