package com.example.gui;

import java.io.IOException;

import com.example.controllers.Fachada;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginController {

    private final Fachada fachada = new Fachada();

    @FXML private TextField txtCPF;
    @FXML private PasswordField pxtSenha;
    @FXML private Button btnRealizarLogin;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    @FXML
    private void realizarLogin() {
        String cpf = txtCPF.getText();
        String senha = pxtSenha.getText();

        if (cpf.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Campos obrigatórios", "Informe CPF e senha para continuar.");
            return;
        }

        boolean autenticado = fachada.confirmarLoginAdministrador(cpf, senha);

        if (autenticado) {
            System.out.println("Login bem-sucedido!");
            abrirTelaAdministrador();
        } else {
            exibirAlerta("Erro de login", "CPF ou senha inválidos.");
        }
    }

    private void abrirTelaAdministrador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/passagensaereas/TelaAdministrador.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnRealizarLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Área do Administrador");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            exibirAlerta("Erro", "Não foi possível abrir a tela do administrador.");
        }
    }

    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/passagensaereas/TelaInicial.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tela Inicial");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            exibirAlerta("Erro", "Não foi possível voltar para a tela inicial.");
        }
    }

    @FXML
    private void sair() {
        System.out.println("Encerrando aplicação...");
        System.exit(0);
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
