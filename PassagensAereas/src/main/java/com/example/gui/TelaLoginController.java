package com.example.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginController {

    @FXML
    private TextField txtCPF;

    @FXML
    private PasswordField pxtSenha;

    @FXML
    private Button btnRealizarLogin;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnSair;

    @FXML
    private void realizarLogin() {
        String cpf = txtCPF.getText();
        String senha = pxtSenha.getText();

        // Aqui você colocaria a lógica de autenticação
        System.out.println("Tentando login com CPF: " + cpf + " e senha: " + senha);

        if (cpf.equals("12345678900") && senha.equals("senha123")) {
            System.out.println("Login bem-sucedido!");
            // navegar para próxima tela...
        } else {
            System.out.println("CPF ou senha incorretos.");
            // exibir alerta ou mensagem
        }
    }

    @FXML
    private void voltar() {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/passagensaereas/TelaInicial.fxml"));
        Parent loginRoot = fxmlLoader.load();

        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        Scene scene = new Scene(loginRoot);
        stage.setScene(scene);
        stage.setTitle("Tela de Login");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Erro ao carregar a tela de login");
    }
    }

    @FXML
    private void sair() {
        System.out.println("Encerrando aplicação...");
        System.exit(0);
    }
}
