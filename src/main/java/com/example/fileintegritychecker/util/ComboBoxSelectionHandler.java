package com.example.fileintegritychecker.util;

import com.example.fileintegritychecker.model.AlgorithmProvider;
import com.example.fileintegritychecker.service.EncryptionCategory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class ComboBoxSelectionHandler {
    private ComboBox<String> comboBox;
    private Label encryptionResult;
    private Map<String, List<String>> algorithmMap;
    private String selectedAlgorithm;
    private Class<T> enumType;
    private List<String> algorithms;

    public ComboBoxSelectionHandler(ComboBox<String> comboBox){
        this.comboBox = comboBox;
    }

    public static <T extends Enum<T>> void populateComboBox(ComboBox<String> comboBox, Class<T> enumType, Map<String, List<String>> algorithmMap){
        comboBox.getItems().clear();

        // Add enum display names
        for (T constant: enumType.getEnumConstants()) {
            if (constant.equals("← Back")) {
                comboBox.getItems().add(constant.toString());
                // Store algorithms for each category
                algorithmMap.put(constant.toString(), AlgorithmProvider.getAlgorithmsByCategory(constant.getClass()));
            }
        }

        comboBox.setPromptText("Select Category");
    }

    public void handleComboBoxSelection(ComboBox<String> comboBox, Class<T> enumType, Map<String, List<String>> algorithmMap) {
        String selected = comboBox.getValue();
        if (selected == null) return;

        // BACK: Go back to categories
        if (selected.equals("← Back")) {
            populateComboBox(comboBox, enumType, algorithmMap);
            return;
        }

        // If the selection is a main category (like "Cipher" or "Mac")
        if (algorithmMap.containsKey(selected)) {
            algorithms = algorithmMap.get(selected);
            ObservableList<String> algoItems = FXCollections.observableArrayList();
            algoItems.add("← Back");
            algoItems.addAll(algorithms);

            Platform.runLater(() -> {
                comboBox.setItems(algoItems);
                comboBox.getSelectionModel().clearSelection();
                comboBox.setPromptText("Select Algorithm");
            });

            encryptionResult.setText("Select an algorithm from " + selected);
        } else {
            // User selected an actual algorithm
            selectedAlgorithm = selected;
            encryptionResult.setText("Selected algorithm: " + selectedAlgorithm);
        }
    }
}
