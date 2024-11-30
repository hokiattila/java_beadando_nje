package nje.ea.eabeadando.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import nje.ea.eabeadando.MNB;
import nje.ea.eabeadando.MainApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ChartController {

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public void initialize() {
        // Az mnb.txt fájl útvonala
        String filePath = "c:\\adatok\\mnb.txt";

        // Ellenőrizzük, hogy létezik-e a fájl
        File file = new File(filePath);
        if (file.exists()) {
            // Fájl létezik: dolgozzuk fel
            processFile(filePath);
        } else {
            // Fájl nem létezik: kérjünk adatokat az MNB API-tól
            requestDataFromMNB();
        }
    }

    private void processFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Az első sor a valuta típusa
            String currencyType = br.readLine();
            xAxis.setLabel("Dátum");
            yAxis.setLabel(currencyType + " Árfolyam");
            lineChart.setTitle(currencyType + " Árfolyamok"); // A grafikon címének beállítása

            // Új széria létrehozása
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(currencyType + " Árfolyamok");

            // Az adatok feldolgozása
            String dateLine;
            while ((dateLine = br.readLine()) != null) {
                String valueLine = br.readLine(); // Az érték az aktuális dátum után következik
                if (valueLine != null) {
                    String date = dateLine.trim();
                    double value = Double.parseDouble(valueLine.trim().replace(",", "."));
                    // Adatok hozzáadása a szériához
                    series.getData().add(new XYChart.Data<>(date, value));
                }
            }

            // Széria hozzáadása a charthoz
            lineChart.getData().add(series);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Hiba az mnb.txt fájl olvasása közben!");
        }
    }

    private void requestDataFromMNB() {
        try {
            // 1. Mai és három nappal ezelőtti dátum kiszámítása
            LocalDate today = LocalDate.now();
            LocalDate threeDaysAgo = today.minusDays(3);

            // Dátumok formázása
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String todayStr = today.format(formatter);
            String threeDaysAgoStr = threeDaysAgo.format(formatter);

            // API hívás (itt az MNB.getData() használata történik)
            MNB.getData("EUR", threeDaysAgoStr, todayStr, false);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Hiba az MNB API meghívása közben!");
        }
    }

    @FXML
    public void onMenuRedirectClicked(ActionEvent event) {
        try {
            // FXML betöltése
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/nje/ea/eabeadando/menu-bar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600); // Beállíthatod a szélességet és magasságot is

            // Aktuális Stage megszerzése az ActionEvent segítségével
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("JavaFX CRUD Alkalmazás"); // Ablak címének beállítása
            stage.setScene(scene);
            stage.setResizable(false); // Nem méretezhető ablak
            stage.show(); // Új nézet megjelenítése
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Hiba történt a menu-bar.fxml betöltése során!");
        }
    }
}
