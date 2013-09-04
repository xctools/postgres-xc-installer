package common;

import java.awt.Component;

import java.io.File;

import javax.swing.JFileChooser;

/*
 * UseJFileChooser
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Create browse dialog.
 */

public class UseJFileChooser {
    public static String getChoseDirectoryName(final Component component) {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int selected = filechooser.showOpenDialog(component);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            return file.getAbsolutePath();
        }
        return "";
    }

    /*
     * getChoseFileName
     * Get chosen file name in browse dialog.
     */

    public static String getChoseFileName(final Component component) {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int selected = filechooser.showOpenDialog(component);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            return file.getAbsolutePath();
        }
        return "";
    }
}