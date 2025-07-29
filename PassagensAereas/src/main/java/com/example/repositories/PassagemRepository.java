package com.example.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Passagem;

public class PassagemRepository implements IRepository<Passagem> {
    private ArrayList<Passagem> passagens = new ArrayList<>();

    @Override
    public void adicionar(Passagem passagem) {
        passagens.add(passagem);
    }

    @Override
    public void remover(Passagem passagem) {
        passagens.remove(passagem);
    }

    @Override
    public List<Passagem> listarTodos() {
        return new ArrayList<>(passagens); 
    }

    @Override
    public Passagem buscarPorId(int id) {
        for (Passagem p : passagens) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean atualizar(Passagem passagemAtualizada) {
        for (int i = 0; i < passagens.size(); i++) {
            if (passagens.get(i).getId() == passagemAtualizada.getId()) {
                passagens.set(i, passagemAtualizada); 
                return true;
            }
        }
        return false;
    }

}
