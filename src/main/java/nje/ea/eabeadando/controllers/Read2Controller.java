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

        // ComboBox feltöltése
        kepzesComboBox.getItems().addAll(
                "francia",
                "angol",
                "matematika",
                "informatika",
                "környezetvédelmi",
                "közgazdasági"
        );

        // ToggleGroup beállítása
        genderToggleGroup = new ToggleGroup();
        radioOption1.setToggleGroup(genderToggleGroup);
        radioOption2.setToggleGroup(genderToggleGroup);

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
        String nevFilter = textField.getText().trim();
        String kepzesFilter = kepzesComboBox.getValue();
        RadioButton selectedGender = (RadioButton) genderToggleGroup.getSelectedToggle();
        String genderFilter = selectedGender != null ? selectedGender.getText() : null;
        boolean noDuplicates = checkBox.isSelected();

        // Adatok szűrése
        List<Record> allRecords = recordDAO.getAllRecords();
        List<Record> filteredRecords = allRecords.stream()
                .filter(record -> nevFilter.isEmpty() || record.getJelentkezoNev().toLowerCase().contains(nevFilter.toLowerCase()))
                .filter(record -> kepzesFilter == null || record.getKepzesNev().equalsIgnoreCase(kepzesFilter))
                .filter(record -> genderFilter == null || record.getJelentkezoNem().equalsIgnoreCase(genderFilter))
                .collect(Collectors.toList());

        if (noDuplicates) {
            filteredRecords = filteredRecords.stream()
                    .distinct()
                    .collect(Collectors.toList());
        }

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
