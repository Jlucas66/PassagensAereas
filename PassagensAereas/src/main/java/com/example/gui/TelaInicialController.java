package com.example.gui;

import java.io.IOException;
import java.time.LocalDate;

import com.example.controllers.Fachada;
import com.example.models.Voo;
import com.example.models.VooInternacional;
import com.example.models.VooNacional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaInicialController {

    private final  Fachada fachada = new Fachada();

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

    @FXML
    private Label lblPrecoCalculado;

    @FXML
    private CheckBox chkInternacional;

    private double precoTotalCalculado = 0.0;

    @FXML
    private void initialize() {
        dtpDataVoo.setValue(LocalDate.now()); 
    }


    
    @FXML
    private void sair(ActionEvent event) {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void calcularPreco(ActionEvent event) {
    try {
        if (txtOrigem.getText().isEmpty() || txtDestino.getText().isEmpty()) {
            lblPrecoCalculado.setText("Preencha origem e destino");
            return;
        }

        int quantidade = Integer.parseInt(txtQuantidadeNoVoo.getText());

        double precoBase = 500.0;
        precoTotalCalculado = precoBase * quantidade;

        lblPrecoCalculado.setText("Preço: R$ " + String.format("%.2f", precoTotalCalculado));
    } catch (NumberFormatException e) {
        lblPrecoCalculado.setText("Quantidade inválida");
    }
    }

    @FXML
    private void comprarPassagem(ActionEvent event) {
        if (validarCampos()) {
            try {
                String origem = txtOrigem.getText();
                String destino = txtDestino.getText();
                LocalDate data = dtpDataVoo.getValue();
                int capacidade = Integer.parseInt(txtQuantidadeNoVoo.getText());
                double precoBase = 500.0;

                Voo voo;


                if (chkInternacional.isSelected()) {
                    voo = new VooInternacional(origem, destino, data, capacidade, origem, destino, precoBase);
                } else {
                    voo = new VooNacional(origem, destino, data, capacidade, origem, destino, precoBase);
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/passagensaereas/TelaInfoPassagem.fxml"));
                Parent root = loader.load();

                TelaInfoPassagemController controller = loader.getController();
                controller.setVooSelecionado(voo);

                Stage stage = (Stage) btnComprarPassagem.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Informações da Passagem");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao abrir tela de compra");
            }
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
