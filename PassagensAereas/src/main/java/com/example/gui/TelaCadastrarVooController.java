package com.example.gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Voo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TelaCadastrarVooController {

    // Campos da interface
    @FXML private TextField txtOrigem;
    @FXML private TextField txtDestino;
    @FXML private DatePicker dpDataPartida;
    @FXML private TextField txtCapacidade;
    @FXML private TextField txtPrecoBase;
    @FXML private Button btnFinalizarCadastro;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    // Mensagem opcional (caso queira adicionar no FXML mais tarde)
    @FXML private Label lblMensagem;

    // Lista de voos cadastrados (pública para outras telas acessarem, ou substitua por DAO)
    public static List<Voo> listaVoos = new ArrayList<>();

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

            mostrarMensagem("Voo cadastrado com sucesso!", Color.GREEN);
            limparCampos();

        } catch (NumberFormatException e) {
            mostrarMensagem("Capacidade ou preço inválido.", Color.RED);
        }
    }

    @FXML
    private void voltar() {
        System.out.println("Voltando à tela anterior...");
        // Aqui você pode usar NavigationHelper ou trocar de cena se desejar
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
            System.out.println(msg); // Fallback se não houver label visível
        }
    }
}

