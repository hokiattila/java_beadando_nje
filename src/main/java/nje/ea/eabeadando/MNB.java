package nje.ea.eabeadando;

import javafx.scene.control.Alert;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MNB {
    public static void getData(String currency, String startDate, String endDate, boolean openResult) {
        try {
            String jarPath = "src/main/resources/nje/ea/eabeadando/SOAP-MNB.jar";
            File dataFolder = new File("c:\\adatok");

            // Ellenőrzi, hogy létezik-e a mappa, ha nem, létrehozza
            if (!dataFolder.exists()) {
                boolean isCreated = dataFolder.mkdirs();
                if (!isCreated) {
                    throw new IOException("A c:\\adatok mappa létrehozása nem sikerült!");
                }
            }

            // ProcessBuilder a JAR futtatására a szükséges JVM paraméterrel
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "java",
                    "--add-opens", "java.base/java.lang=ALL-UNNAMED", // Hozzáadjuk a szükséges JVM paramétert
                    "-jar", jarPath,
                    currency, startDate, endDate
            );

            // Parancs futtatása
            Process process = processBuilder.start();
            int exitCode = process.waitFor(); // Várakozás a folyamat befejezésére

            if (exitCode != 0) {
                throw new RuntimeException("SOAP-MNB.jar futtatása sikertelen! Hibakód: " + exitCode);
            }

            // MNB.txt fájl megnyitása
            File file = new File("c:\\adatok\\MNB.txt");
            if (!file.exists()) {
                throw new IOException("Nem található a letöltött MNB.txt fájl: " + file.getAbsolutePath());
            }

            if (openResult) {
                // c:\adatok mappa megnyitása
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(dataFolder);
                } else {
                    throw new UnsupportedOperationException("A rendszer nem támogatja a mappanyitást");
                }

                // Alapértelmezett rendszerprogrammal nyitja meg az MNB.txt-t
                Desktop.getDesktop().open(file);
            }
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
