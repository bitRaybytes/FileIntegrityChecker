package com.example.fileintegritychecker.model;

public enum EncryptionCategory {

    MESSAGEDIGEST("MessageDigest"),
    CIPHER("Cipher"),
    KEYFACTORY("KeyFactory"),
    KEYGENERATOR("KeyGenerator"),
    MAC("Mac"),
    SECURERANDOM("SecureRandom"),
    SIGNATURE("Signature");


    private final String displayName;

    EncryptionCategory(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName(){return displayName;}


}


