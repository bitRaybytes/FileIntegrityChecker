package com.example.fileintegritychecker.service;

public enum DecryptionCategory {

//    DECRYPTION("Decryption"),
    CIPHER("Cipher"),
    SIGNATURE("Signature"),
    KEYFACTORY("KeyFactory");


    private final String displayName;

    DecryptionCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName()
    {
        return displayName;
    }



}
