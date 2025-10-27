package com.example.fileintegritychecker.controller;

import com.example.fileintegritychecker.model.AlgorithmProvider;
import com.example.fileintegritychecker.service.EncryptionCategory;
import com.example.fileintegritychecker.service.EncryptionService;
import com.example.fileintegritychecker.util.ToolTipHandler;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;


public class EncryptionToolController {

    @FXML GridPane encrGridPane;
    @FXML Label header;
    @FXML Label subheader;
    @FXML Label encryptionResult;
    @FXML TextArea encryptText;
    @FXML Button BtnEncryptText;
    @FXML Button BtncopyToClipboard;

    @FXML ComboBox<Object> algorithmComboBox;



    private final EncryptionService encryptionService = new EncryptionService();
    private EncryptionCategory currentCategory = null;
    private String selectedAlgorithm;

    private final String HEADER = "Encryption Service";
    private final String SUBHEADER="Choose from an encryption algorithm.";
    private final String ENCRYPTIONTEXT= "Enter your text here...";
    private final String BTNENCRYPTTEXT="Encrypt";
    private final String RESULTTEXT="Your result will displayed here.";
    private final String GENERATEBTNTOOLTIP = "Generate encryption from chosen algorithm";


    @FXML
    public void initialize(){
        algorithmComboBox.getItems().addAll(EncryptionCategory.values());
        algorithmComboBox.getSelectionModel().selectFirst();

        // Initial mit Kategorien befüllen
        algorithmComboBox.getItems().addAll(EncryptionCategory.values());

        // Event Handler für Auswahl
        algorithmComboBox.setOnAction(event -> handleComboBoxSelection());

        initEncryptGridPane();

        BtnEncryptText.setOnAction(e -> handleEncrypt());

        algorithmComboBox.setOnAction(e -> onComboBoxSelection());
        BtnEncryptText.setOnAction(e -> handleEncrypt());
        BtncopyToClipboard.setOnAction(e -> copyToClipboard(encryptionResult));

//        BtncopyToClipboard.setOnAction(e -> {;
//            String resultText = encryptionResult.getText();
//            Clipboard clipboard = Clipboard.getSystemClipboard();
//            ClipboardContent content = new ClipboardContent();
//            content.putString(resultText);
//            clipboard.setContent(content);
//        });

    }

//    private void handleEncrypt() {
//        try {
//            String input = encryptText.getText();
//            EncryptionService.Algorithm algo = comboBoxEncryption.getValue();
//            String encrypted = encryptionService.encrypt(input, algo);
//            encryptionResult.setText(encrypted);
//        } catch (Exception ex) {
//            encryptionResult.setText("Error: " + ex.getMessage());
//            ex.printStackTrace();
//        }
//    }


    // --- Initialize GridPane and UI Elements --- //
    private void initEncryptGridPane(){
        encrGridPane.setHgap(10);
        encrGridPane.setVgap(10);
        encrGridPane.setPadding(new Insets(10));
        encrGridPane.setAlignment(Pos.CENTER);

        header.setText(HEADER);
        subheader.setText(SUBHEADER);

        encryptText.setText(ENCRYPTIONTEXT);

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
        GridPane.setRowIndex(algorithmComboBox, 3);
        GridPane.setRowIndex(BtnEncryptText, 4);
        GridPane.setRowIndex(BtncopyToClipboard, 4);
        GridPane.setRowIndex(encryptionResult, 5);

        // GridPane Column Index
        GridPane.setColumnIndex(header,0);
        GridPane.setColumnIndex(subheader, 0);
        GridPane.setColumnIndex(encryptText, 0);
        GridPane.setColumnIndex(BtnEncryptText, 0);
        GridPane.setColumnIndex(BtncopyToClipboard,1);
        GridPane.setColumnIndex(encryptionResult, 0);
        GridPane.setColumnIndex(algorithmComboBox,0);
    }

    // --- Handle ComboBox selections --- //
    private void showMainCategories() {
        currentCategory = null;
        algorithmComboBox.getItems().clear();
        algorithmComboBox.getItems().addAll(EncryptionCategory.values());
    }

    private void onComboBoxSelection() {
        String selected = String.valueOf(algorithmComboBox.getValue());
        if (selected == null) return;

        // If user chooses BACK, show main categories again
        if (selected.equals(EncryptionCategory.BACK.toString())) {
            showMainCategories();
            return;
        }

        // If currently viewing algorithms and the user selects one
        if (currentCategory != null && !selected.equals(EncryptionCategory.BACK)) {
            selectedAlgorithm = selected;
            subheader.setText("Selected algorithm: " + selectedAlgorithm);
            return;
        }

        // Otherwise, user selected a new main category
        for (EncryptionCategory cat : EncryptionCategory.values()) {
            if (cat.toString().equals(selected)) {
                currentCategory = cat;
                break;
            }
        }

        if (currentCategory != null) {
            List<String> algorithms = AlgorithmProvider.setEncryptionAlgoListStr(currentCategory);
            algorithmComboBox.getItems().clear();
            algorithmComboBox.getItems().add(EncryptionCategory.BACK);
            algorithmComboBox.getItems().addAll(AlgorithmProvider.setEncryptionAlgorithmsAsString(currentCategory));
            subheader.setText("Select an algorithm from " + currentCategory.toString());
        }
    }

    /** --- Step 3: Encrypt using EncryptionService --- */
    private void handleEncrypt() {
        if (currentCategory == null || selectedAlgorithm == null) {
            encryptionResult.setText("Please select an algorithm first.");
            return;
        }

        try {
            String input = encryptText.getText();
            String result;

            // For now, we’ll only use CIPHER algorithms with the EncryptionService.
            // You could later expand this logic for MAC, Signature, etc.
            if (currentCategory == EncryptionCategory.CIPHER) {
                result = encryptionService.encryptSymmetric(input, EncryptionCategory.valueOf(selectedAlgorithm));
            } else {
                result = "Category not yet supported for encryption.";
            }

            encryptionResult.setText(result);

        } catch (Exception ex) {
            encryptionResult.setText("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void handleComboBoxSelection() {
        Object selected = algorithmComboBox.getSelectionModel().getSelectedItem();

        if (selected instanceof EncryptionCategory) {
            EncryptionCategory category = (EncryptionCategory) selected;
            currentCategory = category;

            // ComboBox mit Algorithmen der gewählten Kategorie neu befüllen
            algorithmComboBox.getItems().clear();
            algorithmComboBox.getItems().addAll(AlgorithmProvider.setEncryptionAlgorithms(category));
            // Zurück-Option hinzufügen
            algorithmComboBox.getItems().add(EncryptionCategory.BACK);

        } else if (selected instanceof String && selected.equals(EncryptionCategory.BACK)) {
            // Zurück zu Kategorien
            algorithmComboBox.getItems().clear();
            algorithmComboBox.getItems().addAll(EncryptionCategory.values());
            currentCategory = null;
        }
    }


    /** --- Step 4: Copy results --- */
    private void copyToClipboard(Label label) {
        ClipboardContent content = new ClipboardContent();
        content.putString(label.getText());
        Clipboard.getSystemClipboard().setContent(content);
    }

}
