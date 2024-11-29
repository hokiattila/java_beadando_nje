package nje.ea.eabeadando.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import nje.ea.eabeadando.dao.RecordDAO;

import java.util.List;

public class IrController {

    // A szükséges DAO példányosítása
    private RecordDAO recordDAO = new RecordDAO();

    // FXML mezők
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private ComboBox<String> applicantComboBox;
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private TextField nameField;
    @FXML
    private TextField courseNameField;
    @FXML
    private TextField courseCapacityField;
    @FXML
    private TextField courseMinScoreField;
    @FXML
    private TextField rankField;
    @FXML
    private TextField scoreField;

    // Az inicializálás során feltöltjük a ComboBox-okat
    @FXML
    private void initialize() {
        // Gender ComboBox
        genderComboBox.setItems(FXCollections.observableArrayList("Fiú", "Lány"));

        // Jelentkező és Képzés ComboBox-ok feltöltése
        loadApplicants();
        loadCourses();
    }

    // Jelentkezők betöltése ComboBox-ba
    private void loadApplicants() {
        List<String> applicants = recordDAO.getAllApplicants();  // Lekérdezzük a jelentkezőket az adatbázisból
        applicantComboBox.setItems(FXCollections.observableArrayList(applicants));
    }

    // Képzések betöltése ComboBox-ba
    private void loadCourses() {
        List<String> courses = recordDAO.getAllCourses();  // Lekérdezzük a képzéseket az adatbázisból
        courseComboBox.setItems(FXCollections.observableArrayList(courses));
    }

    // Jelentkező hozzáadása
    @FXML
    private void handleAddJelentkezo() {
        // A jelentkező adatainak lekérése
        String name = nameField.getText();
        String gender = genderComboBox.getValue();

        String newGender = gender;
        if (gender.toLowerCase().equals("fiú")) newGender = "f";
        if (gender.toLowerCase().equals("lány")) newGender = "l";

        // Ellenőrizzük, hogy minden adatot megadtak-e
        if (name == null || name.isEmpty() || gender == null || gender.isEmpty()) {
            System.out.println("Kérlek töltsd ki az összes mezőt!");
            return;
        }

        // Hozzáadjuk az adatbázishoz (itt a DAO-n keresztül)
        recordDAO.addJelentkezo(name, newGender);
        System.out.println("Jelentkező hozzáadva. Név: " + name + " Nem: " + newGender);
        // Mezők tisztítása
        nameField.clear();
        genderComboBox.getSelectionModel().clearSelection();

        // Jelentkező lista frissítése
        loadApplicants();
    }

    // Képzés hozzáadása
    @FXML
    private void handleAddKepzes() {
        // Képzés adatainak lekérése
        String courseName = courseNameField.getText();
        String capacityText = courseCapacityField.getText();
        String minScoreText = courseMinScoreField.getText();

        // Ellenőrizzük, hogy minden adatot megadtak-e
        if (courseName == null || courseName.isEmpty() || capacityText == null || capacityText.isEmpty() || minScoreText == null || minScoreText.isEmpty()) {
            System.out.println("Kérlek töltsd ki az összes mezőt!");
            return;
        }

        try {
            int capacity = Integer.parseInt(capacityText);
            int minScore = Integer.parseInt(minScoreText);

            // Hozzáadjuk az adatbázishoz
            recordDAO.addKepzes(courseName, capacity, minScore);
            System.out.println("Képzés hozzáadva: Név: " + courseName + " felvehető: " + capacity + "Min Pontszám:" + minScore);
            // Mezők tisztítása
            courseNameField.clear();
            courseCapacityField.clear();
            courseMinScoreField.clear();

            // Képzés lista frissítése
            loadCourses();

        } catch (NumberFormatException e) {
            System.out.println("Kérem, válassza ki a helyes számformátumot a kapacitás és minimális pontszám mezőknél!");
        }
    }

    // Jelentkezés hozzáadása
    @FXML
    private void handleAddJelentkezes() {
        // Jelentkezés adatainak lekérése
        String applicantName = recordDAO.getApplicantNameById(Integer.parseInt(applicantComboBox.getValue()));
        String courseName = recordDAO.getKepzesNameById(Integer.parseInt(courseComboBox.getValue()));
        String rankText = rankField.getText();
        String scoreText = scoreField.getText();

        // Ellenőrizzük, hogy minden adatot megadtak-e
        if (applicantName == null || applicantName.isEmpty() || courseName == null || courseName.isEmpty() || rankText == null || rankText.isEmpty() || scoreText == null || scoreText.isEmpty()) {
            System.out.println("Kérlek töltsd ki az összes mezőt!");
            return;
        }

        try {
            int rank = Integer.parseInt(rankText);
            int score = Integer.parseInt(scoreText);

            // Jelentkező ID és Képzés ID lekérése
            int applicantId = recordDAO.getApplicantIdByName(applicantName);
            int courseId = recordDAO.getCourseIdByName(courseName);

            // Hozzáadjuk a jelentkezést az adatbázishoz
            recordDAO.addJelentkezes(applicantId, courseId, rank, score);
            System.out.println("Jelentkezés hozzáadva: ID: " + applicantId + " Kurzus: " + courseId + " Rangsor: " + rank + " Pontszám: " + score);
            // Mezők tisztítása
            applicantComboBox.getSelectionModel().clearSelection();
            courseComboBox.getSelectionModel().clearSelection();
            rankField.clear();
            scoreField.clear();

        } catch (NumberFormatException e) {
            System.out.println("Kérem, válassza ki a helyes számformátumot a sorrend és szerzett pont mezőknél!");
        }
    }
}
