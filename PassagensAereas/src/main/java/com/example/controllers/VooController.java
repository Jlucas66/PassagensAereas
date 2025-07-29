package com.example.controllers;

import java.util.List;
import java.util.Optional;

import com.example.models.Voo;
import com.example.repositories.IRepository;
import com.example.repositories.VooRepository;

public class VooController {

    private IRepository<Voo> vooRepository;

    public VooController() {
        this.vooRepository = new VooRepository(); 
    }

    public void cadastrarVoo(Voo voo) throws IllegalArgumentException {
        if (voo == null) {
            throw new IllegalArgumentException("Voo não pode ser nulo.");
        }

        if (voo.getOrigem() == null || voo.getOrigem().isBlank() ||
            voo.getDestino() == null || voo.getDestino().isBlank()) {
            throw new IllegalArgumentException("Origem e destino são obrigatórios.");
        }

        vooRepository.adicionar(voo);
    }

    public List<Voo> listarVoos() {
        return vooRepository.listarTodos();
    }

    public Optional<Voo> buscarVooPorId(int id) {
    return Optional.ofNullable(vooRepository.buscarPorId(id));
}


    public boolean removerVoo(int id) {
        Voo voo = vooRepository.buscarPorId(id);
        if (voo != null) {
            vooRepository.remover(voo);
            return true;
        }
        return false;
    }

    public boolean vooExiste(int id) {
        return vooRepository.buscarPorId(id) != null;
    }

    public int contarVoos() {
        return vooRepository.listarTodos().size();
    }
}
