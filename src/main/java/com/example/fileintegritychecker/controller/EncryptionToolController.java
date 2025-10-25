package com.example.fileintegritychecker.controller;

import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


public class EncryptionTool {

    @FXML GridPane encrGridPane;
    @FXML Label header;
    @FXML Label subheader;
    @FXML TextArea encryptText;
    @FXML Button BtnEncryptText;
    @FXML Label encryptionResult;
    @FXML ComboBox<String> comboBoxEncryption;

    private final String HEADER = "Encryption Service";
    private final String SUBHEADER="Choose from an encryption algorithm.";
    private final String ENCRYPTIONTEXT= "Enter your text here...";
    private final String BTNENCRYPTTEXT="Encrypt";
    private final String RESULTTEXT="Your result will displayed here.";


    public void initialize(){

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

        encryptionResult.setText(RESULTTEXT);

//        comboBoxEncryption.setItems();

        // GridPane Row Index
        GridPane.setRowIndex(header,0);
        GridPane.setRowIndex(subheader, 1);
        GridPane.setRowIndex(encryptText, 2);
        GridPane.setRowIndex(comboBoxEncryption, 3);
        GridPane.setRowIndex(BtnEncryptText, 4);
        GridPane.setRowIndex(encryptionResult, 5);

        // GridPane Column Index
        GridPane.setColumnIndex(header,0);
        GridPane.setColumnIndex(subheader, 0);
        GridPane.setColumnIndex(encryptText, 0);
        GridPane.setColumnIndex(BtnEncryptText, 0);
        GridPane.setColumnIndex(encryptionResult, 0);
        GridPane.setColumnIndex(comboBoxEncryption,0);

        

    }


}
