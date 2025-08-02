package com.example.gui;

import java.io.IOException;
import java.time.LocalDate;

import com.example.controllers.Fachada; // ou VooInternacional, se quiser incluir também
import com.example.models.Voo;
import com.example.models.VooNacional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TelaCadastrarVooController {

    @FXML private TextField txtOrigem;
    @FXML private TextField txtDestino;
    @FXML private DatePicker dpDataPartida;
    @FXML private TextField txtCapacidade;
    @FXML private TextField txtPrecoBase;
    @FXML private Button btnFinalizarCadastro;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;
    @FXML private Label lblMensagem;

    private final Fachada fachada = new Fachada();

    @FXML
    private void finalizarCadastroVoo() {
        try {
            String origem = txtOrigem.getText().trim();
            String destino = txtDestino.getText().trim();
            LocalDate data = dpDataPartida.getValue();
            int capacidade = Integer.parseInt(txtCapacidade.getText().trim());
            double precoBase = Double.parseDouble(txtPrecoBase.getText().trim());

            if (origem.isEmpty() || destino.isEmpty() || data == null) {
                mostrarMensagem("Preencha todos os campos obrigatórios.", Color.RED);
                return;
            }

            Voo voo = new VooNacional(origem, destino, data, capacidade, origem, destino, precoBase);
            fachada.cadastrarVoo(voo);

            mostrarMensagem("Voo cadastrado com sucesso!", Color.GREEN);
            limparCampos();

        } catch (NumberFormatException e) {
            mostrarMensagem("Capacidade ou preço inválido.", Color.RED);
        } catch (IllegalArgumentException e) {
            mostrarMensagem(e.getMessage(), Color.RED);
        }
    }

    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/passagensaereas/TelaAdmin.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tela do Administrador");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            exibirAlerta("Erro", "Não foi possível voltar para a tela do administrador.");
        }
    }

    @FXML
    private void sair() {
        System.exit(0);
    }

    private void limparCampos() {
        txtOrigem.clear();
        txtDestino.clear();
        dpDataPartida.setValue(null);
        txtCapacidade.clear();
        txtPrecoBase.clear();
    }

    private void mostrarMensagem(String msg, Color cor) {
        if (lblMensagem != null) {
            lblMensagem.setText(msg);
            lblMensagem.setTextFill(cor);
        } else {
            System.out.println(msg);
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
