package com.example.fileintegritychecker.service;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class IntegrityService {
    /**
     * Berechnet die Prüfsumme einer Datei mit dem angegebenen Algorithmus.
     * @param file Die Datei, die geprüft werden soll.
     * @param algorithm Algorithmus wie "MD5", "SHA-1", "SHA-256".
     * @return Hash der Datei als Hex-String.
     * @throws Exception bei Problemen beim Lesen oder Algorithmus.
     */
    public String computeHash(File file, String algorithm) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algorithm);

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, read);
            }
            // Bytes in Hex-String umwandeln
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
