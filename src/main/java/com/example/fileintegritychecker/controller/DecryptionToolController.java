package com.example.fileintegritychecker.controller;

import com.example.fileintegritychecker.model.AlgorithmProvider;
import com.example.fileintegritychecker.service.DecryptionCategory;
import com.example.fileintegritychecker.service.EncryptionCategory;
import com.example.fileintegritychecker.util.ComboBoxSelectionHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.*;


public class DecryptionToolController {
    // Controller logic for Decryption Tool goes here

    /*
    * Textarea, Buttons, Dropdown for selecting decryption algorithms
    * Event handlers for decrypting files based on selected algorithm
    * Integration with EncryptionService for decryption operations
    * */

    @FXML private Label headerLabel;
    @FXML private Label subheaderLabel;
    @FXML private Label decryptResultLabel;
    @FXML private ComboBox<DecryptionCategory> decryptAlgoBox;
    @FXML private TextArea inputDecryptTextArea;
    @FXML private Button BtnDecryptText;
    @FXML private Button BtnCopyDecryptedText;
    @FXML private GridPane decryptToolPane;



    private final String[] LABELS =
            {
                    "Decryption Service",
                    "Choose from a decryption algorithm.",
                    "Your decrypted result will be displayed here.",
                    "Enter your encrypted text here...",
                    "Select Decryption Algorithm",
                    "Decrypt"
            };


    private ComboBoxSelectionHandler<DecryptionCategory> comboHandler;


    @FXML
    private void initialize(){
        decryptAlgoBox.getItems().setAll();
        decryptAlgoBox.getItems().toString().split(", ");

        // Initialization logic for Decryption Tool UI components
        initDecryptPane();
        comboHandler = new ComboBoxSelectionHandler(decryptAlgoBox, decryptResultLabel, DecryptionCategory.class);
        comboHandler.showMainCategories();

        decryptAlgoBox.setOnAction(e -> comboHandler.handleSelection());
        BtnDecryptText.setOnAction(e -> comboHandler.handleSelection());

    }


    private void initDecryptPane(){
        decryptToolPane.setVgap(10);
        decryptToolPane.setHgap(10);
        decryptToolPane.setAlignment(Pos.CENTER);
        decryptToolPane.setPadding(new Insets(10));

        headerLabel.setText(LABELS[0]);
        subheaderLabel.setText(LABELS[1]);
        decryptResultLabel.setText(LABELS[2]);
        inputDecryptTextArea.setText(LABELS[3]);

        decryptAlgoBox.setPromptText(LABELS[4]);

        BtnDecryptText.setText(LABELS[5]);

        BtnCopyDecryptedText.setGraphic(new FontIcon("mdi2c-content-copy"));
        BtnCopyDecryptedText.setContentDisplay(ContentDisplay.CENTER);

        // Grid Pane Positioning
        // Rows
        GridPane.setRowIndex(headerLabel,0);
        GridPane.setRowIndex(subheaderLabel,1);
        GridPane.setRowIndex(inputDecryptTextArea,2);
        GridPane.setRowIndex(decryptResultLabel,3);
        GridPane.setRowIndex(decryptAlgoBox,4);
        GridPane.setRowIndex(BtnDecryptText,5);
        GridPane.setRowIndex(BtnCopyDecryptedText,6);

        // Columns
        GridPane.setColumnIndex(headerLabel,0);
        GridPane.setColumnIndex(subheaderLabel,0);
        GridPane.setColumnIndex(inputDecryptTextArea,0);
        GridPane.setColumnIndex(decryptResultLabel,0);
        GridPane.setColumnIndex(decryptAlgoBox,0);
        GridPane.setColumnIndex(BtnDecryptText,0);
        GridPane.setColumnIndex(BtnCopyDecryptedText,1);

    }

}
