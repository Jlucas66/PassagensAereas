package com.example.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.example.models.Passageiro;

public class PassageiroRepository implements IRepository<Passageiro> {
    private ArrayList<Passageiro> passageiros;
    private static final String ARQUIVO = "passageiros.dat";

    public PassageiroRepository() {
        passageiros = carregarArquivo();
    }

    @Override
    public void adicionar(Passageiro passageiro) {
        passageiros.add(passageiro);
        salvarArquivo();
    }

    @Override
    public void remover(Passageiro passageiro) {
        passageiros.remove(passageiro);
        salvarArquivo();
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
                salvarArquivo();
                return true;
            }
        }
        return false;
    }

    private void salvarArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(passageiros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Passageiro> carregarArquivo() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Passageiro>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void limparRepositorio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'limparRepositorio'");
    }

    @Override
    public int contarVoos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contarVoos'");
    }
}
