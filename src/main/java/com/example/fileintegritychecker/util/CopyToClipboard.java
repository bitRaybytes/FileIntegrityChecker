package com.example.fileintegritychecker.util;

import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CopyToClipboard {

    public CopyToClipboard(){}

    /** --- Copy results --- */
    public void copyToClipboard(Label label) {
        // Copy the result to clipboard
        ClipboardContent content = new ClipboardContent();
        // Put the text from the label into the clipboard content
        content.putString(label.getText());
        // Copy the content to the system clipboard
        Clipboard.getSystemClipboard().setContent(content);
    }
}
