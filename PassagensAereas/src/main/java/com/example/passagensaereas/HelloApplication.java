package com.example.passagensaereas;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/passagensaereas/TelaInicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 725);
        stage.setTitle("Aterrissar.com - Sistema de Passagens Aéreas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
