package nje.ea.eabeadando.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import nje.ea.eabeadando.MainApp;

import java.io.IOException;

public class MainController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private AnchorPane contentPane;

    @FXML
    public void initialize() {
        System.out.println("MainController initialized");
    }


    @FXML
    private void handleOlvas() {
        loadView("/nje/ea/eabeadando/read-view.fxml");
    }

    @FXML
    private void handleOlvas2() {
        loadView("/nje/ea/eabeadando/read-view-2.fxml");
    }

    @FXML
    private void handleIr() {
        loadView("/nje/ea/eabeadando/ir-view.fxml");
    }

    @FXML
    private void handleModosit() {
        loadView("/nje/ea/eabeadando/modosit-view.fxml");
    }

    @FXML
    private void handleTorol() {
        loadView("/nje/ea/eabeadando/torol-view.fxml");
    }

    @FXML
    private void handleLetoltes(ActionEvent event) {
        System.out.println("Letöltés menüpont kiválasztva.");
    }

    @FXML
    private void handleLetoltes2(ActionEvent event) {
        System.out.println("Letöltés2 menüpont kiválasztva.");
    }

    @FXML
    private void handleGrafikon(ActionEvent event) {
        System.out.println("Grafikon menüpont kiválasztva.");
    }

    @FXML
    private void handleParhuzamos(ActionEvent event) {
        System.out.println("Párhuzamos menüpont kiválasztva.");
    }

    @FXML
    private void handleSzamlainfo(ActionEvent event) {
        System.out.println("Számlainformációk menüpont kiválasztva.");
    }

    @FXML
    private void handleAktualisArak(ActionEvent event) {
        System.out.println("Aktuális árak menüpont kiválasztva.");
    }

    @FXML
    private void handleHistorikusArak(ActionEvent event) {
        System.out.println("Hisztorikus árak menüpont kiválasztva.");
    }

    @FXML
    private void handlePozicioNyitas(ActionEvent event) {
        System.out.println("Pozíció nyitás menüpont kiválasztva.");
    }

    @FXML
    private void handlePozicioZaras(ActionEvent event) {
        System.out.println("Pozíció zárás menüpont kiválasztva.");
    }

    @FXML
    private void handleNyitottPoziciok(ActionEvent event) {
        System.out.println("Nyitott pozíciók menüpont kiválasztva.");
    }

    private void loadView(String fxmlPath) {
        try {
            System.out.println("Attempting to load view: " + fxmlPath);
            Node node = FXMLLoader.load(MainApp.class.getResource(fxmlPath));

            System.out.println("ContentPane children count before clear: " + contentPane.getChildren().size());
            contentPane.getChildren().clear();
            System.out.println("ContentPane children count after clear: " + contentPane.getChildren().size());
            contentPane.getChildren().add(node);
            System.out.println("ContentPane children count after add: " + contentPane.getChildren().size());


            // Méretezési szabályok az AnchorPane-n
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);

        } catch (IOException e) {
            System.err.println("Error loading view: " + fxmlPath);
            e.printStackTrace();
        }
    }



    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hiba");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
