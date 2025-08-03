package com.example.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Administrador;

public class AdministradorRepository implements IRepository<Administrador> {
    private ArrayList<Administrador> administradores;
    private static final String ARQUIVO = "administradores.dat";

    public AdministradorRepository() {
        administradores = carregarArquivo();
    }

    @Override
    public void adicionar(Administrador administrador) {
        administradores.add(administrador);
        salvarArquivo();
    }

    @Override
    public void remover(Administrador administrador) {
        administradores.remove(administrador);
        salvarArquivo();
    }

    @Override
    public List<Administrador> listarTodos() {
        return new ArrayList<>(administradores);
    }

    @Override
    public Administrador buscarPorId(int id) {
        for (Administrador a : administradores) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    @Override
    public boolean atualizar(Administrador novoAdministrador) {
        for (int i = 0; i < administradores.size(); i++) {
            if (administradores.get(i).getId() == novoAdministrador.getId()) {
                administradores.set(i, novoAdministrador);
                salvarArquivo();
                return true;
            }
        }
        return false;
    }

    private void salvarArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(administradores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar administradores: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private ArrayList<Administrador> carregarArquivo() {
        File file = new File(ARQUIVO);
        if (!file.exists()) {
            System.out.println("Arquivo de administradores não encontrado. Criando lista vazia.");
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Administrador>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar administradores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void limparRepositorio() {
        throw new UnsupportedOperationException("Unimplemented method 'limparRepositorio'");
    }

    @Override
    public int contarVoos() {
        throw new UnsupportedOperationException("Unimplemented method 'contarVoos'");
    }
}
