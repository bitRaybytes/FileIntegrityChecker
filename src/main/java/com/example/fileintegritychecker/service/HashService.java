package com.example.fileintegritychecker.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Author:
 *  Ray
 * */
public class HashService {

    private String algorithm;

    public String computeHash(File file, String algo) throws Exception{
        MessageDigest digest = MessageDigest.getInstance(algorithm);

        try (InputStream fis = new FileInputStream(file)) {
            byte[] bytesBuffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(bytesBuffer)) != -1) {
                digest.update(bytesBuffer, 0, bytesRead);
            }

            byte[] hashedBytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error computing hash";
        }
    }

}
