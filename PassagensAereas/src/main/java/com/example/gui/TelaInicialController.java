package com.example.gui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaInicialController {

    // Elementos da interface
    @FXML
    private TextField txtOrigem;
    
    @FXML
    private TextField txtDestino;
    
    @FXML
    private DatePicker dtpDataVoo;
    
    @FXML
    private TextField txtQuantidadeNoVoo;
    
    @FXML
    private Button btnCalcularPreco;
    
    @FXML
    private Button btnComprarPassagem;
    
    @FXML
    private Button btnLogin;
    
    @FXML
    private Button btnSair;

    // Método de inicialização
    @FXML
    private void initialize() {
        // Configurações iniciais
        dtpDataVoo.setValue(LocalDate.now()); // Define a data atual como padrão
    }

    // Métodos para ações dos botões
    
    @FXML
    private void sair(ActionEvent event) {
        // Fecha a aplicação
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void calcularPreco(ActionEvent event) {
        try {
            // Validações básicas
            if (txtOrigem.getText().isEmpty() || txtDestino.getText().isEmpty()) {
                System.out.println("Por favor, preencha origem e destino");
                return;
            }
            
            int quantidade = Integer.parseInt(txtQuantidadeNoVoo.getText());
            LocalDate dataVoo = dtpDataVoo.getValue();
            
            // Lógica para cálculo de preço (simplificado)
            double precoBase = 500.0; // Valor base fictício
            double precoTotal = precoBase * quantidade;
            
            System.out.println("Preço calculado: R$" + precoTotal);
            
        } catch (NumberFormatException e) {
            System.out.println("Quantidade de pessoas inválida");
        }
    }

    @FXML
    private void comprarPassagem(ActionEvent event) {
        // Validações antes de comprar
        if (validarCampos()) {
            System.out.println("Passagem comprada com sucesso!");
            System.out.println("Detalhes:");
            System.out.println("Origem: " + txtOrigem.getText());
            System.out.println("Destino: " + txtDestino.getText());
            System.out.println("Data: " + dtpDataVoo.getValue());
            System.out.println("Passageiros: " + txtQuantidadeNoVoo.getText());
        }
    }

@FXML
private void abrirTelaLogin(ActionEvent event) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/passagensaereas/TelaLogin.fxml"));
        Parent loginRoot = fxmlLoader.load();

        Stage stage = (Stage) btnLogin.getScene().getWindow();
        Scene scene = new Scene(loginRoot);
        stage.setScene(scene);
        stage.setTitle("Tela de Login");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Erro ao carregar a tela de login");
    }
}


    // Método auxiliar para validação
    private boolean validarCampos() {
        if (txtOrigem.getText().isEmpty()) {
            System.out.println("Informe a origem");
            return false;
        }
        
        if (txtDestino.getText().isEmpty()) {
            System.out.println("Informe o destino");
            return false;
        }
        
        if (dtpDataVoo.getValue() == null) {
            System.out.println("Selecione uma data");
            return false;
        }
        
        try {
            int quantidade = Integer.parseInt(txtQuantidadeNoVoo.getText());
            if (quantidade <= 0) {
                System.out.println("Quantidade deve ser maior que zero");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida");
            return false;
        }
        
        return true;
    }
}
