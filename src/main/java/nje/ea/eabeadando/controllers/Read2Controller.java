package nje.ea.eabeadando.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Read2Controller {

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
    public void initialize() {
        System.out.println("Initialize fut!");
        kepzesComboBox.getItems().addAll(
                "francia",
                "angol",
                "matematika",
                "informatika",
                "környezetvédelmi",
                "közgazdasági"
        );
    }


    @FXML
    private void handleFilterAction(ActionEvent event) {
        // Logic here
    }

}
