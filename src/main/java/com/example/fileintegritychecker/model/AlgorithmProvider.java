package com.example.fileintegritychecker.model;

import com.example.fileintegritychecker.service.EncryptionCategory;
import com.example.fileintegritychecker.service.EncryptionService;

import java.security.Security;
import java.util.*;

public class AlgorithmProvider {

    /**
     * Returns a list of available algorithm names for the given encryption category.
     * Uses the standard Java Security API to fetch all providers.
     */
    public static List<String> getAlgorithmsByCategory(EncryptionCategory category) {
        if (category == null) {
            return List.of();
        }

        Set<String> algorithms = Security.getAlgorithms(category.toString());
        return new ArrayList<>(algorithms);
    }

    /**
     * Returns a single string (comma separated) of all available algorithms for display/logging purposes.
     */
    public static String getAlgorithmsAsString(EncryptionCategory category) {
        return String.join(", ", getAlgorithmsByCategory(category));
    }


    public static List<String> setEncryptionAlgorithms(EncryptionCategory category) {
        List<String> algorithmList = new ArrayList<>();
        Set<String> algorithms = Security.getAlgorithms(String.valueOf(category));

        for (String algorithm : algorithms) {
            algorithmList.add(algorithm);
        }

        return algorithmList;
    }

    public static List<String> setEncryptionAlgoListStr(EncryptionCategory category){
        List<String> algorithmList = new ArrayList<>();


        Set<String> algorithms = Security.getAlgorithms(String.valueOf(category));

        for (String algorithm : algorithms) {
            algorithm.split(", ");
            algorithmList.add(algorithm);
        }

        return algorithmList;
    }


    public static List<String> setEncryptionAlgorithms(EncryptionCategory category, int key_length) {
//        return Arrays.asList(setAlgorithmsCategory(EncryptionCategory.MESSAGE_DIGEST));
        List<String> mdList = new ArrayList<>();

        String categoryAlgorithms = Security.getAlgorithms(String.valueOf(category)).toString()
                .replace("[","")
                .replace("]","");

        for (String str : categoryAlgorithms.split(", ")) {
            mdList.add(str);
        }

        return mdList;
    }

    public static String setEncryptionAlgoString(EncryptionCategory category){
        String algoCategory = Security.getAlgorithms(String.valueOf(category)).toString()
                .replace("[","")
                .replace("]","");
        return algoCategory;
    }

    public static String[] setEncryptionAlgorithmsAsString(EncryptionCategory category) {
//        return Arrays.asList(setAlgorithmsCategory(EncryptionCategory.MESSAGE_DIGEST));


        String[] categoryAlgorithms = (Security.getAlgorithms(String.valueOf(category)).toString()
                .replace("[","")
                .replace("]","").split(", "));

        for (String str : categoryAlgorithms){
            categoryAlgorithms.toString();
        }

        return categoryAlgorithms;
    }

    static void main(String[] args) {
//        System.out.println(setEncryptionAlgoListStr(EncryptionCategory.MAC));
        System.out.println(setEncryptionAlgoListStr(EncryptionCategory.MESSAGEDIGEST));

//        List<String> list = List.of(setEncryptionAlgorithmsAsString(EncryptionCategory.MESSAGEDIGEST));
//        String str = Security.getProperty(EncryptionCategory.MESSAGEDIGEST.toString());
//        System.out.println(list);


    }
}
