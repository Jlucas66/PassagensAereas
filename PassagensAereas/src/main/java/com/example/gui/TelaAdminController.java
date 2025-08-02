package com.example.gui;

import java.util.List;
import java.util.stream.Collectors;

import com.example.models.Passagem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaAdminController {

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnAplicarFiltro;

    @FXML
    private TableView<Passagem> tbPassagens;

    @FXML
    private TableColumn<Passagem, String> colPassageiro;

    @FXML
    private TableColumn<Passagem, String> colCPF;

    @FXML
    private TableColumn<Passagem, String> colOrigem;

    @FXML
    private TableColumn<Passagem, String> colDestino;

    @FXML
    private TableColumn<Passagem, String> colData;

    @FXML
    private TableColumn<Passagem, String> colPreco;

    private final ObservableList<Passagem> todasPassagens = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Configura as colunas
        colPassageiro.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getPassageiro().getNome()));
        colCPF.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getPassageiro().getCpf()));
        colOrigem.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getOrigem()));
        colDestino.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getDestino()));
        colData.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getDataPartida().toString()));
        colPreco.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                String.format("R$ %.2f", cellData.getValue().getVoo().calcularPrecoFinal())));


        // Exibe todas inicialmente
        tbPassagens.setItems(todasPassagens);
    }

    @FXML
    private void aplicarFiltro() {
        String termo = txtPesquisa.getText().trim().toLowerCase();

        if (termo.isEmpty()) {
            tbPassagens.setItems(todasPassagens);
            return;
        }

        List<Passagem> filtradas = todasPassagens.stream()
            .filter(p -> 
                p.getPassageiro().getNome().toLowerCase().contains(termo) ||
                p.getPassageiro().getCpf().toLowerCase().contains(termo) ||
                p.getVoo().getOrigem().toLowerCase().contains(termo) ||
                p.getVoo().getDestino().toLowerCase().contains(termo) ||
                p.getVoo().getDataPartida().toString().contains(termo) ||
                String.valueOf(p.getVoo().calcularPrecoFinal()).contains(termo)
            )
            .collect(Collectors.toList());

        tbPassagens.setItems(FXCollections.observableArrayList(filtradas));
    }

    @FXML
    private void sair() {
        System.exit(0);
    }

    @FXML
    private void voltar() {
        System.out.println("Voltando à tela anterior...");
        // aqui você pode carregar a tela anterior se desejar
    }

    @FXML
    private void abrirTelaCadastrarVoo() {
        System.out.println("Abrir tela de cadastro de voo...");
        // aqui você pode carregar a tela de cadastro de voo
    }
}
