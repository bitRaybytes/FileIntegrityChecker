package com.example.fileintegritychecker.service;

import com.example.fileintegritychecker.controller.EncryptionToolController;

import javax.crypto.*;
import java.security.*;
import java.util.Base64;


/**
 * A general-purpose EncryptionService that supports multiple algorithms.
 * Supports AES, DES, Blowfish (symmetric) and RSA (asymmetric) encryption.
 */
public class EncryptionService {


    private EncryptionCategory encryptionCategory;

    /**
     * Encrypts a plain text after using the selected algorithm.
     */
    public String encrypt(String plainText, String algorithm) throws Exception {
        // Prüfe zuerst, zu welcher Kategorie der Algorithmus gehört
        EncryptionCategory category = determineCategory(algorithm);


        switch (category) {
            case MESSAGEDIGEST:
                System.out.println(computeMessageDigest(plainText, algorithm));
                return computeMessageDigest(plainText, algorithm);
            case CIPHER:
                return encryptWithCipher(plainText, algorithm);
            case KEYGENERATOR:
                return encryptSymmetric(plainText, algorithm);
            case MAC:
                return computeMAC(plainText, algorithm);
            case SECURERANDOM:
                return generateSecureRandom(plainText);
            case SIGNATURE:
                return signData(plainText, algorithm);
            default:
                throw new IllegalArgumentException("Nicht unterstützter Algorithmus: " + algorithm);
        }
    }
    private EncryptionCategory determineCategory(String algorithm) {
        if (algorithm.contains("MD") || algorithm.contains("SHA")) {
            return EncryptionCategory.MESSAGEDIGEST;
        } else if (algorithm.contains("AES") || algorithm.contains("DES")) {
            return EncryptionCategory.CIPHER;
        } // ... weitere Bedingungen für andere Kategorien

        throw new IllegalArgumentException("Kategorie nicht gefunden für: " + algorithm);
    }
    public String computeMessageDigest(String input, String algorithm) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hash = digest.digest(input.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    private String encryptWithCipher(String input, String algorithm) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        SecretKey secretKey = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    private String computeMAC(String input, String algorithm) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        SecretKey key = keyGen.generateKey();
        Mac mac = Mac.getInstance(algorithm);
        mac.init(key);
        byte[] macBytes = mac.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(macBytes);
    }

    private String generateSecureRandom(String input) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    // Standard KeySize 2048
    private String signData(String input, String algorithm) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(pair.getPrivate());
        signature.update(input.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    // individual Keysize, but has to be at least 512 for RSA
    private String signData(String input, String algorithm, int keySize) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
        keyGen.initialize(keySize);
        KeyPair pair = keyGen.generateKeyPair();
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(pair.getPrivate());
        signature.update(input.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }












    public String encryptSymmetric(String plainText, String algorithm) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(String.valueOf(algorithm));

        SecretKey secretKey = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance(String.valueOf(algorithm));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }


    private String encryptRSA(String plainText, String algorithm) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);
//        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }



    private String encryptRSA(String plainText, String algorithm, int keySize) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);
        keyPairGen.initialize(keySize);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    static void main(String[] args) throws Exception {
        System.out.println(new EncryptionService().encryptRSA("Hello, World!", new EncryptionService().computeMessageDigest("Hello, World!", EncryptionCategory.MESSAGEDIGEST.toString())));
    }
}
