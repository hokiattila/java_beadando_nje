package nje.ea.eabeadando.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NBAFormController {
    @FXML
    private ComboBox<String> currencyComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    public void initialize() {
        currencyComboBox.getItems().addAll("EUR", "USD", "GBP", "CHF", "JPY");
    }

    public void onDownloadClicked(ActionEvent actionEvent) {
        try {
            // 1. Inputok validálása
            String currency = currencyComboBox.getValue();
            if (currency == null || currency.isEmpty()) {
                throw new IllegalArgumentException("Kérjük, válasszon egy valutát a listából!");
            }

            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            if (startDate == null) {
                throw new IllegalArgumentException("Kérjük, válasszon egy kezdődátumot!");
            }
            if (endDate == null) {
                throw new IllegalArgumentException("Kérjük, válasszon egy végdátumot!");
            }
            if (startDate.isAfter(endDate)) {
                throw new IllegalArgumentException("A kezdődátum nem lehet későbbi, mint a végdátum!");
            }

            // 2. SOAP-MNB.jar futtatása
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startDateStr = startDate.format(formatter);
            String endDateStr = endDate.format(formatter);

            String jarPath = "src/main/resources/nje/ea/eabeadando/SOAP-MNB.jar";
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "java", "-jar", jarPath, currency, startDateStr, endDateStr);

            // Parancs futtatása
            Process process = processBuilder.start();
            int exitCode = process.waitFor(); // Várakozás a folyamat befejezésére

            if (exitCode != 0) {
                throw new RuntimeException("SOAP-MNB.jar futtatása sikertelen! Hibakód: " + exitCode);
            }

            // 3. MNB.txt fájl megnyitása
            File file = new File("c:\\adatok\\MNB.txt");
            if (!file.exists()) {
                throw new IOException("Nem található a letöltött MNB.txt fájl: " + file.getAbsolutePath());
            }

            // Fájl és mappa megnyitása
            Desktop.getDesktop().open(file);
            Desktop.getDesktop().open(new File("c:\\adatok"));

        } catch (IllegalArgumentException e) {
            // Felhasználói hibák kezelése
            showAlert(Alert.AlertType.WARNING, "Figyelem!", e.getMessage());
        } catch (Exception e) {
            // Egyéb hibák kezelése
            showAlert(Alert.AlertType.ERROR, "Hiba", "Valami hiba történt: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
