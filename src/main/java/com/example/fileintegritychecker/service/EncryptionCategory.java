package com.example.fileintegritychecker.service;

public enum EncryptionCategory {

    MESSAGE_DIGEST("Message Digest"),
    SYMMETRIC("Symmetric Encryption"),
    ASYMMETRIC("Asymmetric Encryption"),
    BACK("← Back");

    private final String displayName;

    EncryptionCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}


