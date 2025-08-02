package com.example.gui;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.controllers.Fachada;
import com.example.models.Passagem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaAdminController {

    private final Fachada fachada = new Fachada();

    @FXML private TextField txtPesquisa;
    @FXML private Button btnAplicarFiltro;
    @FXML private TableView<Passagem> tbPassagens;
    @FXML private TableColumn<Passagem, String> colPassageiro;
    @FXML private TableColumn<Passagem, String> colCPF;
    @FXML private TableColumn<Passagem, String> colOrigem;
    @FXML private TableColumn<Passagem, String> colDestino;
    @FXML private TableColumn<Passagem, String> colData;
    @FXML private TableColumn<Passagem, String> colPreco;

    private final ObservableList<Passagem> todasPassagens = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colPassageiro.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getPassageiro().getNome()));
        colCPF.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getPassageiro().getCpf()));
        colOrigem.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getOrigem()));
        colDestino.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getDestino()));
        colData.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getDataPartida().toString()));
        colPreco.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                String.format("R$ %.2f", cellData.getValue().getVoo().calcularPrecoFinal())));


        List<Passagem> lista = fachada.listarPassagens();
        todasPassagens.setAll(lista);

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
                String.format("%.2f", p.getVoo().calcularPrecoFinal()).contains(termo)
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/passagensaereas/TelaInicial.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tbPassagens.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tela Inicial");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            exibirErro("Erro ao voltar para a tela inicial.");
        }
    }

    @FXML
    private void abrirTelaCadastrarVoo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/passagensaereas/TelaCadastroVoo.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tbPassagens.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro de Voo");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            exibirErro("Erro ao abrir a tela de cadastro de voo.");
        }
    }

    private void exibirErro(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
