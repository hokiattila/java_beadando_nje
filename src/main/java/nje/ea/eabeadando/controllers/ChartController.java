package nje.ea.eabeadando.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import nje.ea.eabeadando.MNB;

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
}
