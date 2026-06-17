package com.example.fileintegritychecker.controller;

import com.example.fileintegritychecker.model.EncryptionCategory;
import com.example.fileintegritychecker.service.EncryptionService;
import com.example.fileintegritychecker.util.ComboBoxSelectionHandler;
import com.example.fileintegritychecker.util.ToolTipHandler;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;


public class EncryptionToolController {

    @FXML GridPane encrGridPane;
    @FXML Label header;
    @FXML Label subheader;
    @FXML Label encryptionResult;
    @FXML TextArea encryptText = new TextArea();
    @FXML Button BtnEncryptText;
    @FXML Button BtncopyToClipboard;

    @FXML ComboBox<String> algorithmComboBox;


    private EncryptionService encrSer = new EncryptionService();
    private EncryptionService encryptionService = new EncryptionService(getTextInput(), getAlgorithmString());
    private EncryptionCategory currentCategory;
    private String selectedAlgorithm;

    private final String HEADER = "Encryption Service";
    private final String SUBHEADER="Choose from an encryption algorithm.";
    private final String ENCRYPTIONTEXT= "Enter your text here to encrypt...";
    private final String BTNENCRYPTTEXT="Encrypt";
    private final String RESULTTEXT="Your result will displayed here.";
    private final String GENERATEBTNTOOLTIP = "Generate encryption from chosen algorithm";

    private ComboBoxSelectionHandler<EncryptionCategory> comboHandler;


    public EncryptionToolController() {}


    @FXML
    public void initialize(){

        initEncryptGridPane();
        // Initial population of ComboBox
        comboHandler = new ComboBoxSelectionHandler<>(algorithmComboBox, encryptionResult, EncryptionCategory.class);
        comboHandler.showMainCategories();

        algorithmComboBox.getItems();


        algorithmComboBox.setOnAction(e -> comboHandler.handleSelection());
        BtnEncryptText.setOnAction(e -> {
            try {
                encryptionService.encrypt();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        if (algorithmComboBox.getItems().isEmpty()) {
            throw new IllegalArgumentException("ComboBox should not be empty during initialization.");
        }
    }

    private String getAlgorithmString()
    {
        return selectedAlgorithm;
    }

    private String getTextInput(){
        return encryptText.getText();
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

}
