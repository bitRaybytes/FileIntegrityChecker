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


public class EncryptionToolController {

    @FXML GridPane encrGridPane;
    @FXML Label header;
    @FXML Label subheader;
    @FXML Label encryptionResult;
    @FXML TextArea encryptText;
    @FXML Button BtnEncryptText;
    @FXML Button BtncopyToClipboard;
    @FXML ComboBox<EncryptionService.Algorithm> comboBoxEncryption;

    private EncryptionCategory currentCategory = null;


    private final String HEADER = "Encryption Service";
    private final String SUBHEADER="Choose from an encryption algorithm.";
    private final String ENCRYPTIONTEXT= "Enter your text here...";
    private final String BTNENCRYPTTEXT="Encrypt";
    private final String RESULTTEXT="Your result will displayed here.";
    private final String GENERATEBTNTOOLTIP = "Generate encryption from chosen algorithm";

    private final EncryptionService encryptionService = new EncryptionService();

    public void initialize(){
        comboBoxEncryption.getItems().addAll(EncryptionService.Algorithm.values());
        comboBoxEncryption.getSelectionModel().selectFirst();
        initEncryptGridPane();

        BtnEncryptText.setOnAction(e -> handleEncrypt());

        BtncopyToClipboard.setOnAction(e -> {;
            String resultText = encryptionResult.getText();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(resultText);
            clipboard.setContent(content);
        });

    }

    private void handleEncrypt() {
        try {
            String input = encryptText.getText();
            EncryptionService.Algorithm algo = comboBoxEncryption.getValue();
            String encrypted = encryptionService.encrypt(input, algo);
            encryptionResult.setText(encrypted);
        } catch (Exception ex) {
            encryptionResult.setText("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

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
        GridPane.setRowIndex(comboBoxEncryption, 3);
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
        GridPane.setColumnIndex(comboBoxEncryption,0);
    }
    private void showMainCategories() {
        currentCategory = null;
        comboBoxEncryption.getItems().clear();
        comboBoxEncryption.getItems().addAll(EncryptionCategory.values());
    }

    private void onAlgorithmSelected() {
        Object selected = comboBoxEncryption.getValue();

        if (selected instanceof EncryptionCategory) {
            EncryptionCategory category = (EncryptionCategory) selected;

            if (category == EncryptionCategory.BACK) {
                showMainCategories();
                return;
            }

            currentCategory = category;
            comboBoxEncryption.getItems().clear();
            comboBoxEncryption.getItems().add(EncryptionCategory.BACK);

            switch (category) {
                case MESSAGE_DIGEST:
                    comboBoxEncryption.getItems().addAll(AlgorithmProvider.getMessageDigestAlgorithms());
                    break;
                case SYMMETRIC:
                    comboBoxEncryption.getItems().addAll(AlgorithmProvider.getSymmetricAlgorithms());
                    break;
                case ASYMMETRIC:
                    comboBoxEncryption.getItems().addAll(AlgorithmProvider.getAsymmetricAlgorithms());
                    break;
            }




        }
    }

}
