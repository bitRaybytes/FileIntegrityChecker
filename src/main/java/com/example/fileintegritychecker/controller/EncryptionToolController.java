package com.example.fileintegritychecker.controller;

import com.example.fileintegritychecker.model.AlgorithmProvider;
import com.example.fileintegritychecker.service.EncryptionCategory;
import com.example.fileintegritychecker.service.EncryptionService;
import com.example.fileintegritychecker.util.CopyToClipboard;
import com.example.fileintegritychecker.util.ToolTipHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.*;


public class EncryptionToolController {

    @FXML GridPane encrGridPane;
    @FXML Label header;
    @FXML Label subheader;
    @FXML Label encryptionResult;
    @FXML TextArea encryptText;
    @FXML Button BtnEncryptText;
    @FXML Button BtncopyToClipboard;

    @FXML ComboBox<String> algorithmComboBox;



    private final EncryptionService encryptionService = new EncryptionService();
    private EncryptionCategory currentCategory;
    private String selectedAlgorithm;

    private final String HEADER = "Encryption Service";
    private final String SUBHEADER="Choose from an encryption algorithm.";
    private final String ENCRYPTIONTEXT= "Enter your text here...";
    private final String BTNENCRYPTTEXT="Encrypt";
    private final String RESULTTEXT="Your result will displayed here.";
    private final String GENERATEBTNTOOLTIP = "Generate encryption from chosen algorithm";

    // Map: CategoryName → Algorithms
    private final Map<String, List<String>> algorithmMap = new HashMap<>();

    public EncryptionToolController(){}


    @FXML
    public void initialize(){
        initEncryptGridPane();
        // Initial population of ComboBox
        algorithmComboBox.getItems().addAll(Arrays.toString(EncryptionCategory.values()));
        populateMainCategories();

        BtnEncryptText.setOnAction(e -> handleEncrypt());
        BtncopyToClipboard.setOnAction(e -> new CopyToClipboard().copyToClipboard(encryptionResult));

        algorithmComboBox.setOnAction(event -> handleComboBoxSelection());

    }



    // --- Initialize GridPane and UI Elements --- //
    private void initEncryptGridPane(){
        encrGridPane.setHgap(10);
        encrGridPane.setVgap(10);
        encrGridPane.setPadding(new Insets(10));
        encrGridPane.setAlignment(Pos.CENTER);

        header.setText(HEADER);
        subheader.setText(SUBHEADER);

        encryptText.setText(ENCRYPTIONTEXT);
        encryptText.setMaxHeight(100);

        BtnEncryptText.setText(BTNENCRYPTTEXT);
        ToolTipHandler.attachToolTips(BtnEncryptText, GENERATEBTNTOOLTIP, 500);

        BtncopyToClipboard.setGraphic(new FontIcon("mdi2c-content-copy"));
        BtncopyToClipboard.setContentDisplay(ContentDisplay.CENTER);


        encryptionResult.setText(RESULTTEXT);

//        comboBoxEncryption.setItems();

        // GridPane Row Index
        GridPane.setRowIndex(header,0);
        GridPane.setRowIndex(subheader, 1);
        GridPane.setRowIndex(encryptText, 2);
        GridPane.setRowIndex(encryptionResult, 3);
        GridPane.setRowIndex(algorithmComboBox, 4);
        GridPane.setRowIndex(BtnEncryptText, 5);
        GridPane.setRowIndex(BtncopyToClipboard, 6);

        // GridPane Column Index
        GridPane.setColumnIndex(header,0);
        GridPane.setColumnIndex(subheader, 0);
        GridPane.setColumnIndex(encryptText, 0);
        GridPane.setColumnIndex(BtnEncryptText, 0);
        GridPane.setColumnIndex(BtncopyToClipboard,1);
        GridPane.setColumnIndex(encryptionResult, 0);
        GridPane.setColumnIndex(algorithmComboBox,0);
    }



    /** --- Encrypt using EncryptionService --- */
    private void handleEncrypt() {

        if (selectedAlgorithm == null || selectedAlgorithm.equals("← Back")) {
            encryptionResult.setText("Please select a valid algorithm first.");
            return;
        }

        String input = encryptText.getText().trim();
        if (input.isEmpty()) {
            encryptionResult.setText("Please enter text to encrypt.");
            return;
        }

        try {
            String result = encryptionService.computeMessageDigest(input, selectedAlgorithm);
            encryptionResult.setText(result);
        } catch (Exception ex) {
            encryptionResult.setText("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void populateMainCategories() {
        algorithmComboBox.getItems().clear();

        // Add enum display names
        for (EncryptionCategory category : EncryptionCategory.values()) {
            if (category != EncryptionCategory.BACK) {
                algorithmComboBox.getItems().add(category.toString());
                // Store algorithms for each category
                algorithmMap.put(category.toString(), AlgorithmProvider.getEncryptionAlgorithmsByCategory(category));
            }
        }

        algorithmComboBox.setPromptText("Select Category");
    }

    public void handleComboBoxSelection() {
        String selected = algorithmComboBox.getValue();
        if (selected == null) return;

        // BACK: Go back to categories
        if (selected.equals("← Back")) {
            populateMainCategories();
            return;
        }

        // If the selection is a main category (like "Cipher" or "Mac")
        if (algorithmMap.containsKey(selected)) {
            List<String> algorithms = algorithmMap.get(selected);
            ObservableList<String> algoItems = FXCollections.observableArrayList();
            algoItems.add("← Back");
            algoItems.addAll(algorithms);

            Platform.runLater(() -> {
                algorithmComboBox.setItems(algoItems);
                algorithmComboBox.getSelectionModel().clearSelection();
                algorithmComboBox.setPromptText("Select Algorithm");
            });

            encryptionResult.setText("Select an algorithm from " + selected);
        } else {
            // User selected an actual algorithm
            selectedAlgorithm = selected;
            encryptionResult.setText("Selected algorithm: " + selectedAlgorithm);
        }

    }
}
