package nje.ea.eabeadando.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

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

                // 2. Ellenőrzés és mappa létrehozása, ha nem létezik
                File dataFolder = new File("c:\\adatok");
                if (!dataFolder.exists()) {
                    boolean isCreated = dataFolder.mkdirs(); // Mappák létrehozása
                    if (!isCreated) {
                        throw new IOException("A c:\\adatok mappa létrehozása nem sikerült!");
                    }
                }

                // 3. SOAP-MNB.jar futtatása
                String jarPath = "src/main/resources/nje/ea/eabeadando/SOAP-MNB.jar";
                ProcessBuilder processBuilder = new ProcessBuilder(
                        "java", "-jar", jarPath, "EUR", threeDaysAgoStr, todayStr);

                // Parancs futtatása
                Process process = processBuilder.start();
                int exitCode = process.waitFor(); // Várakozás a folyamat befejezésére

                if (exitCode != 0) {
                    throw new RuntimeException("SOAP-MNB.jar futtatása sikertelen! Hibakód: " + exitCode);
                }

                // 4. MNB.txt fájl megnyitása
                File file = new File("c:\\adatok\\MNB.txt");
                if (!file.exists()) {
                    throw new IOException("Nem található a letöltött MNB.txt fájl: " + file.getAbsolutePath());
                }

                // 5. c:\adatok mappa megnyitása
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(dataFolder);
                } else {
                    throw new UnsupportedOperationException("A rendszer nem támogatja a mappanyitást");
                }

                // Alapértelmezett rendszerprogrammal nyitja meg az MNB.txt-t
                Desktop.getDesktop().open(file);

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
