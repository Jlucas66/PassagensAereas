package com.example.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.models.Passageiro;
import com.example.models.Passagem;
import com.example.models.Voo;
import com.example.repositories.IRepository;

public class PassagemController {
    private final IRepository<Passagem> repositorio;
    private final PassageiroController passageiroController;
    private final VooController vooController;

    public PassagemController(IRepository<Passagem> repositorio, 
                            PassageiroController passageiroController,
                            VooController vooController) {
        this.repositorio = repositorio;
        this.passageiroController = passageiroController;
        this.vooController = vooController;
    }

    public void comprarPassagem(Passageiro passageiro, Voo voo) {
        if (passageiro == null || voo == null) {
            throw new IllegalArgumentException("Passageiro ou voo inválido");
        }
        Passagem passagem = new Passagem(passageiro, voo);
        repositorio.adicionar(passagem);
        salvarPassagens();
    }

    private void salvarPassagens() {
    try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("passagens.dat"))) {
        
        oos.writeObject(repositorio.listarTodos());
        System.out.println("Passagens salvas: " + repositorio.listarTodos().size());
        
    } catch (IOException e) {
        System.err.println("Erro ao salvar passagens: " + e.getMessage());
    }
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

    public List<Passagem> listarPassagensCompletas() {
    List<Passagem> passagens = this.listarTodas();
    List<Passageiro> passageiros = passageiroController.listarPassageiros();
    List<Voo> voos = vooController.listarVoos();
    
    // Verificação para evitar duplicatas
    Map<Integer, Passageiro> mapaPassageiros = passageiros.stream()
        .collect(Collectors.toMap(
            Passageiro::getId,
            Function.identity(),
            (existente, novo) -> existente)); // Mantém o valor existente em caso de duplicata
    
    Map<Integer, Voo> mapaVoos = voos.stream()
        .collect(Collectors.toMap(
            Voo::getId,
            Function.identity(),
            (existente, novo) -> existente)); // Mantém o valor existente em caso de duplicata
    
    return passagens.stream()
        .map(passagem -> {
            Passageiro passageiro = mapaPassageiros.get(passagem.getPassageiro().getId());
            Voo voo = mapaVoos.get(passagem.getVoo().getId());

            if (passageiro != null && voo != null) {
                Passagem p = new Passagem(passageiro, voo);
                p.setId(passagem.getId());
                return p;
            }
            return null;
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    }
}