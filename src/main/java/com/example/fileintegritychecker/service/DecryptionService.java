package com.example.fileintegritychecker.service;

import java.security.Provider;
import java.security.Security;

public class DecryptionService {

    static void main(String[] args) {

//        Provider[] provider = Security.getProviders();
        for (Provider s : Security.getProviders()) {
            System.out.println(s);
        }
        String provider = Security.getProviders().toString();


        System.out.println(provider);

    }
}
