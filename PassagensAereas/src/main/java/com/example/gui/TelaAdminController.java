package com.example.gui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.controllers.Fachada;
import com.example.models.Passageiro;
import com.example.models.Passagem;
import com.example.models.Voo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaAdminController {

    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private final Fachada fachada = new Fachada();

    // Elementos da interface
    @FXML private ListView<String> listaPassagens;
    @FXML private TextField txtPesquisa;
    @FXML private Button btnAplicarFiltro;
    @FXML private Button btnSair;
    @FXML private Button btnVoltar;
    @FXML private Button btnCadastrarVoo;

    @FXML
    public void initialize() {
        System.out.println("Inicializando tela de administração...");
        carregarTodasPassagens();
    }

    /**
     * Carrega e exibe todas as passagens do sistema
     */
    private void carregarTodasPassagens() {
        try {
            List<Passagem> passagens = fachada.listarPassagens();
            
            if (passagens.isEmpty()) {
                listaPassagens.setPlaceholder(new Label("Nenhuma passagem cadastrada"));
                return;
            }

            ObservableList<String> dadosFormatados = FXCollections.observableArrayList();
            passagens.forEach(passagem -> {
                dadosFormatados.add(formatarDadosPassagem(passagem));
            });
            
            listaPassagens.setItems(dadosFormatados);
            System.out.println(passagens.size() + " passagens carregadas com sucesso");
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar passagens: " + e.getMessage());
            listaPassagens.setPlaceholder(new Label("Erro ao carregar dados"));
            exibirMensagemErro("Erro", "Não foi possível carregar as passagens");
        }
    }

    /**
     * Formata os dados da passagem para exibição
     */
    private String formatarDadosPassagem(Passagem passagem) {
        Passageiro passageiro = passagem.getPassageiro();
        Voo voo = passagem.getVoo();
        
        return String.format(
            "ID Passagem: %d\n" +
            "Passageiro: %s (CPF: %s)\n" +
            "Voo: %s → %s | Data: %s\n" +
            "Capacidade: %d | Preço: R$ %.2f\n" +
            "----------------------------------------",
            passagem.getId(),
            passageiro.getNome(),
            passageiro.getCpf(),
            voo.getOrigem(),
            voo.getDestino(),
            voo.getDataPartida().format(DATE_FORMATTER),
            voo.getCapacidade(),
            voo.calcularPrecoFinal()
        );
    }

    /**
     * Aplica filtro nas passagens conforme texto digitado
     */
    @FXML
    private void aplicarFiltro() {
        String termo = txtPesquisa.getText().trim().toLowerCase();
        
        if (termo.isEmpty()) {
            carregarTodasPassagens();
            return;
        }

        try {
            List<Passagem> todasPassagens = fachada.listarPassagens();
            ObservableList<String> passagensFiltradas = FXCollections.observableArrayList();
            
            for (Passagem p : todasPassagens) {
                String textoBusca = String.format("%d %s %s %s %s %d %.2f",
                    p.getId(),
                    p.getPassageiro().getNome(),
                    p.getPassageiro().getCpf(),
                    p.getVoo().getOrigem(),
                    p.getVoo().getDestino(),
                    p.getVoo().getCapacidade(),
                    p.getVoo().calcularPrecoFinal()
                ).toLowerCase();
                
                if (textoBusca.contains(termo)) {
                    passagensFiltradas.add(formatarDadosPassagem(p));
                }
            }
            
            listaPassagens.setItems(passagensFiltradas);
            
            if (passagensFiltradas.isEmpty()) {
                listaPassagens.setPlaceholder(new Label("Nenhuma passagem encontrada"));
            }
        } catch (Exception e) {
            exibirMensagemErro("Erro", "Falha ao aplicar filtro");
        }
    }

    // Métodos de navegação
    @FXML private void sair() { System.exit(0); }

    @FXML 
    private void voltar() {
        carregarTela("/com/example/passagensaereas/TelaInicial.fxml", "Tela Inicial");
    }

    @FXML 
    private void abrirTelaCadastrarVoo() {
        carregarTela("/com/example/passagensaereas/TelaCadastrarVoo.fxml", "Cadastrar Voo");
    }

    /**
     * Carrega uma nova tela
     */
    private void carregarTela(String fxml, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) listaPassagens.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            exibirMensagemErro("Erro", "Não foi possível carregar a tela");
        }
    }

    /**
     * Exibe mensagem de erro
     */
    private void exibirMensagemErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}