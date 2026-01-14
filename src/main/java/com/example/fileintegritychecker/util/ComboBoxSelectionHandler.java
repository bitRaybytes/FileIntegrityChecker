package com.example.fileintegritychecker.util;

import com.example.fileintegritychecker.model.AlgorithmProvider;
import com.example.fileintegritychecker.service.DecryptionCategory;
import com.example.fileintegritychecker.service.EncryptionCategory;
import com.example.fileintegritychecker.service.EncryptionService;
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
        List<String> mainCategories = new ArrayList<>();


        List<String> categoryNames = algorithmMap.keySet()
                        .stream()
                                .map(Enum::toString)
                                        .toList();
        comboBox.getItems().setAll(categoryNames);
        comboBox.setPromptText("Select Category");
        infoLabel.setText("Select a category to view algorithms");
    }

    public void showSubCategories(){
        List<Object> list = new ArrayList<>();

        for (List<String> strings : algorithmMap.values()) {
//            comboBox.getItems().
            list.add(strings);
//            System.out.println(list); // for debugging purposes
//            comboBox.getItems().setAll(String.valueOf(list));
            comboBox.setPromptText("Select Subcategory");
            infoLabel.setText("Select subcategory");
//            comboBox.getSelectionModel().selectFirst();
        }

    }

    public void handleSelection() {

        String selected = comboBox.getSelectionModel().getSelectedItem();
        System.out.println("[DEBUG] Selected item: " + selected); // for debugging purposes

        if (selected == null) return;

        String isBackSelected = comboBox.getItems().getFirst();
        // Back zur Hauptansicht
        if (isBackSelected.contains(BACK)) {
            showMainCategories();
        }
        // Macht Probleme:
        // Lädt die Subkategorien, aber selektiert nicht das erste Element
        else {
            showSubCategories();
        }


        // Enum[] in ArrayList, filtered by e as selected
        T selectedEnum = Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.toString().equals(selected))
                .findFirst()
                .orElse(null);

        // If selected is an enum category
        if (selectedEnum != null && algorithmMap.containsKey(selectedEnum)) {
//            System.out.println("[DEBUG] Selected Enum Category: "+ selectedEnum); // for debugging purposes

            List<String> displayList = new ArrayList<>();
            List<String> algorithms = algorithmMap.get(selectedEnum);

            if (algorithms != null && !algorithms.isEmpty()) {
                displayList.addAll(algorithms);
            }
//            System.out.println("[DEBUG] Loaded algorithms for " + selectedEnum + ": " + displayList); // for debugging purposes

            Platform.runLater(() -> {
                comboBox.setItems(FXCollections.observableArrayList(displayList));
                comboBox.getSelectionModel().clearSelection();
                comboBox.setPromptText("Select Algorithm");
                comboBox.getItems().addFirst(BACK);

            });


            infoLabel.setText("Algorithms in " + selected + ":");
        } else {
            // Es ist ein Algorithmus
            selectedAlgorithm = selected;
            infoLabel.setText("Selected algorithm: " + selected);

//            System.out.println("[DEBUG] Selected algorithm: " + selectedAlgorithm); // for debugging purposes
        }
    }



    public String getSelectedAlgorithm() {
        return selectedAlgorithm;
    }
}
