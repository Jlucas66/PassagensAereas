package com.example.controllers;

import java.util.List;
import java.util.Optional;

import com.example.models.Passageiro;
import com.example.models.Passagem;
import com.example.models.Voo;
import com.example.repositories.IRepository;



public class PassagemController {
    private IRepository<Passagem> repositorio;

    public PassagemController(IRepository<Passagem> repositorio) {
        this.repositorio = repositorio;
    }

    public void comprarPassagem(Passageiro passageiro, Voo voo) {
        if (passageiro == null || voo == null) {
            throw new IllegalArgumentException("Passageiro ou voo inválido");
        }
        Passagem passagem = new Passagem(passageiro, voo);
        repositorio.adicionar(passagem);
    }

    public List<Passagem> listarTodas() {
        return repositorio.listarTodos();
    }

    public Optional<Passagem> buscarPorId(int id) {
        return Optional.ofNullable(repositorio.buscarPorId(id));
    }

    public boolean atualizarPassagem(Passagem passagemAtualizada) {
        if (passagemAtualizada == null) {
            throw new IllegalArgumentException("Passagem não pode ser nula");
        }
        return repositorio.atualizar(passagemAtualizada);
    }
}
