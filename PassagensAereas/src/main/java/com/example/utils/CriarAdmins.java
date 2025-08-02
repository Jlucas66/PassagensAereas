package com.example.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Administrador;

public class CriarAdmins {
    public static void main(String[] args) {
        Administrador admin1 = new Administrador("Admin Um", "admin1@email.com", null);
        admin1.setSenha("1234"); 

        Administrador admin2 = new Administrador("Admin Dois", "admin2@email.com", null);
        admin2.setSenha("abcd");

        List<Administrador> admins = new ArrayList<>();
        admins.add(admin1);
        admins.add(admin2);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("administradores.dat"))) {
            oos.writeObject(admins);
            System.out.println("Administradores salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar administradores: " + e.getMessage());
        }
    }
}
