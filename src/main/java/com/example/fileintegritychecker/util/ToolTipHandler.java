package com.example.fileintegritychecker.util;

import javafx.scene.control.Tooltip;
import javafx.scene.Node;


/**
 * Author: Ray
 * Utility class for easily creating and attaching Tooltips to JavaFX components.
 */

public class ToolTipHandler {
    // prevent instantiation - this is a utility class
    private ToolTipHandler(){}

    /**
     * Creates a new Tooltip with the specified text.
     *
     * @param text the tooltip text
     * @return a configured Tooltip instance
     */

//    public static Tooltip setToolTipText(String toolTipTxt){
//        return new Tooltip(toolTipTxt);
//    }

    public static Tooltip createToolTip(String text) {
        return new Tooltip(text);
    }

    /**
     * Attaches a tooltip with the specified text to the given JavaFX node.
     *
     * @param node the Node to attach the tooltip to
     * @param text the tooltip text
     */
    public static void attachToolTips(Node node, String text) {
        Tooltip tooltip = new Tooltip(text);
        Tooltip.install(node, tooltip);
    }

    /**
     * Attaches a tooltip with a configurable delay (in milliseconds).
     *
     * @param node the Node to attach the tooltip to
     * @param text the tooltip text
     * @param delayMillis the delay before showing the tooltip (ms)
     */
    public static void attachToolTips(Node node, String text, int delayMillis) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(javafx.util.Duration.millis(delayMillis));
        Tooltip.install(node, tooltip);
    }


}
