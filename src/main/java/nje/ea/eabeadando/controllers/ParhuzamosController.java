package nje.ea.eabeadando.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class ParhuzamosController {

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    private int counter1 = 1;
    private int counter2 = 1;

    @FXML
    public void initialize() {
        // Időzítő a label1 frissítésére 1 másodpercenként
        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateLabel1())
        );
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();

        // Időzítő a label2 frissítésére 2 másodpercenként
        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> updateLabel2())
        );
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
    }

    // label1 szövegének frissítése
    private void updateLabel1() {
        label1.setText("Label 1: " + counter1++);
    }

    // label2 szövegének frissítése
    private void updateLabel2() {
        label2.setText("Label 2: " + counter2++);
    }
}
