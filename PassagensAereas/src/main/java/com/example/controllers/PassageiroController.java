package com.example.controllers;

import java.util.Optional;

import com.example.models.Passageiro;
import com.example.repositories.IRepository;

import java.util.List;



public class PassageiroController {

    private IRepository<Passageiro> repositorio;

    public PassageiroController(IRepository<Passageiro> repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarPassageiro(String nome, String cpf) {
        for (Passageiro p : repositorio.listarTodos()) {
            if (p.getCpf().equals(cpf)) {
                throw new IllegalArgumentException("CPF já cadastrado");
            }
        }
        repositorio.adicionar(new Passageiro(nome, cpf));
    }

    public Optional<Passageiro> buscarPorId(int id) {
        return Optional.ofNullable(repositorio.buscarPorId(id));
    }

    public List<Passageiro> listarPassageiros() {
        return repositorio.listarTodos();
    }

    public boolean atualizarPassageiro(Passageiro passageiroAtualizado) {
        if (passageiroAtualizado == null) {
            throw new IllegalArgumentException("Passageiro não pode ser nulo");
        }

        return repositorio.atualizar(passageiroAtualizado);
    }
}
