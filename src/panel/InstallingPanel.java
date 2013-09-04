package panel;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import common.CreateObject;
import common.Define;
import common.JLabelParameter;
import common.JScrollPaneParameter;

import main.MainFrame;


/*
 * InstallingPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Show stdout_log and pgxc_ctl_log
 */

public class InstallingPanel extends CommonPanel {

    private static final long serialVersionUID = -9050985901614534202L;
    private final JLabelParameter header2_label_parameter = new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.INSTALLING_PANEL_NAME, new Font(Font.SERIF, Font.PLAIN, 20));
    private final JLabelParameter please_label_parameter =
            new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, 20,
            "Please wait while Setup installs Postgres-XC",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private final JLabelParameter stdout_label_parameter = new JLabelParameter(
            10, 40, Define.MAIN_FRAME_WIDTH, 20,
            "stdout", new Font(Font.SERIF, Font.PLAIN, 15));
    private final JLabelParameter pgxc_ctl_log_label_parameter =
            new JLabelParameter(
            10, (Define.MAIN_PANEL_HEIGHT - 120) / 2 + 80,
            Define.MAIN_FRAME_WIDTH, 20,
            "pgxc_ctl_log", new Font(Font.SERIF, Font.PLAIN, 15));

    private JTextArea std_log_textarea;
    private JScrollPane std_log_scroll;
    private JScrollPaneParameter std_log_scroll_parameter =
            new JScrollPaneParameter(
            10, 70, Define.MAIN_FRAME_WIDTH - 20,
            (Define.MAIN_PANEL_HEIGHT - 120) / 2,
            Color.BLACK, 1, null);
    private Thread std_log_thread;

    private JTextArea pgxc_ctl_log_textarea;
    private JScrollPane pgxc_ctl_log_scroll;
    private JScrollPaneParameter	pgxc_ctl_log_scroll_parameter =
            new JScrollPaneParameter(
            10, (Define.MAIN_PANEL_HEIGHT - 120) / 2 + 110,
            Define.MAIN_FRAME_WIDTH - 20, (Define.MAIN_PANEL_HEIGHT - 120) / 2,
            Color.BLACK, 1, null);
    private Thread pgxc_ctl_log_thread;

    private String pgxc_ctl_log_file_name;
    private boolean thread_is_runnable;

    public InstallingPanel(final MainFrame main_frame_org) {
        super(main_frame_org);

        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.please_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.stdout_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.pgxc_ctl_log_label_parameter));
        this.back_button.setEnabled(false);
        this.next_button.setEnabled(false);
        this.cancel_button.setEnabled(false);

        this.std_log_textarea = new JTextArea();
        this.std_log_textarea.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        this.std_log_textarea.setEditable(false);
        this.std_log_textarea.setLineWrap(true);
        this.std_log_scroll_parameter.setComponent(this.std_log_textarea);
        this.std_log_scroll = CreateObject.createJScrollPane(
                this.std_log_scroll_parameter);
        this.main_panel.add(this.std_log_scroll);

        this.pgxc_ctl_log_textarea = new JTextArea();
        this.pgxc_ctl_log_textarea.setFont(new Font(Font.SERIF,
                Font.PLAIN, 15));
        this.pgxc_ctl_log_textarea.setEditable(false);
        this.pgxc_ctl_log_textarea.setLineWrap(true);
        this.pgxc_ctl_log_scroll_parameter.setComponent(
                this.pgxc_ctl_log_textarea);
        this.pgxc_ctl_log_scroll = CreateObject.createJScrollPane(
                this.pgxc_ctl_log_scroll_parameter);
        this.main_panel.add(this.pgxc_ctl_log_scroll);

        this.std_log_thread = new Thread() {
            public void run() {
                while (thread_is_runnable) {
                    repaintTextArea();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) { }
                }
            }
        };

        this.pgxc_ctl_log_thread = new Thread() {
            public void run() {
                String[] command_org = {"sh", "-c",
                        "tail -f " + pgxc_ctl_log_file_name};
                List<String> command = new ArrayList<String>();
                for (int i = 0; i < command_org.length; i++) {
                    command.add(command_org[i]);
                }

                ProcessBuilder process_builder = new ProcessBuilder(command);
                process_builder.redirectErrorStream(true);
                InputStreamReader input_stream_reader = null;
                Process process = null;
                try {
                    process = process_builder.start();
                    input_stream_reader =
                            new InputStreamReader(
                                    process.getInputStream(), "UTF-8");
                    } catch (Exception e) { }
                while (true) {
                    try {
                        addPgxcCtlLogInfoNoNewLine(
                                String.valueOf(
                                        (char) input_stream_reader.read()));
                    } catch (Exception e) { }
                    if (!thread_is_runnable) {
                        break;
                    }
                }
                return;
            }
        };

        this.thread_is_runnable = true;

        return;
    }

    private void repaintTextArea() {
        this.revalidate();
        this.repaint();
        return;
    }

    public final void addStdLogInfoNoNewLine(final String add_log_info) {
        this.std_log_textarea.append(add_log_info);
        this.std_log_textarea.setCaretPosition(
                this.std_log_textarea.getText().length());
        return;
    }

    public final void addStdLogInfoAndNewLine(final String add_log_info) {
        this.addStdLogInfoNoNewLine("\n" + add_log_info);
        return;
    }

    public final void addPgxcCtlLogInfoNoNewLine(final String add_log_info) {
        this.pgxc_ctl_log_textarea.append(add_log_info);
        this.pgxc_ctl_log_textarea.setCaretPosition(
                this.pgxc_ctl_log_textarea.getText().length());
        return;
    }

    public void startStdLogThread() {
        this.std_log_thread.start();
        return;
    }

    public final void startPgxcCtlLogThread(
            final String installation_directory) {
        this.pgxc_ctl_log_file_name = installation_directory
                + "/pgxc_ctl_log/*";
        this.pgxc_ctl_log_thread.start();
        return;
    }

    public final void killThread() {
        this.thread_is_runnable = false;
        return;
    }

    public final void installFinish() {
        this.next_button.setEnabled(true);
        this.repaint();
        return;
    }
}
