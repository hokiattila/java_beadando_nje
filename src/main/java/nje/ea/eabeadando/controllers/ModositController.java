package nje.ea.eabeadando.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import nje.ea.eabeadando.dao.RecordDAO;
import nje.ea.eabeadando.models.Record;

import java.util.List;

public class ModositController {

    @FXML
    private ComboBox<String> jelentkezoComboBox;
    @FXML
    private ComboBox<String> kepzesComboBox;
    @FXML
    private ComboBox<String> jelentkezesComboBox;

    @FXML
    private TextField nameField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField courseNameField;

    @FXML
    private ComboBox<String> genderComboBox;  // A ComboBox deklarálása
    @FXML
    private TextField courseCapacityField;
    @FXML
    private TextField courseMinScoreField;
    @FXML
    private TextField rankField;
    @FXML
    private TextField scoreField;

    private RecordDAO recordDAO = new RecordDAO(); // DAO példányosítás

    // Adatforrások a ComboBox-okhoz
    private ObservableList<String> jelentkezok = FXCollections.observableArrayList();
    private ObservableList<String> kepzesek = FXCollections.observableArrayList();
    private ObservableList<String> jelentkezesek = FXCollections.observableArrayList();
    private ObservableList<String> applicants = FXCollections.observableArrayList();
    private ObservableList<String> courses = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Adatok lekérése az adatbázisból
        jelentkezok.setAll(recordDAO.getAllApplicants());
        kepzesek.setAll(recordDAO.getAllCourses());

        // Jelentkezesek adatok betöltése és formázása
        List<String[]> applications = recordDAO.getAllApplications();
        for (String[] application : applications) {
            // Formázás: "jelentkezoid : kepzesid"
            jelentkezesek.add(application[0] + " : " + application[1]);
        }

        // ComboBox-ok feltöltése
        jelentkezoComboBox.setItems(jelentkezok);
        jelentkezesComboBox.setItems(jelentkezesek); // Formázott értékek
        kepzesComboBox.setItems(kepzesek);


        // Debug: Lekérdezett adatok
        System.out.println("Lekérdezett jelentkezések: " + jelentkezesek);
    }

    // Jelentkező kiválasztása alapján adatbetöltés
    @FXML
    private void handleJelentkezoSelection() {
        int selectedId = Integer.parseInt(jelentkezoComboBox.getValue());
        loadJelentkezoData(selectedId);

    }

    // Képzés kiválasztása alapján adatbetöltés
    @FXML
    private void handleKepzesSelection() {
        int selectedId = Integer.parseInt(kepzesComboBox.getValue());
        loadKepzesData(selectedId);
    }

    // Jelentkezés kiválasztása alapján adatbetöltés
    @FXML
    private void handleJelentkezesSelection() {
        // Kiválasztott érték a ComboBox-ból (formátum: "jelentkezoid : kepzesid")
        String selectedApplication = jelentkezesComboBox.getValue();

        if (selectedApplication != null && selectedApplication.contains(" : ")) {
            // Szétválasztjuk a "jelentkezoid : kepzesid" szöveget
            String[] ids = selectedApplication.split(" : ");
            try {
                // Az első rész jelentkezoid, a második rész kepzesid
                int jelentkezoid = Integer.parseInt(ids[0].trim());
                int kepzesid = Integer.parseInt(ids[1].trim());

                // Meghívjuk a loadJelentkezesData metódust a megfelelő paraméterekkel
                loadJelentkezesData(jelentkezoid, kepzesid);
            } catch (NumberFormatException e) {
                System.err.println("Hiba: Nem sikerült az ID-kat egész számmá konvertálni: " + selectedApplication);
            }
        } else {
            System.err.println("Hiba: Érvénytelen formátum a ComboBox-ban: " + selectedApplication);
        }
    }

    // Jelentkező adatainak betöltése
    private void loadJelentkezoData(int id) {
        int applicationId = id;
        //int applicantId = recordDAO.getApplicantIdByName(id);
        if (applicationId != -1) {
            nameField.setText(recordDAO.getApplicantNameById(id));
            genderComboBox.setValue(recordDAO.getApplicantGenderById(id)); // Példa adat, ezt frissíteni kell
        }
    }

    // Képzés adatainak betöltése
    private void loadKepzesData(int id) {
        int courseId = id;
        if (courseId != -1) {
            courseNameField.setText(recordDAO.getCourseNameById(id));
            courseCapacityField.setText(recordDAO.getCourseCapacityById(id));
            courseMinScoreField.setText(recordDAO.getCourseMinscoreById(id));
        }
    }

    // Jelentkezés adatainak betöltése
    private void loadJelentkezesData(int jelentkezoid, int kepzesid) {
        rankField.setText(recordDAO.getApplicationSorrendById(jelentkezoid, kepzesid));
        scoreField.setText(recordDAO.getApplicationSzerzettById(jelentkezoid, kepzesid));
    }

    // Jelentkező módosítása
    @FXML
    private void handleJelentkezoModify() {
        int id = Integer.parseInt(jelentkezoComboBox.getValue());
        String name = nameField.getText();
        String gender = genderComboBox.getValue();
        if (id != -1) {
            boolean success = recordDAO.updateJelentkezo(id, name, gender);
            if (success) {
                System.out.println("Jelentkező módosítva: " + name + ", " + gender);
            }
        }
    }

    // Képzés módosítása
    @FXML
    private void handleKepzesModify() {
        int id = Integer.parseInt(kepzesComboBox.getValue());
        String courseName = courseNameField.getText();
        String capacity = courseCapacityField.getText();
        String minScore = courseMinScoreField.getText();
        if (id != -1) {
            boolean success = recordDAO.updateKepzes(id, courseName, Integer.parseInt(capacity), Integer.parseInt(minScore));
            if (success) {
                System.out.println("Képzés módosítva: " + courseName + ", " + capacity + ", " + minScore);
            }
        }
    }

    // Jelentkezés módosítása
    @FXML
    private void handleJelentkezesModify() {
        // Az érték szétbontása
        String selectedValue = jelentkezesComboBox.getValue();
        if (selectedValue == null || !selectedValue.contains(" : ")) {
            System.out.println("Érvénytelen jelentkezés kiválasztva.");
            return;
        }

        // "jelentkezoid : kepzesid" formátum feldolgozása
        String[] parts = selectedValue.split(" : ");
        int applicantId;
        int courseId;

        try {
            applicantId = Integer.parseInt(parts[0].trim());
            courseId = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Hibás számformátum a kiválasztott értékben: " + selectedValue);
            return;
        }

        // Mezők értékeinek lekérése
        String rank = rankField.getText();
        String score = scoreField.getText();

        try {
            int rankValue = Integer.parseInt(rank.trim());
            int scoreValue = Integer.parseInt(score.trim());

            // Jelentkezés frissítése a DAO-n keresztül
            boolean success = recordDAO.updateJelentkezes(applicantId, courseId, rankValue, scoreValue);
            if (success) {
                System.out.println("Jelentkezés módosítva: Jelentkező ID = " + applicantId +
                        ", Képzés ID = " + courseId +
                        ", Rank = " + rank +
                        ", Score = " + score);
            } else {
                System.out.println("A jelentkezés módosítása sikertelen.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás számformátum a rank vagy score mezőkben.");
        }
    }

}