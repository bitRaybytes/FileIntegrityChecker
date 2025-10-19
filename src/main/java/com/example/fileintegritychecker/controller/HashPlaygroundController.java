package com.example.fileintegritychecker.controller;

import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class HashPlaygroundController {
    @FXML private Label hashHeader;
    @FXML private Label hashSubheader;
    @FXML private Label hashResult;
    @FXML private TextArea inputTextArea;
    @FXML private Button BtnGenerateHash;
    @FXML private GridPane hashGridPane;

    @FXML
    private void initialize() {
        initalizeGridPane();

        // Button action to generate hash (functionality to be implemented)
        BtnGenerateHash.setOnAction(e -> {
            System.out.println("Generating hash...");
        });
    }

    @FXML
    private void initalizeGridPane() {
        // Elements setup
        hashHeader.setText("Hash Playground");
        hashSubheader.setText("Experiment with different hashing algorithms and see their outputs.");

        inputTextArea.setWrapText(true);
        inputTextArea.setText("Enter text here to hash...");
        inputTextArea.prefWidth(300);
        inputTextArea.prefHeight(150);

        BtnGenerateHash.setText("Generate Hash");
        hashResult.setText("Hash Result will be displayed here.");

        // GridPane setup rows and columns
        // Rows
        GridPane.setRowIndex(hashHeader,0);
        GridPane.setRowIndex(hashSubheader,1);
        GridPane.setRowIndex(inputTextArea,2);
        GridPane.setRowIndex(BtnGenerateHash,3);
        GridPane.setRowIndex(hashResult,4);
        // Columns
        GridPane.setColumnIndex(hashHeader,0);
        GridPane.setColumnIndex(hashSubheader,0);
        GridPane.setColumnIndex(inputTextArea,0);
        GridPane.setColumnIndex(BtnGenerateHash,0);
        GridPane.setColumnIndex(hashResult,0);

        // Alignment and spacing
        hashGridPane.setVgap(10);
        hashGridPane.setHgap(10);
        hashGridPane.setPadding(new Insets(10));
        hashGridPane.setAlignment(Pos.CENTER);

    }
}
