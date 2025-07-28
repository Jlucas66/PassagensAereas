package com.example.repositories;

import java.util.List;
import java.util.Optional;

import com.example.models.Voo;

public interface IVooRepository {
        void adicionar(Voo voo);
    List<Voo> listarTodos();
    Optional<Voo> buscarPorId(int id);
    boolean remover(int id);
    void limpar();
    boolean existe(int id);
    int contar();
    
}
