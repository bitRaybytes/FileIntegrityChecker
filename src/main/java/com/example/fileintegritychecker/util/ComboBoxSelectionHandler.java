package com.example.fileintegritychecker.util;

import com.example.fileintegritychecker.model.AlgorithmProvider;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.*;

public class ComboBoxSelectionHandler<T extends Enum<T>>  {

    private final ComboBox<String> comboBox;
    private final Label infoLabel;
    private final Class<T> enumType;
    private Map<T, List<String>> algorithmMap;
    private static final String BACK = "← Back";


    public ComboBoxSelectionHandler(ComboBox<String> comboBox, Label infoLabel, Class<T> enumType)
    {
        this.comboBox = comboBox;
        this.infoLabel = infoLabel;
        this.enumType = enumType;
        this.algorithmMap = AlgorithmProvider.getAlgorithmsForEnum(enumType);
    }

    /** Zeigt alle Enum-Kategorien in der ComboBox */
    public void showMainCategories() {
        comboBox.getItems().setAll(String.valueOf(algorithmMap.keySet()).split(", "));
        comboBox.setPromptText("Select Category");
        infoLabel.setText("Select a category to view algorithms");
    }


    public void handleSelection() {
        String selected = comboBox.getValue();
        if (selected == null) return;

        // Back zur Hauptansicht
        if (selected.equals(BACK)) {
            showMainCategories();
            return;
        }

        // Wenn es eine Kategorie ist
        if (algorithmMap.containsKey(selected)) {
            List<String> algorithms = algorithmMap.get(selected);
            List<String> displayList = new ArrayList<>();
            displayList.add(BACK);
            displayList.addAll(algorithms);

            Platform.runLater(() -> {
                comboBox.setItems(FXCollections.observableArrayList(displayList));
                comboBox.getSelectionModel().clearSelection();
                comboBox.setPromptText("Select Algorithm");
            });

            infoLabel.setText("Algorithms in " + selected + ":");
        } else {
            // Es ist ein Algorithmus
            infoLabel.setText("Selected Algorithm: " + selected);
        }
    }
}
