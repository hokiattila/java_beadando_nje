package nje.ea.eabeadando.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import nje.ea.eabeadando.dao.RecordDAO;
import nje.ea.eabeadando.models.Record;
import nje.ea.eabeadando.models.jelentkezoRecord;
import nje.ea.eabeadando.models.kepzesRecord;

import java.util.List;

public class ReadController {

    @FXML
    private TableView<Record> recordTable;

    @FXML
    private TableView<kepzesRecord> kepzesTable;

    @FXML
    private TableView<jelentkezoRecord> jelentkezoTable;

    @FXML
    private TableColumn<Record, Integer> jelentkezoIdColumn;

    @FXML
    private TableColumn<Record, String> jelentkezoNevColumn;

    @FXML
    private TableColumn<Record, String> jelentkezoNemColumn;

    @FXML
    private TableColumn<jelentkezoRecord, Integer> jelentkezoId;

    @FXML
    private TableColumn<jelentkezoRecord, String> jelentkezoNev;

    @FXML
    private TableColumn<jelentkezoRecord, String> jelentkezoNem;

    @FXML
    private TableColumn<kepzesRecord, String> kepzesId;

    @FXML
    private TableColumn<kepzesRecord, Integer> kepzesNev;

    @FXML
    private TableColumn<kepzesRecord, String> kepzesFelveheto;

    @FXML
    private TableColumn<kepzesRecord, String> kepzesMinimum;

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
        szerzettPontColumn.setCellValueFactory(new PropertyValueFactory<>("szerzettPont"));

        kepzesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(kepzesTable, Priority.ALWAYS);

        kepzesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        kepzesNev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        kepzesFelveheto.setCellValueFactory(new PropertyValueFactory<>("felveheto"));
        kepzesMinimum.setCellValueFactory(new PropertyValueFactory<>("minimum"));

        jelentkezoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(jelentkezoTable, Priority.ALWAYS);

        jelentkezoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        jelentkezoNev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        jelentkezoNem.setCellValueFactory(new PropertyValueFactory<>("nem"));

        refreshTable();
    }

    public void refreshTable() {
        List<Record> records = recordDAO.getAllRecords();
        recordTable.getItems().setAll(records);
        List<kepzesRecord> kepzesrecords = recordDAO.getAllKepzesRecords();
        kepzesTable.getItems().setAll(kepzesrecords);
        List<jelentkezoRecord> jelentkezorecords = recordDAO.getAllJelentkezoRecords();
        jelentkezoTable.getItems().setAll(jelentkezorecords);
    }
}
