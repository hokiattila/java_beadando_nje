package nje.ea.eabeadando.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import nje.ea.eabeadando.dao.RecordDAO;
import nje.ea.eabeadando.models.Record;

import java.util.List;

public class ReadController {

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

    private final RecordDAO recordDAO = new RecordDAO();

    @FXML
    public void initialize() {
        recordTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(recordTable, Priority.ALWAYS);

        jelentkezoIdColumn.setCellValueFactory(new PropertyValueFactory<>("jelentkezoId"));
        jelentkezoNevColumn.setCellValueFactory(new PropertyValueFactory<>("jelentkezoNev"));
        jelentkezoNemColumn.setCellValueFactory(new PropertyValueFactory<>("jelentkezoNem"));
        kepzesNevColumn.setCellValueFactory(new PropertyValueFactory<>("kepzesNev"));
        sorrendColumn.setCellValueFactory(new PropertyValueFactory<>("sorrend"));
        szerzettPontColumn.setCellValueFactory(new PropertyValueFactory<>("szerzett"));

        refreshTable();
    }

    public void refreshTable() {
        List<Record> records = recordDAO.getAllRecords();
        recordTable.getItems().setAll(records);
    }
}
