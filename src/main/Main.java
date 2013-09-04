package main;

import common.LogPrintFile;

/*
 * Main
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Initialize and create mainframe.
 */

public class Main {
    public static void main(final String[] args) {
        LogPrintFile.Initialize();
        new MainFrame();
    }
}