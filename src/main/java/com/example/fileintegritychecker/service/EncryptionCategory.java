package com.example.fileintegritychecker.service;

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


    @Override
    public String toString() {
        return displayName;
    }
}


