package nje.ea.eabeadando.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import nje.ea.eabeadando.dao.RecordDAO;
import nje.ea.eabeadando.models.Record;

import java.util.List;
import java.util.stream.Collectors;

public class Read2Controller {

    @FXML
    private BorderPane searchPane;

    @FXML
    private VBox tablePane;

    @FXML
    private TextField textField;

    @FXML
    private ComboBox<String> kepzesComboBox;

    @FXML
    private RadioButton radioOption1;

    @FXML
    private RadioButton radioOption3;

    @FXML
    private RadioButton radioOption2;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TableView<Record> recordTable;

    @FXML
    private TableColumn<Record, Integer> jelentkezoIdColumn;

    @FXML
    private TableColumn<Record, String> jelentkezoNevColumn;

    @FXML
    private TableColumn<Record, String> jelentkezoNemColumn;

    @FXML
    private TableColumn<Record, String> kepzesNevColumn;

    @FXML
    private TableColumn<Record, Integer> sorrendColumn;

    @FXML
    private TableColumn<Record, Integer> szerzettPontColumn;

    private ToggleGroup genderToggleGroup;

    private final RecordDAO recordDAO = new RecordDAO();

    @FXML
    public void initialize() {
        // Alapértelmezett nézet: keresési form
        showSearchPane();

        // Lekérjük az adatbázisból az egyedi képzési neveket
        List<String> kepzesNevList = recordDAO.getUniqueKepzesNev();

        // ComboBox feltöltése
        kepzesComboBox.getItems().clear();  // Töröljük az esetleges előző elemeket
        kepzesComboBox.getItems().addAll(kepzesNevList);

        radioOption3.setSelected(true);
        // ToggleGroup beállítása
        genderToggleGroup = new ToggleGroup();
        radioOption1.setToggleGroup(genderToggleGroup);
        radioOption2.setToggleGroup(genderToggleGroup);
        radioOption3.setToggleGroup(genderToggleGroup);

        // Táblázat oszlopok inicializálása
        jelentkezoIdColumn.setCellValueFactory(data -> data.getValue().jelentkezoIdProperty().asObject());
        jelentkezoNevColumn.setCellValueFactory(data -> data.getValue().jelentkezoNevProperty());
        jelentkezoNemColumn.setCellValueFactory(data -> data.getValue().jelentkezoNemProperty());
        kepzesNevColumn.setCellValueFactory(data -> data.getValue().kepzesNevProperty());
        sorrendColumn.setCellValueFactory(data -> data.getValue().sorrendProperty().asObject());
        szerzettPontColumn.setCellValueFactory(data -> data.getValue().szerzettPontProperty().asObject());
    }

    @FXML
    private void handleFilterAction(ActionEvent event) {
        // Szűrési paraméterek beolvasása
        String nevFilter = textField.getText().trim().toLowerCase();  // Kisbetűsítés a pontosabb összehasonlítás érdekében
        String kepzesFilter = kepzesComboBox.getValue();
        RadioButton selectedGender = (RadioButton) genderToggleGroup.getSelectedToggle();
        String genderFilter;

        // Ha van kiválasztott gender, akkor cseréljük a "Fiú"/"Lány" értékeket "f"/"l"-ra
        if (selectedGender != null) {
            String genderText = selectedGender.getText().toLowerCase();  // Kisbetűsítés
            if (genderText.equals("fiú")) {
                genderFilter = "f";  // "Fiú" -> "f"
            } else if (genderText.equals("lány")) {
                genderFilter = "l";  // "Lány" -> "l"
            } else if (genderText.equals("mindegy")) {
                genderFilter = null; // "Mindegy" -> nem szűrünk nem szerint
            } else {
                genderFilter = null;
            }
        } else {
            genderFilter = null;
        }

        // Pontszám szűrése: ha be van jelölve a checkbox, akkor csak azokat a rekordokat vesszük figyelembe,
        // amelyeknél a "szerzettPont" értéke legalább 150
        boolean minPontSzures = checkBox.isSelected(); // checkbox figyelése

        // Adatok szűrése
        List<Record> allRecords = recordDAO.getAllRecords();
        List<Record> filteredRecords = allRecords.stream()
                // Név szűrése (tartalmazza a keresett szöveget)
                .filter(record -> nevFilter.isEmpty() || record.getJelentkezoNev().toLowerCase().contains(nevFilter))
                // Képzés szűrése (egyezik a kiválasztott képzéssel)
                .filter(record -> kepzesFilter == null || record.getKepzesNev().equalsIgnoreCase(kepzesFilter))
                // Nem szűrés (fiú -> f, line -> l, mindegy -> nem szűrünk)
                .filter(record -> genderFilter == null ||
                        (genderFilter.equals("f") && record.getJelentkezoNem().equalsIgnoreCase("f")) ||
                        (genderFilter.equals("l") && record.getJelentkezoNem().equalsIgnoreCase("l")) ||
                        genderFilter == null)
                // Pontszám szűrése (min. 150 pont)
                .filter(record -> !minPontSzures || record.getSzerzettPont() >= 150)
                .collect(Collectors.toList());

        // Táblázat feltöltése
        ObservableList<Record> observableList = FXCollections.observableArrayList(filteredRecords);
        recordTable.setItems(observableList);

        // Nézet váltása táblázatra
        showTablePane();
    }





    @FXML
    private void handleNewSearchAction() {
        // Nézet visszaváltása keresési formra
        showSearchPane();
    }

    private void showSearchPane() {
        searchPane.setVisible(true);
        tablePane.setVisible(false);
    }

    private void showTablePane() {
        searchPane.setVisible(false);
        tablePane.setVisible(true);
    }
}
