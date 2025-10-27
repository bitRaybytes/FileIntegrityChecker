package com.example.fileintegritychecker.model;

import com.example.fileintegritychecker.service.EncryptionCategory;
import com.example.fileintegritychecker.service.EncryptionService;

import java.security.Security;
import java.util.*;

public class AlgorithmProvider {

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
        Set<String> algorithms = Security.getAlgorithms(String.valueOf(category).toString()
                .replace("[", "")
                .replace("]", ""));

        for (String algorithm : algorithms) {
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

    public static String setEncryptionAlgorithmsAsString(EncryptionCategory category) {
//        return Arrays.asList(setAlgorithmsCategory(EncryptionCategory.MESSAGE_DIGEST));
        String categoryAlgorithms = Security.getAlgorithms(String.valueOf(category)).toString()
                .replace("[","")
                .replace("]","");


        return categoryAlgorithms;
    }

    static void main(String[] args) {
//        System.out.println(setEncryptionAlgorithms(EncryptionCategory.MESSAGEDIGEST));
//        System.out.println(setEncryptionAlgorithms(EncryptionCategory.CIPHER));
//        System.out.println(setEncryptionAlgorithms(EncryptionCategory.KEYGENERATOR));
//        System.out.println(setEncryptionAlgorithms(EncryptionCategory.KEYGENERATOR, 256));
//        System.out.println(setEncryptionAlgorithms(EncryptionCategory.MAC));
//        System.out.println(setEncryptionAlgorithms(EncryptionCategory.SECURERANDOM));
//        System.out.println(setEncryptionAlgorithms(EncryptionCategory.KEYFACTORY));
//        System.out.println(setEncryptionAlgorithms(EncryptionCategory.SIGNATURE));
        System.out.println(setEncryptionAlgoListStr(EncryptionCategory.CIPHER));
        System.out.println(setEncryptionAlgoListStr(EncryptionCategory.MAC));
        System.out.println(setEncryptionAlgoListStr(EncryptionCategory.MESSAGEDIGEST));


    }
}
