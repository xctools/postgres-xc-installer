package panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

import common.CreateObject;
import common.Define;
import common.JLabelParameter;
import common.JTextAreaParameter;
import common.Validation;

import dialog.WarningDialog;

import main.MainFrame;


/*
 * HostAuthenticationPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class HostAuthenticationPanel extends CommonPanel {

    private static final long serialVersionUID = 2376240403093020727L;

    private final String INPUT_ERROR_PG_HBA_ENTRIES =
            "\"" + Define.PG_HBA_ENTRIES + "\"";

    private final JLabelParameter header2_label_parameter = new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.HOST_AUTHENTICATION_PANEL_NAME,
            new Font(Font.SERIF, Font.PLAIN, 20));
    private final JLabelParameter please_label_parameter = new JLabelParameter(
            50, 50, Define.MAIN_FRAME_WIDTH, 30,
            "Please specify pg_hba.conf entries.",
            new Font(Font.SERIF, Font.PLAIN, 18));
    private final JLabelParameter pg_hba_label_parameter = new JLabelParameter(
            60, 90, 150, 30, Define.PG_HBA_ENTRIES + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextArea pg_hba_textarea;
    private final JTextAreaParameter pg_hba_textarea_parameter =
            new JTextAreaParameter(
            220, 90, 300, 200, Color.BLACK, 1);

    public HostAuthenticationPanel(final MainFrame main_frame_org) {
        super(main_frame_org);

        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.please_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.pg_hba_label_parameter));
        this.pg_hba_textarea = CreateObject.createJTextArea(
                this.pg_hba_textarea_parameter);
        this.main_panel.add(this.pg_hba_textarea);

        return;
    }

    public final void setpgHba(final String[] pg_hba_array) {
        String pg_hba = "";
        for (int i = 0; i < pg_hba_array.length; i++) {
            pg_hba += pg_hba_array[i] + "\n";
        }
        this.pg_hba_textarea.setText(pg_hba);
        return;
    }

    /*
     * pgHbaIsValid
     * Check pg_hba is not empty.
     */

    private boolean pgHbaIsValid() {
        String pg_hba = this.pg_hba_textarea.getText();
        if (Validation.argIsEmpty(pg_hba)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_PG_HBA_ENTRIES
                    + Define.NOINPUT_MESSAGE_2,
                    this.main_frame);
            return false;
        }
        return true;
    }

    public final String[] getPgHbaEntries() {
        String pg_hba_entries_string = this.pg_hba_textarea.getText();
        String[] pg_hba_entries_string_array =
                pg_hba_entries_string.split("\n");
        return pg_hba_entries_string_array;
    }

    public final void nextButtonPushed() {
        if (this.pgHbaIsValid()) {
            this.main_frame.nextPanel(this);
        }
        return;
    }
}
