package com.example.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Voo;

public class VooRepository implements IRepository<Voo> {
    private ArrayList<Voo> voos;
    private static final String ARQUIVO = "voos.dat";

    public VooRepository() {
        voos = carregarArquivo();
    }

    @Override
    public void adicionar(Voo voo) {
        voos.add(voo);
        salvarArquivo();
    }

    @Override
    public void remover(Voo voo) {
        voos.remove(voo);
        salvarArquivo();
    }

    @Override
    public List<Voo> listarTodos() {
        return new ArrayList<>(voos);
    }

    @Override
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
                salvarArquivo();
                return true;
            }
        }
        return false;
    }

    private void salvarArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(voos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Voo> carregarArquivo() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Voo>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void limparRepositorio() {
    this.voos.clear();
    }

    @Override
    public int contarVoos() {
    return this.voos.size();
}
}
