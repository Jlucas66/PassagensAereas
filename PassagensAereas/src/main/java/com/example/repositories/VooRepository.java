package com.example.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.models.Voo;

public class VooRepository implements IVooRepository {
    private List<Voo> voos = new ArrayList<>();

    public void adicionar(Voo voo) {
        voos.add(voo);
    }

    public List<Voo> listarTodos() {
        return new ArrayList<>(voos); 
    }

    public Optional<Voo> buscarPorId(int id) {
        return voos.stream().filter(v -> v.getId() == id).findFirst();
    }

    public boolean remover(int id) {
        return voos.removeIf(v -> v.getId() == id);
    }

    public void limpar() {
        voos.clear();
    }

    public boolean existe(int id) {
        return voos.stream().anyMatch(v -> v.getId() == id);
    }

    public int contar() {
        return voos.size();
    }
    
}
