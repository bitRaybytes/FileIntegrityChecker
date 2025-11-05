package com.example.fileintegritychecker.util;

import com.example.fileintegritychecker.model.AlgorithmProvider;
import com.example.fileintegritychecker.service.DecryptionCategory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.*;

public class ComboBoxSelectionHandler<T extends Enum<T>>  {

    private final ComboBox<String> comboBox;
    private final Label infoLabel;
    private final Class<T> enumType;
    private Map<T, List<String>> algorithmMap;
    private static final String BACK = "← Back";
    private String selectedAlgorithm; // for getSelectedAlgorithm()


    public ComboBoxSelectionHandler(ComboBox<String> comboBox, Label infoLabel, Class<T> enumType)
    {
        this.comboBox = comboBox;
        this.infoLabel = infoLabel;
        this.enumType = enumType;
        this.algorithmMap = AlgorithmProvider.getAlgorithmsForEnum(enumType);
    }



    /** Zeigt alle Enum-Kategorien in der ComboBox */
    public void showMainCategories() {
        List<String> categoryNames = algorithmMap.keySet()
                        .stream()
                                .map(Enum::toString)
                                        .toList();
        comboBox.getItems().setAll(categoryNames);
//        comboBox.getItems().setAll(String.valueOf(algorithmMap.keySet()).split(", "));
        comboBox.setPromptText("Select Category");
        infoLabel.setText("Select a category to view algorithms");
    }


    public void handleSelection() {

        String selected = comboBox.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        // Back zur Hauptansicht
        if (selected.equals(BACK)) {
            showMainCategories();
//            return;
        }

        // Enum[] in ArrayList, filtered by e
        T selectedEnum = Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.toString().equals(selected))
                .findFirst()
                .orElse(null);

        // Wenn es eine Kategorie ist
        if (selectedEnum != null && algorithmMap.containsKey(selectedEnum)) {
            System.out.println("[DEBUG] Selected Enum Category: "+ selectedEnum);

            List<String> displayList = new ArrayList<>();
//            List<String> algorithms = algorithmMap.getOrDefault(selectedEnum, Collections.emptyList());
            List<String> algorithms = algorithmMap.get(selectedEnum);

            if (algorithms != null && !algorithms.isEmpty()) {
                displayList.addAll(algorithms);
            }
            System.out.println("[DEBUG] Loaded algorithms for " + selectedEnum + ": " + displayList);

            Platform.runLater(() -> {
                comboBox.setItems(FXCollections.observableArrayList(displayList));
                comboBox.getSelectionModel().clearSelection();
                comboBox.getItems().add(0, BACK);
//                comboBox.getItems().addFirst(BACK); // next to try
                comboBox.setPromptText("Select Algorithm");
            });


            infoLabel.setText("Algorithms in " + selected + ":");
        } else {
            // Es ist ein Algorithmus
            selectedAlgorithm = selected;
            infoLabel.setText("Selected algorithm: " + selected);

            System.out.println("[DEBUG] Selected algorithm: " + selectedAlgorithm);
        }
    }



    public String getSelectedAlgorithm() {
        return selectedAlgorithm;
    }
}
