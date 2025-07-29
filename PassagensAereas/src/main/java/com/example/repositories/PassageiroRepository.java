package com.example.repositories;

import java.util.ArrayList;

import com.example.models.Passageiro;

public class PassageiroRepository implements IRepository<Passageiro> {
    private ArrayList<Passageiro> passageiros = new ArrayList<>();

    @Override
    public void adicionar(Passageiro passageiro) {
        passageiros.add(passageiro);
    }

    @Override
    public void remover(Passageiro passageiro) {
        passageiros.remove(passageiro);
    }

    @Override
    public ArrayList<Passageiro> listarTodos() {
        return new ArrayList<>(passageiros);
    }

    @Override
    public Passageiro buscarPorId(int id) {
        for (Passageiro p : passageiros) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean atualizar(Passageiro novoPassageiro) {
        for (int i = 0; i < passageiros.size(); i++) {
            if (passageiros.get(i).getId() == novoPassageiro.getId()) {
                passageiros.set(i, novoPassageiro);
                return true;
            }
        }
        return false;
    }
}
