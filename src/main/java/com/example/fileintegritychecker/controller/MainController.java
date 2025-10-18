package com.example.fileintegritychecker.controller;

import com.example.fileintegritychecker.service.HashService;
import com.example.fileintegritychecker.service.IntegrityService;
import javafx.fxml.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.security.MessageDigest;
import java.security.Security;


/**
 * Author:
 *  Ray
 * */
public class MainController {

    @FXML private Button btnSelectFile;
    @FXML private Button hashFile;
    @FXML private Button replay;
    @FXML private Button themeToggleBtn;
//    @FXML private Button themeButton;
    @FXML private Label header;
    @FXML private Label subheader;
    @FXML private Label lblSelectedFile;
    @FXML private Label hashedValue;
    @FXML private ComboBox<String> algoComboBox;
    @FXML private final HashService hashService = new HashService();
    @FXML private VBox ficVBox;
    private File selectedFile;
    private boolean darkMode = false;
//    private final String[] algorithms = {
//            "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512", "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320"};

    private String algorithms = Security.getAlgorithms("MessageDigest").toString()
            .replace("[","")
            .replace("]","");

    // Initialize ComboBox and other UI elements
    @FXML
    private void initialize() {
        initalizeVBox();

    }

    // Chose File
    @FXML
    private void openFileEvent() {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) btnSelectFile.getScene().getWindow();

        selectedFile = fileChooser.showOpenDialog(stage);

        // Update UI based on file selection
        if (selectedFile != null) {
            lblSelectedFile.setText("Selected file: " + selectedFile.getAbsolutePath());
            algoComboBox.setVisible(true);
            hashFile.setVisible(true);
            btnSelectFile.setVisible(false);
            replay.setVisible(true);

            // Clear previous hash value
            hashedValue.setVisible(false);
            hashedValue.setText("");
        } else {
            lblSelectedFile.setText("No file selected");
            algoComboBox.setVisible(false);

        }
    }



    // Initialize ComboBox with algorithms
    @FXML
    private void initializeComboBox() {

        for (String algo : algorithms.split(", ")) {
            algoComboBox.getItems().add(algo);
        }
//        algoComboBox.getItems().addAll(algorithms);
        algoComboBox.setPromptText("Select Algorithm");
    }


    // Hide certain GUI elements initially
    @FXML
    private void falseVisibilityGUI(){
        algoComboBox.setVisible(false);

        hashedValue.setVisible(false);
        hashFile.setVisible(false);
        replay.setVisible(false);
    }

    // Initialize VBox
    @FXML
    private void initalizeVBox() {

        ficVBox.setSpacing(15);
        ficVBox.setAlignment(javafx.geometry.Pos.CENTER);
        setHeader();
        initializeComboBox();
        falseVisibilityGUI();
        replayImage();
    }

    @FXML
    private void setHeader(){
        header.setText("File Integrity Checker");
        subheader.setText("Verify the integrity of your files using various hashing algorithms.");
    }

    // Replay Button Image
    /*
    * Materialdesign2 Library:
    * https://kordamp.org/ikonli/cheat-sheet-materialdesign2.html
    * */
    private void replayImage(){
        replay.setGraphic(new FontIcon("mdi2r-replay"));
        replay.setContentDisplay(ContentDisplay.CENTER);

    }

    // Compute Hash
    @FXML
    private void computedHash() {
        // Null check
        if (selectedFile == null) return;

        String algorithm = algoComboBox.getValue();

        // Check if algorithm is selected
        if (algorithm == null || algorithm.isEmpty()) {
            lblSelectedFile.setText("Please select an algorithm!");
            return;
        }

        // Compute hash
        try {
            IntegrityService integrityService = new IntegrityService();
            String hash = integrityService.computeHash(selectedFile,algorithm);
            hashedValue.setVisible(true);
            hashedValue.setText("File hash (" + algorithm + "): " + hash);

        } catch (Exception e) {
            hashedValue.setVisible(true);
            hashedValue.setText("Error computing hash: " + e.getMessage());
        }
    }


    // Replay File Selection
    @FXML
    private void replayFileCheck(){
        if (replay.isVisible()){
            btnSelectFile.setDisable(false);
            openFileEvent();
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
