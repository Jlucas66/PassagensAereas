package com.example.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.example.controllers.Fachada;
import com.example.models.Passageiro;
import com.example.models.Voo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaInfoPassagemController implements Initializable {

    private final Fachada fachada = new Fachada();

    @FXML private TextField txtNome;
    @FXML private TextField txtCPF;
    @FXML private TextField txtOrigem;
    @FXML private TextField txtDestino;
    @FXML private DatePicker dtpDataVoo;
    @FXML private TextField txtQuantidadeNoVoo;
    @FXML private ComboBox<Voo> cbxVoo;
    @FXML private Button btnFinalizarReserva;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;
    private Voo vooSelecionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarVoos();
        dtpDataVoo.setValue(LocalDate.now());
    }

    private void carregarVoos() {
        List<Voo> voos = fachada.listarVoos();
        ObservableList<Voo> lista = FXCollections.observableArrayList(voos);
        cbxVoo.setItems(lista);


        cbxVoo.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Voo voo, boolean empty) {
                super.updateItem(voo, empty);
                setText(empty || voo == null ? null : voo.toString());
            }
        });
        cbxVoo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Voo voo, boolean empty) {
                super.updateItem(voo, empty);
                setText(empty || voo == null ? null : voo.toString());
            }
        });
    }

    @FXML
    private void finalizarReserva(ActionEvent event) {
        String nome = txtNome.getText();
        String cpf = txtCPF.getText();
        Voo vooSelecionado = cbxVoo.getValue();

        if (nome.isEmpty() || cpf.isEmpty() || vooSelecionado == null) {
            exibirAlerta("Preencha todos os campos obrigatórios.", "N\u00e3o foi poss\u00edvel voltar para a tela inicial.");
            return;
        }

        try {
            Passageiro passageiro = new Passageiro(nome, cpf);
            fachada.cadastrarPassageiro(nome, cpf);
            fachada.comprarPassagem(passageiro, vooSelecionado);

            exibirAlerta("Reserva realizada com sucesso!", "N\u00e3o foi poss\u00edvel voltar para a tela inicial.");
        } catch (Exception e) {
            exibirAlerta("Erro ao realizar reserva: " + e.getMessage(), "N\u00e3o foi poss\u00edvel voltar para a tela inicial.");
        }
    }

    @FXML
    private void voltar(ActionEvent event) { 
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
    private void sair(ActionEvent event) {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    private void exibirAlerta(String mensagem, String não_foi_possível_voltar_para_a_tela_inici) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void setVooSelecionado(Voo voo) {
        this.vooSelecionado = voo;
        if (voo != null) {
            txtOrigem.setText(voo.getOrigem());
            txtDestino.setText(voo.getDestino());
            dtpDataVoo.setValue(voo.getDataPartida());
            txtQuantidadeNoVoo.setText(String.valueOf(voo.getCapacidade()));
            cbxVoo.getSelectionModel().select(voo);
        }
    }
}

