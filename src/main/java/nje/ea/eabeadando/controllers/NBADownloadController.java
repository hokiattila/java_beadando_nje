package nje.ea.eabeadando.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import nje.ea.eabeadando.MNB;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NBADownloadController {

        public void onDownloadClicked(ActionEvent actionEvent) {
            try {
                // 1. Mai és három nappal ezelőtti dátum kiszámítása
                LocalDate today = LocalDate.now();
                LocalDate threeDaysAgo = today.minusDays(3);

                // Dátumok formázása
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String todayStr = today.format(formatter);
                String threeDaysAgoStr = threeDaysAgo.format(formatter);

                // Kérés indítés
                MNB.getData("EUR",threeDaysAgoStr, todayStr, true);

            } catch (Exception e) {
                // Hiba esetén felugró ablak jelenik meg
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText("Hiba történt a művelet során");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }

    }
