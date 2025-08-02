package com.example.controllers;

import java.util.List;

import com.example.models.*;
import com.example.repositories.*;

public class Fachada {

    private final VooController vooController = new VooController();
    private final PassageiroController passageiroController;
    private final PassagemController passagemController;
    private final AdministradorController administradorController;

    public Fachada() {
        this.passageiroController = new PassageiroController(new PassageiroRepository());
        this.passagemController = new PassagemController(new PassagemRepository());
        this.administradorController = new AdministradorController(new AdministradorRepository());
    }

    // ---------------- VOOS ----------------

    public void cadastrarVoo(Voo voo) throws IllegalArgumentException {
        vooController.cadastrarVoo(voo);
    }

    public List<Voo> listarVoos() {
        return vooController.listarVoos();
    }

    public Voo buscarVooPorId(int id) {
        return vooController.buscarVooPorId(id).orElse(null);
    }

    public boolean removerVoo(int id) {
        return vooController.removerVoo(id);
    }

    public boolean vooExiste(int id) {
        return vooController.vooExiste(id);
    }

    public int contarVoos() {
        return vooController.contarVoos();
    }

    // ---------------- PASSAGEIROS ----------------

    public void cadastrarPassageiro(String nome, String cpf) {
        passageiroController.cadastrarPassageiro(nome, cpf);
    }

    public Passageiro buscarPassageiroPorId(int id) {
        return passageiroController.buscarPorId(id).orElse(null);
    }

    public List<Passageiro> listarPassageiros() {
        return passageiroController.listarPassageiros();
    }

    public boolean atualizarPassageiro(Passageiro passageiroAtualizado) {
        return passageiroController.atualizarPassageiro(passageiroAtualizado);
    }

    // ---------------- PASSAGENS ----------------

    public void comprarPassagem(Passageiro passageiro, Voo voo) {
        passagemController.comprarPassagem(passageiro, voo);
    }

    public List<Passagem> listarPassagens() {
        return passagemController.listarTodas();
    }

    public Passagem buscarPassagemPorId(int id) {
        return passagemController.buscarPorId(id).orElse(null);
    }

    public boolean atualizarPassagem(Passagem passagemAtualizada) {
        return passagemController.atualizarPassagem(passagemAtualizada);
    }

    // ---------------- ADMINISTRADORES ----------------

    public void cadastrarAdministrador(String nome, String email, String senha) {
        administradorController.cadastrarAdministrador(nome, email, senha);
    }

    public Administrador buscarAdministradorPorId(int id) {
        return administradorController.buscarAdministradorPorId(id).orElse(null);
    }

    public List<Administrador> listarAdministradores() {
        return administradorController.listarAdministradores();
    }

    public boolean atualizarAdministrador(Administrador administradorAtualizado) {
        return administradorController.atualizarAdministrador(administradorAtualizado);
    }

    public boolean removerAdministrador(int id) {
        return administradorController.removerAdministrador(id);
    }

    public boolean confirmarLoginAdministrador(String email, String senha) {
        return administradorController.confirmarLogin(email, senha);
    }
}
