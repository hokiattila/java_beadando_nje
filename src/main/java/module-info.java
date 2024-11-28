module nje.ea.eabeadando {
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
    requires java.sql;

    opens nje.ea.eabeadando to javafx.fxml;
    exports nje.ea.eabeadando;

    exports nje.ea.eabeadando.controllers to javafx.fxml;
    opens nje.ea.eabeadando.controllers to javafx.fxml;
    opens nje.ea.eabeadando.models to javafx.base;
}