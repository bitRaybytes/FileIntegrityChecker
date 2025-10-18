package com.example.fileintegritychecker.util;

import javafx.stage.FileChooser;

import java.io.File;

/**
 * Author:
 *  Ray
 * */
public class FileUtils {


    public static File showFileChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select a file to check");
        return chooser.showOpenDialog(null);
    }
}
