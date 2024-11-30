module nje.ea.eabeadando {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;

    opens nje.ea.eabeadando to javafx.fxml;
    exports nje.ea.eabeadando;

    opens nje.ea.eabeadando.controllers to javafx.fxml;
    opens nje.ea.eabeadando.models to javafx.base;
    exports nje.ea.eabeadando.controllers;
}