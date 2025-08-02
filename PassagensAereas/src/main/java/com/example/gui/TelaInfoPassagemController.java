package com.example.gui;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Passageiro;
import com.example.models.Passagem;
import com.example.models.Voo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class TelaInfoPassagemController {

    @FXML private TextField txtNome;
    @FXML private TextField txtCPF;
    @FXML private ComboBox<Voo> cbxVoo;

    // Lista pública de passagens (usada por outras telas)
    public static List<Passagem> listaPassagens = new ArrayList<>();

    @FXML
    private void initialize() {
        // Preencher o ComboBox com os voos cadastrados
        cbxVoo.setItems(FXCollections.observableArrayList(TelaCadastrarVooController.listaVoos));

        // Personalizar como o voo é exibido
        cbxVoo.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Voo voo, boolean empty) {
                super.updateItem(voo, empty);
                setText(empty || voo == null ? null : formatarVoo(voo));
            }
        });

        cbxVoo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Voo voo, boolean empty) {
                super.updateItem(voo, empty);
                setText(empty || voo == null ? null : formatarVoo(voo));
            }
        });
    }

    private String formatarVoo(Voo voo) {
        return voo.getOrigem() + " → " + voo.getDestino() + " (" + voo.getDataPartida() + ")";
    }

    @FXML
    private void finalizarReserva() {
        String nome = txtNome.getText().trim();
        String cpf = txtCPF.getText().trim();
        Voo vooSelecionado = cbxVoo.getValue();

        if (nome.isEmpty() || cpf.isEmpty() || vooSelecionado == null) {
            mostrarAlerta("Erro", "Por favor, preencha todos os campos.");
            return;
        }

        Passageiro passageiro = new Passageiro(nome, cpf);
        Passagem passagem = new Passagem(passageiro, vooSelecionado);
        listaPassagens.add(passagem);

        mostrarAlerta("Sucesso", "Reserva concluída com sucesso!");
        limparCampos();
    }

    @FXML
    private void voltar() {
        // Aqui você pode trocar de tela (por exemplo, para tela inicial)
        System.out.println("Voltando para tela anterior...");
    }

    @FXML
    private void sair() {
        System.exit(0);
    }

    private void limparCampos() {
        txtNome.clear();
        txtCPF.clear();
        cbxVoo.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}

