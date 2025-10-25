package com.example.fileintegritychecker.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

public class HashingService {

    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 256;

    private static String algorithms = Security.getAlgorithms("MessageDigest").toString()
            .replace("[","")
            .replace("]","");


    public HashingService(){}

    public static String hashText(String input, String algorithms, boolean addSalt) {
        try {
            String textToHash = input;

            // Add salt if requested
            if (addSalt) {
                textToHash += generateSalt();
            }
            MessageDigest digest = MessageDigest.getInstance(algorithms);
            byte[] hashBytes = digest.digest(textToHash.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateSalt(){
//        String saltHash = "";

        byte[] salt = new byte[16]; // Do not alter or modify the value of the instance 'salt'
        new SecureRandom().nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }


    public static String hashString(String input, String algorithms) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithms);
            byte[] hashBytes = digest.digest(input.getBytes());
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static ObservableList<String> getAvailableAlgorithms(){
        Set<String> algorithmSet = Security.getAlgorithms("MessageDigest");
        List<String> algorithmList  = new ArrayList<>(algorithmSet);

        Collections.sort(algorithmList ); // sort alphabetically for nice display
        return FXCollections.observableArrayList(algorithmList);
    }
}
