package nje.ea.eabeadando;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("Java Runtime Version: " + System.getProperty("java.version"));
            System.out.println("Java Runtime Vendor: " + System.getProperty("java.vendor"));
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/nje/ea/eabeadando/menu-bar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            stage.setTitle("JavaFX CRUD Alkalmazás");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
