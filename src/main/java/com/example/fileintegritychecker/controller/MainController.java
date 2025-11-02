package com.example.fileintegritychecker.controller;


import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;



/**
 * Author:
 *  Ray
 * */
public class MainController {

    @FXML private Button themeToggleBtn;
    @FXML private StackPane mainContent;

    @FXML private VBox sidebarNav;
    @FXML Button BtnNavIntegrityChecker;
    @FXML Button BtnNavHashPlayground;
    @FXML Button BtnNavEncryptionTool;
    @FXML Button BtnNavDecryptionTool;
    @FXML BorderPane mainBorderPane;

    private boolean darkMode = false;



    // Initialize ComboBox and other UI elements
    @FXML
    private void initialize() {

        BtnNavIntegrityChecker.setOnAction(e -> {
            loadView("FileIntegrityChecker-view.fxml");
            setActiveButton(BtnNavIntegrityChecker);
        });
        BtnNavHashPlayground.setOnAction(event ->{
            loadView("HashPlayground-view.fxml");
            setActiveButton(BtnNavHashPlayground);
            Scene scene = BtnNavHashPlayground.getScene();
            scene.getStylesheets().add(getClass().getResource("/com/example/fileintegritychecker/css/HashPlaygroundStyles.css").toExternalForm());
        });
        BtnNavEncryptionTool.setOnAction(e -> {
                    loadView("EncryptionTool-view.fxml");
                    setActiveButton(BtnNavEncryptionTool);
        });
        BtnNavDecryptionTool.setOnAction(e->{
            loadView("DecryptionTool-view.fxml");
            setActiveButton(BtnNavDecryptionTool);
        });
        // Default view at startup
        loadView("FileIntegrityChecker-view.fxml");
    }

    private void setActiveButton(Button activeButton) {
        sidebarNav.getChildren().forEach(node -> node.getStyleClass().remove("active"));
        activeButton.getStyleClass().add("active");
    }

    // Load different views based on navigation
    private void loadView(String fxmlfile) {
        // Logic to load the specified FXML view
        try{
            String FXMLPath = "/com/example/fileintegritychecker/fxml/";
            Parent view = FXMLLoader.load(getClass().getResource(FXMLPath + fxmlfile));
            mainContent.getChildren().setAll(view);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Toggle Theme
    @FXML
    private void toggleTheme() {
        Scene scene = themeToggleBtn.getScene();
        // Null check
        if (scene == null) return;

        scene.getStylesheets().clear();
        // Toggle between dark and light mode
        if (darkMode) {
            scene.getStylesheets().add(getClass().getResource("/com/example/fileintegritychecker/css/lightTheme.css").toExternalForm());
            themeToggleBtn.setGraphic(new FontIcon("mdi2m-moon-waxing-crescent")); // Mond
        } else {
            scene.getStylesheets().add(getClass().getResource("/com/example/fileintegritychecker/css/darkTheme.css").toExternalForm());
            themeToggleBtn.setGraphic(new FontIcon("mdi2w-white-balance-sunny")); // Sonne
        }
        // Toggle the mode
        darkMode = !darkMode;
    }
}
