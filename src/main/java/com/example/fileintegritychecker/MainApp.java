package com.example.fileintegritychecker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Author: Ray
 * */

// Main Application Class
public class MainApp extends Application {

    // Launch application
    @Override
    public void start(Stage stage) throws Exception {
        // Load FXML file
        var url = getClass().getResource("/com/example/fileintegritychecker/fxml/Main-view.fxml");

        // Throw Exception, if file not found
        if (url == null){
            // FXML file not found
            throw new RuntimeException("FXML file not found!");
        }

        // Create Scene
        FXMLLoader loader = new FXMLLoader(url);
        // Set Scene
        Scene scene = new Scene(loader.load(), WIDTH, HEIGHT);
        // Add CSS
//        scene.getStylesheets().add(getClass().getResource("/com/example/fileintegritychecker/css/style.css").toExternalForm());


        // Set Stage
        stage.setTitle("AlgoSuite");
        // Set Scene
        stage.setScene(scene);
        // Show Stage
        stage.show();
    }

    // Main method
    public static void main(String[] args) {
        System.setProperty("javafx.allowNativeAccess", "true");
        launch(args);

    }


    // Prefs... DO NOT ALTER OR MODIFY
    private final int WIDTH = 800;
    private final int HEIGHT = 400;


}
