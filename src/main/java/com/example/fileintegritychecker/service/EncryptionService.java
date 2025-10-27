package com.example.fileintegritychecker.service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.util.Base64;



/**
 * A general-purpose EncryptionService that supports multiple algorithms.
 * Supports AES, DES, Blowfish (symmetric) and RSA (asymmetric) encryption.
 */
public class EncryptionService {

//    public enum Algorithm {
//        AES("AES"),
//        DES("DES"),
//        BLOWFISH("Blowfish"),
//        RSA("RSA");
//
//        private final String name;
//
//        Algorithm(String name) {
//            this.name = name;
//        }
//
//        public String getName() {
//            return name;
//        }
//    }

    private EncryptionCategory encryptionCategory;

    /**
     * Encrypts a plain text after using the selected algorithm.
     */
    public String encrypt(String plainText, EncryptionCategory category) throws Exception {
        switch (category) {

            case EncryptionCategory.MESSAGEDIGEST:
                return encryptSymmetric(plainText, EncryptionCategory.valueOf(String.valueOf(category).split(", ")[0]));
            case encryptionCategory.CIPHER:
                return encryptSymmetric(plainText, EncryptionCategory.valueOf(String.valueOf(category)));
            case encryptionCategory.KEYFACTORY:
                return encryptSymmetric(plainText, EncryptionCategory.valueOf(String.valueOf(category)));
            case encryptionCategory.KEYGENERATOR:
                return encryptSymmetric(plainText, EncryptionCategory.valueOf(String.valueOf(category)));
            case encryptionCategory.MAC:
                return encryptSymmetric(plainText, EncryptionCategory.valueOf(String.valueOf(category)));
            case encryptionCategory.SECURERANDOM:
                return encryptSymmetric(plainText, EncryptionCategory.valueOf(String.valueOf(category)));
            case encryptionCategory.SIGNATURE:
                return encryptSymmetric(plainText, EncryptionCategory.valueOf(String.valueOf(category)));
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + category);
        }
    }

    public String encryptSymmetric(String plainText, EncryptionCategory algorithm) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(String.valueOf(algorithm));
//        if (algorithm.equals("DES")) {
//            keyGen.init(56); // DES uses a 56-bit key
//        }
        SecretKey secretKey = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance(String.valueOf(algorithm));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String encryptRSA(String plainText) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
