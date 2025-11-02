package com.example.fileintegritychecker.service;

public enum DecryptionCategory {

//    DECRYPTION("Decryption"),
    CIPHER("Cipher"),
    SIGNATURE("Signature"),
    KEYFACTORY("KeyFactory"),
    BACK("← Back");

    private final String displayName;

    DecryptionCategory(String displayName) {
        this.displayName = displayName;
    }

}
