package com.example.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Optional;

import com.example.models.Voo;
import com.example.repositories.IRepository;
import com.example.repositories.VooRepository;

public class VooController {
    private static final String ARQUIVO_VOOS = "voos.dat";
    private IRepository<Voo> vooRepository;

    public VooController() {
        this.vooRepository = new VooRepository();
        carregarVoos(); // Carrega os voos ao iniciar
    }

    public void cadastrarVoo(Voo voo) throws IllegalArgumentException {
        validarVoo(voo);
        vooRepository.adicionar(voo);
        salvarVoos();
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
            salvarVoos(); 
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

    private void validarVoo(Voo voo) throws IllegalArgumentException {
        if (voo == null) {
            throw new IllegalArgumentException("Voo não pode ser nulo.");
        }
        if (voo.getOrigem() == null || voo.getOrigem().isBlank()) {
            throw new IllegalArgumentException("Origem do voo é obrigatória.");
        }
        if (voo.getDestino() == null || voo.getDestino().isBlank()) {
            throw new IllegalArgumentException("Destino do voo é obrigatório.");
        }
        if (voo.getDataPartida() == null) {
            throw new IllegalArgumentException("Data de partida é obrigatória.");
        }
        if (voo.getCapacidade() <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
        }
    }

@SuppressWarnings("unchecked")
private void carregarVoos() {
    try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream(ARQUIVO_VOOS))) {
        
        // 1. Lê a lista de voos do arquivo
        List<Voo> voosSalvos = (List<Voo>) ois.readObject();
        
        // 2. Limpa o repositório antes de adicionar os novos voos
        vooRepository.limparRepositorio();
        
        // 3. Adiciona os voos salvos ao repositório
        voosSalvos.forEach(voo -> {
            try {
                vooRepository.adicionar(voo);
            } catch (IllegalArgumentException e) {
                System.err.println("Erro ao adicionar voo: " + e.getMessage());
            }
        });
        
        // 4. Log informativo
        System.out.println("Voos carregados: " + vooRepository.contarVoos());
        
    } catch (FileNotFoundException e) {
        System.out.println("Arquivo de voos não encontrado. Iniciando com lista vazia.");
    } catch (IOException e) {
        System.err.println("Erro de E/S ao carregar voos: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.err.println("Erro: formato do arquivo de voos inválido.");
    } catch (Exception e) {
        System.err.println("Erro inesperado ao carregar voos: " + e.getMessage());
    }
}

    private void salvarVoos() {
        List<Voo> voos = vooRepository.listarTodos();
        
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(ARQUIVO_VOOS))) {
            
            oos.writeObject(voos);
            System.out.println("Voos salvos: " + voos.size());
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar voos: " + e.getMessage());
        }
    }

    public boolean atualizarVoo(Voo voo) {
        if (vooRepository.buscarPorId(voo.getId()) != null) {
            vooRepository.remover(vooRepository.buscarPorId(voo.getId()));
            vooRepository.adicionar(voo);
            salvarVoos();
            return true;
        }
        return false;
    }
}