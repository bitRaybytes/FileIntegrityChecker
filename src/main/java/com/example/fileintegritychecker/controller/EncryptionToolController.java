package com.example.fileintegritychecker.controller;

import com.example.fileintegritychecker.model.AlgorithmProvider;
import com.example.fileintegritychecker.service.DecryptionCategory;
import com.example.fileintegritychecker.service.EncryptionCategory;
import com.example.fileintegritychecker.service.EncryptionService;
import com.example.fileintegritychecker.util.ComboBoxSelectionHandler;
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



    private final EncryptionService encryptionService = new EncryptionService(getTextInput(encryptText), getAlgorithmString());
    private EncryptionCategory currentCategory;
    private String selectedAlgorithm;

    private final String HEADER = "Encryption Service";
    private final String SUBHEADER="Choose from an encryption algorithm.";
    private final String ENCRYPTIONTEXT= "Enter your text here...";
    private final String BTNENCRYPTTEXT="Encrypt";
    private final String RESULTTEXT="Your result will displayed here.";
    private final String GENERATEBTNTOOLTIP = "Generate encryption from chosen algorithm";

    // Map: CategoryName → Algorithms
    private ComboBoxSelectionHandler<EncryptionCategory> comboHandler;


    public EncryptionToolController(String algorithm)
    {
        this.selectedAlgorithm = algorithm;
    }


    @FXML
    public void initialize(){
        initEncryptGridPane();
        // Initial population of ComboBox
        comboHandler = new ComboBoxSelectionHandler(algorithmComboBox, encryptionResult, EncryptionCategory.class);
        comboHandler.showMainCategories();

        algorithmComboBox.setOnAction(e -> comboHandler.handleSelection());
        BtnEncryptText.setOnAction(e -> {
            try {
                encryptionService.encrypt();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private String getAlgorithmString()
    {
        return selectedAlgorithm;
    }

    private String getTextInput(TextArea encryptText){
        String encryptedTxtInput = encryptText.getText();
        return encryptedTxtInput;
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
