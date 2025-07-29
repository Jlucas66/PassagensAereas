package com.example.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Voo;

public class VooRepository implements IRepository<Voo> {
    private List<Voo> voos = new ArrayList<>();

    @Override
    public void adicionar(Voo voo) {
        voos.add(voo);
    }
    
    @Override
    public List<Voo> listarTodos() {
        return new ArrayList<>(voos); 
    }
    
    public Voo buscarPorId(int id) {
        for (Voo v : voos) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }
    
    @Override
    public boolean atualizar(Voo vooAtualizado) {
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getId() == vooAtualizado.getId()) {
                voos.set(i, vooAtualizado); 
                return true;
            }
        }
        return false;
    }

    @Override
    public void remover(Voo voo) {
        voos.remove(voo);
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
