package nje.ea.eabeadando;

import javafx.scene.control.Alert;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MNB {
    public static void getData(String currency, String startDate, String endDate, boolean openResult) {
        try {
            // Az erőforrás elérési útja a JAR-ban
            String jarResourcePath = "/nje/ea/eabeadando/SOAP-MNB.jar";
            File tempJarFile = File.createTempFile("SOAP-MNB", ".jar");
            tempJarFile.deleteOnExit(); // Ideiglenes fájl automatikus törlése a program kilépésekor

            // A JAR-ból másoljuk ki a másik JAR fájlt egy ideiglenes fájlba
            try (InputStream inputStream = MNB.class.getResourceAsStream(jarResourcePath);
                 FileOutputStream outputStream = new FileOutputStream(tempJarFile)) {
                if (inputStream == null) {
                    throw new IOException("Nem található az erőforrás: " + jarResourcePath);
                }
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            File dataFolder = new File("c:\\adatok");

            // Ellenőrzi, hogy létezik-e a mappa, ha nem, létrehozza
            if (!dataFolder.exists()) {
                boolean isCreated = dataFolder.mkdirs();
                if (!isCreated) {
                    throw new IOException("A c:\\adatok mappa létrehozása nem sikerült!");
                }
            }

            // ProcessBuilder a másik JAR futtatására
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", tempJarFile.getAbsolutePath(), currency, startDate, endDate);

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

            if (openResult) {
                // 5. c:\adatok mappa megnyitása
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
