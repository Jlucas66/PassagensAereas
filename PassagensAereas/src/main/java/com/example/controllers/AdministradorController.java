package com.example.controllers;

import java.util.List;
import java.util.Optional;

import com.example.models.Administrador;
import com.example.repositories.IRepository;

public class AdministradorController {
    private final IRepository<Administrador> repositorio;

    public AdministradorController(IRepository<Administrador> repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarAdministrador(String nome, String email, String senha) {
        Administrador administrador = new Administrador(nome, email, senha);
        repositorio.adicionar(administrador);
    }

    public Optional<Administrador> buscarAdministradorPorId(int id) {
        return Optional.ofNullable(repositorio.buscarPorId(id));
    }

    public boolean atualizarAdministrador(Administrador administradorAtualizado) {
        if (administradorAtualizado == null) {
            throw new IllegalArgumentException("Administrador não pode ser nulo");
        }
        return repositorio.atualizar(administradorAtualizado);
    }

    public boolean removerAdministrador(int id) {
        Administrador administrador = repositorio.buscarPorId(id);
        if (administrador != null) {
            repositorio.remover(administrador);
            return true;
        }
        return false;
    }

    public List<Administrador> listarAdministradores() {
        return repositorio.listarTodos();
    }

    public boolean confirmarLogin(String email, String senha) {
        for (Administrador administrador : repositorio.listarTodos()) {
            if (administrador.getEmail().equals(email) && administrador.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
