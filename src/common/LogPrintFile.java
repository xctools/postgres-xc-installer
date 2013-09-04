package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;

/*
 * LogPrintFile
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Create postgres_xc_gui_installaer_log.
 */

public class LogPrintFile {

    private static String log_file_name;
    private static String log_time;

    public static void Initialize() {
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        year = year.substring(2);
        String month = String.format("%02d", (cal.get(Calendar.MONTH) + 1));
        String date = String.format("%02d", cal.get(Calendar.DATE));
        String hour_of_day =
                String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));
        String minute = String.format("%02d", cal.get(Calendar.MINUTE));
        log_time = year + month + date + "_" + hour_of_day + minute;
        log_file_name = "postgres_xc_gui_installer_" + log_time + ".log";
        return;
    }

    /*
     * addLogInfo
     * Add log_info to postgres_xc_gui_installer_log.
     */

    public static void addLogInfo(final String log_info) {
        try {
            File log_file = new File(LogPrintFile.log_file_name);
            PrintWriter print_writer =
                    new PrintWriter(new BufferedWriter(
                            new FileWriter(log_file, true)));
            print_writer.println(log_info);
            print_writer.close();
        } catch (Exception e) { }
        return;
    }

    public static String getLog_file_name() {
        return log_file_name;
    }

    public static String getLog_time() {
        return log_time;
    }
}