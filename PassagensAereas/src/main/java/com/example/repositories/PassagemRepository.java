package com.example.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Passagem;

public class PassagemRepository implements IRepository<Passagem> {
    private ArrayList<Passagem> passagens;
    private static final String ARQUIVO = "passagens.dat";

    public PassagemRepository() {
        passagens = carregarArquivo();
    }

    @Override
    public void adicionar(Passagem passagem) {
        passagens.add(passagem);
        salvarArquivo();
    }

    @Override
    public void remover(Passagem passagem) {
        passagens.remove(passagem);
        salvarArquivo();
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
                salvarArquivo();
                return true;
            }
        }
        return false;
    }

    private void salvarArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(passagens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Passagem> carregarArquivo() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Passagem>) in.readObject();
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
