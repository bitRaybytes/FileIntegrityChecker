package com.example.fileintegritychecker.service;

import com.example.fileintegritychecker.model.AlgorithmProvider;
import javafx.scene.control.TextArea;

import javax.crypto.*;
import java.security.*;
import java.util.Base64;

public class DecryptionService {

    private TextArea inputText;
    private String algorithm;

    /**
     * Constructor for DecryptionService gets two parameters
     * @TextArea to receive input from user via TextArea.getText()
     * @algorithm to compute with the available algorithm
     * */
    public DecryptionService(TextArea inputText, String algorithm)
    {
        this.inputText = inputText;
        this.algorithm = algorithm;
    }

    /**
     * choose between values of enum class 'DecryptionCategory'
     * @inputText value of inputText.getText() which gets decoded
     * @algorithm gets the value for decrypting value of inputText
     * */

    public String decryptPatterns() {
        // Schritt 1: Algorithmus einer Kategorie zuordnen
        DecryptionCategory category = determineCategory(algorithm);

        // Schritt 2: Switch über die Kategorie
        switch (category) {
            case CIPHER:
                try {
                    return decryptCipherInput();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            case SIGNATURE:
                try {
                    return decryptSignatureInput();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            default:
                throw new IllegalArgumentException("Input could not be decoded: " + inputText);
        }
    }

    private final AlgorithmProvider algorithmProvider = new AlgorithmProvider();

    private DecryptionCategory determineCategory(String algorithm) {
        // Hole aus AlgorithmProvider die Listen
        var cipherAlgorithms = algorithmProvider.getAlgorithmsByCategory(DecryptionCategory.CIPHER);
        var signatureAlgorithms = algorithmProvider.getAlgorithmsByCategory(DecryptionCategory.SIGNATURE);

        if (cipherAlgorithms.contains(algorithm)) {
            return DecryptionCategory.CIPHER;
        } else if (signatureAlgorithms.contains(algorithm)) {
            return DecryptionCategory.SIGNATURE;
        } else {
            throw new IllegalArgumentException("Unbekannte Kategorie für Algorithmus: " + algorithm);
        }
    }



    /**
     * Method for decrypting input from textarea
     * @TextArea get the input with getText() via inputText from Controller
     * @algorithm get the available algorithm for computing with Cipher algorithms.
     * */
    public String decryptCipherInput() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // generate secretKey for decoding cipher text
//        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
//        SecretKey secretKey = keyGen.generateKey();

        // Get Instance of cipher algorithms
//        Cipher cipher = Cipher.getInstance(algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        // init cipher mode to decryption with generated secret key
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encodedChar = inputText.getText().getBytes();
        byte[] decodedChar = Base64.getDecoder().decode(encodedChar);

        return new String(cipher.doFinal(decodedChar));
    }


    /**
     * Decryption of signature patterns. Generates a keypair for each decryption
     * @inputText TextArea where the users input initially gets received.
     * @algorithm which is corresponding to users choice from combo box.
     * */
    public String decryptSignatureInput() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
//        keyGen.initialize();
        KeyPair keyPair = keyGen.generateKeyPair();

        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(keyPair.getPublic());
        signature.update(inputText.getText().getBytes());
        return (signature.verify(Base64.getDecoder().decode(inputText.getText())) ? "Valid" : "Invalid");
    }


}
