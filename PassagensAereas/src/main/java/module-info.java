module com.example.passagensaereas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.passagensaereas to javafx.fxml;
    exports com.example.passagensaereas;

    exports com.example.gui;
    opens com.example.gui to javafx.fxml;
}