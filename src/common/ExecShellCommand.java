package common;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import panel.InstallingPanel;

import dialog.ErrorDialog;

import main.MainFrame;

/*
 * ExecShellCommand
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Execute linux shell command.
 */

public final class ExecShellCommand {

   private static MainFrame main_frame;

    public static void setMainFrame(final MainFrame main_frame_org) {
        ExecShellCommand.main_frame = main_frame_org;
        return;
    }

    /*
     * execShellCommand
     * Execute command_org and write the result on log-file.
     */

    public static String execShellCommand(final String[] command_org) {
        List<String> command = new ArrayList<String>();
        for (int i = 0; i < command_org.length; i++) {
            command.add(command_org[i]);
        }

        ProcessBuilder process_builder = new ProcessBuilder(command);
        process_builder.redirectErrorStream(true);
        InputStreamReader input_stream_reader = null;
        String result = "";

        try {
            Process process = process_builder.start();
            input_stream_reader =
                    new InputStreamReader(process.getInputStream(), "UTF-8");
            while (true) {
                int c = input_stream_reader.read();
                if (c == -1) {
                    input_stream_reader.close();
                    break;
                }
                result += String.valueOf((char) c);
            }
            process.waitFor();
            LogPrintFile.addLogInfo(result);
            int ret = process.exitValue();
            if (ret != 0) {
                new ErrorDialog(
                        Define.ERROR_MESSAGE_1
                        + LogPrintFile.getLog_file_name()
                        + Define.ERROR_MESSAGE_2, ExecShellCommand.main_frame);
            }
        } catch (Exception e) {
            new ErrorDialog(
                    Define.ERROR_MESSAGE_1
                    + LogPrintFile.getLog_file_name()
                    + Define.ERROR_MESSAGE_2, ExecShellCommand.main_frame);
        }
        return result.trim();
    }

    /*
     * execShellCommandWithoutWrite
     * Execute command_org.
     */

    public static void execShellCommandWithoutWrite(
            final String[] command_org) {
        List<String> command = new ArrayList<String>();
        for (int i = 0; i < command_org.length; i++) {
            command.add(command_org[i]);
        }

        ProcessBuilder process_builder = new ProcessBuilder(command);
        process_builder.redirectErrorStream(true);
        InputStreamReader input_stream_reader = null;

        try {
            Process process = process_builder.start();
            input_stream_reader =
                    new InputStreamReader(process.getInputStream(), "UTF-8");
            while (true) {
                int c = input_stream_reader.read();
                if (c == -1) {
                    input_stream_reader.close();
                    break;
                }
            }
            process.waitFor();
            int ret = process.exitValue();
            if (ret != 0) {
                new ErrorDialog(
                        Define.ERROR_MESSAGE_1
                        + LogPrintFile.getLog_file_name()
                        + Define.ERROR_MESSAGE_2, ExecShellCommand.main_frame);
                while (true) { }
            }
        } catch (Exception e) {
            new ErrorDialog(
                    Define.ERROR_MESSAGE_1
                    + LogPrintFile.getLog_file_name()
                    + Define.ERROR_MESSAGE_2, ExecShellCommand.main_frame);
            while (true) { }
        }
        return;
    }

    /*
     * execShellCommandWithReturn
     * Execute command_org and write the result on log-file
     * and return the result.
     */

    public static int execShellCommandWithReturn(final String[] command_org) {
        List<String> command = new ArrayList<String>();
        for (int i = 0; i < command_org.length; i++) {
            command.add(command_org[i]);
        }

        ProcessBuilder process_builder = new ProcessBuilder(command);
        process_builder.redirectErrorStream(true);
        InputStreamReader input_stream_reader = null;
        String result = "";

        try {
            Process process = process_builder.start();
            input_stream_reader = new InputStreamReader(
                    process.getInputStream(), "UTF-8");
            while (true) {
                int c = input_stream_reader.read();
                if (c == -1) {
                    input_stream_reader.close();
                    break;
                }
                result += String.valueOf((char) c);
            }
            process.waitFor();
            LogPrintFile.addLogInfo(result);
            int ret = process.exitValue();
            return ret;
        } catch (Exception e) {
            new ErrorDialog(
                    Define.ERROR_MESSAGE_1
                    + LogPrintFile.getLog_file_name()
                    + Define.ERROR_MESSAGE_2, ExecShellCommand.main_frame);
        }
        return -1;
    }

    /*
     * execshellCommandWithPrintLogToPanel
     * Execute command_org and write the result on log-file and show the result.
     */

    public static void execShellCommandWithPrintLogToPanel(
            final String[] command_org,
            final InstallingPanel installing_panel) {
        List<String> command = new ArrayList<String>();
        for (int i = 0; i < command_org.length; i++) {
            command.add(command_org[i]);
        }

        ProcessBuilder process_builder = new ProcessBuilder(command);
        process_builder.redirectErrorStream(true);
        InputStreamReader input_stream_reader = null;
        String result = "";

        try {
            Process process = process_builder.start();
            input_stream_reader =
                    new InputStreamReader(process.getInputStream(), "UTF-8");
            while (true) {
                int c = input_stream_reader.read();
                if (c == -1) {
                    input_stream_reader.close();
                    break;
                }
                installing_panel.addStdLogInfoNoNewLine(
                        String.valueOf((char) c));
                result += String.valueOf((char) c);
            }
            process.waitFor();
            installing_panel.addStdLogInfoAndNewLine("");
            LogPrintFile.addLogInfo(result);
            int ret = process.exitValue();
            if (ret != 0) {
                new ErrorDialog(
                        Define.ERROR_MESSAGE_1
                        + LogPrintFile.getLog_file_name()
                        + Define.ERROR_MESSAGE_2, ExecShellCommand.main_frame);
                while (true) { }
            }
        } catch (Exception e) {
            new ErrorDialog(
                    Define.ERROR_MESSAGE_1
                    + LogPrintFile.getLog_file_name()
                    + Define.ERROR_MESSAGE_2, ExecShellCommand.main_frame);
            while (true) { }
        }
        return;
    }
}
