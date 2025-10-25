package com.example.fileintegritychecker.model;

import com.example.fileintegritychecker.service.EncryptionCategory;

import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlgorithmProvider {
//    private static final String MESSAGEDIGEST = Security.getAlgorithms(String.valueOf(EncryptionCategory.MESSAGE_DIGEST)).toString()
//            .replace("[","")
//            .replace("]","");

    public static String setAlgorithmsCategory(EncryptionCategory category){
        String categoryAlgorithms = Security.getAlgorithms(String.valueOf(category)).toString()
                .replace("[","")
                .replace("]","");
        return categoryAlgorithms;
    }

    public static List<String> getMessageDigestAlgorithms() {
//        return Arrays.asList(setAlgorithmsCategory(EncryptionCategory.MESSAGE_DIGEST));
        return Arrays.asList(setAlgorithmsCategory(EncryptionCategory.MESSAGE_DIGEST).split(", "));

//        return Collections.singletonList(setAlgorithmsCategory(EncryptionCategory.MESSAGE_DIGEST));
    }

    public static List<String> getSymmetricAlgorithms() {
        return Arrays.asList(setAlgorithmsCategory(EncryptionCategory.SYMMETRIC));
    }

    public static List<String> getAsymmetricAlgorithms() {
        return Arrays.asList(setAlgorithmsCategory(EncryptionCategory.ASYMMETRIC));
    }

    private static void setEncryptionAlgorithms(){

    }

}
