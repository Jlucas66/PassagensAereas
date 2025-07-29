package com.example.repositories;

import java.util.List;


public interface IRepository<T> {
    void adicionar(T entidade);
    void remover(T entidade);
    List<T> listarTodos();
    T buscarPorId(int id);
    boolean atualizar(T entidade);
    
}
