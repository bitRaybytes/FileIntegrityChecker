package com.example.fileintegritychecker.controller;

import com.example.fileintegritychecker.service.HashingService;
import com.example.fileintegritychecker.util.ToolTipHandler;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;


/**
 * Author: Ray
 */
public class HashPlaygroundController {
    @FXML private Label hashHeader;
    @FXML private Label hashSubheader;
    @FXML private Label hashResult;
    @FXML private TextArea inputTextArea;
    @FXML private Button BtnGenerateHash;
    @FXML private Button copyHashValue;
    @FXML private GridPane hashGridPane;
    @FXML private CheckBox addSaltToIt;
    @FXML private ComboBox<String> hashAlgoComboBox;
    private final boolean isChecked = false;

    private final String TITLE = "Hash Playground";
    private final String SUBTITLE = "Experiment with different hashing algorithms and see their outputs.";
    private final String TEXTFIELDTXT = "Enter text here to hash...";
    private final String SALTING = "Add salt to hash";
    private final String HASHTXT = "Generate Hash";
    private final String HASHRESULT = "Hash Result will be displayed here.";
    private final String EMPTYTEXTERR = "⚠ Please enter some text first!";
    private final String COPYTOCLIPBOARD = "Copy hash value to clipboard";

    @FXML
    private void initialize() {
        initalizeGridPane();



        inputTextArea.setOnMouseClicked(e -> {
            if (inputTextArea.getText().equals(TEXTFIELDTXT)){
                inputTextArea.clear();
            }

        });

        // Button action to generate hash
        BtnGenerateHash.setOnAction(e -> {
            String selectedAlgorithm = (String) hashAlgoComboBox.getValue();
            String input = inputTextArea.getText();
            boolean useSalt = addSaltToIt.isSelected();

            String result = HashingService.hashText(input, selectedAlgorithm, useSalt);
            hashResult.setText(result);

            if (input == null || input.isBlank()) {
                hashResult.setText(EMPTYTEXTERR);
            }
        });

        copyHashValue.setOnAction(e -> {
            if (!hashResult.getText().equals(HASHRESULT)){
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(hashResult.getText());
                clipboard.setContent(content);
            }

        });
    }

    public HashPlaygroundController(){}

    public String getInputText(){
        return inputTextArea.getText();
    }

    @FXML
    private void initalizeGridPane() {
        // Element Properties
        hashHeader.setText(TITLE);
        hashSubheader.setText(SUBTITLE);

        inputTextArea.setWrapText(true);
        inputTextArea.setText(TEXTFIELDTXT);
        inputTextArea.prefWidth(300);
        inputTextArea.prefHeight(100);

        addSaltToIt.setText(SALTING);

        hashAlgoComboBox.setItems(HashingService.getAvailableAlgorithms());
        hashAlgoComboBox.getSelectionModel().selectFirst();

        BtnGenerateHash.setText(HASHTXT);

        copyHashValue.setGraphic(new FontIcon("mdi2c-content-copy"));
        copyHashValue.setContentDisplay(ContentDisplay.CENTER);

        ToolTipHandler.attachToolTips(copyHashValue, COPYTOCLIPBOARD, 500);

        hashResult.setText(HASHRESULT);
        hashResult.setWrapText(true);
        hashResult.setPrefWidth(350);

        // GridPane setup rows and columns
        // Rows
        GridPane.setRowIndex(hashHeader,0);
        GridPane.setRowIndex(hashSubheader,1);
        GridPane.setRowIndex(inputTextArea,2);
        GridPane.setRowIndex(hashResult,3);
        GridPane.setRowIndex(hashAlgoComboBox, 4);
        GridPane.setRowIndex(BtnGenerateHash,5);
        GridPane.setRowIndex(addSaltToIt, 6);
        GridPane.setRowIndex(copyHashValue, 5);
        // Columns
        GridPane.setColumnIndex(hashHeader,0);
        GridPane.setColumnIndex(hashSubheader,0);
        GridPane.setColumnIndex(inputTextArea,0);
        GridPane.setColumnIndex(addSaltToIt, 0);
        GridPane.setColumnIndex(hashAlgoComboBox,0);
        GridPane.setColumnIndex(BtnGenerateHash,0);
        GridPane.setColumnIndex(copyHashValue,1);
        GridPane.setColumnIndex(hashResult,0);

        // Colum span
        GridPane.setColumnSpan(hashHeader, 2);
        GridPane.setColumnSpan(hashSubheader,2);
        GridPane.setColumnSpan(inputTextArea,2);
        GridPane.setColumnSpan(hashResult,2);

        // Alignment and spacing
        hashGridPane.setVgap(10);
        hashGridPane.setHgap(10);
        hashGridPane.setPadding(new Insets(10));
        hashGridPane.setAlignment(Pos.CENTER);

    }

    // TODO Checkbox for adding salt to a password. If checked the password appends an individual salt to the hash

}
