package panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import common.CreateObject;
import common.Define;
import common.JButtonParameter;
import common.JLabelParameter;
import common.JTextFieldParameter;
import common.UseJFileChooser;
import common.Validation;

import dialog.WarningDialog;


import main.MainFrame;

/*
 * InstallationDirectoryPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class InstallationDirectoryPanel extends CommonPanel {
    private static final long serialVersionUID = 1440444157021410311L;

    private final String DEFAULT_INSTALLATION_DIRECTORY = "/usr/local/pgsql";
    private final String INPUT_ERROR_INSTALLATION_DIRECTORY =
            "\"" + Define.INSTALLATION_DIRECTORY + "\"";

    private final JLabelParameter header2_label_parameter = new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.INSTALLATION_DIRECTORY_PANEL_NAME,
            new Font(Font.SERIF, Font.PLAIN, 20));
    private final JLabelParameter please_label_parameter = new JLabelParameter(
            50, 50, Define.MAIN_FRAME_WIDTH, 30,
            "Please specify the directory where Postgres-XC will installed.",
            new Font(Font.SERIF, Font.PLAIN, 18));
    private final JLabelParameter installation_directory_label_parameter =
            new JLabelParameter(
            60, 90, 170, 30, Define.INSTALLATION_DIRECTORY + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField installation_directory_textfield;
    private final JTextFieldParameter
        installation_directory_textfield_parameter =
            new JTextFieldParameter(
            240, 90, 350, 30, this.DEFAULT_INSTALLATION_DIRECTORY);
    private final JButtonParameter
        installation_directory_button_parameter =
        new JButtonParameter(
            600, 90, 80, 30, "Browse", new Font(Font.SERIF, Font.BOLD, 12),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    installationDirectoryButtonPushed();
                    return;
                }
            });

    public InstallationDirectoryPanel(final MainFrame main_frame_org) {
        super(main_frame_org);

        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.please_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.installation_directory_label_parameter));
        this.installation_directory_textfield =
                CreateObject.createJTextField(
                        this.installation_directory_textfield_parameter);
        this.main_panel.add(this.installation_directory_textfield);
        this.main_panel.add(CreateObject.createJButton(
                this.installation_directory_button_parameter));

        return;
    }

    private void installationDirectoryButtonPushed() {
        this.installation_directory_textfield.setText(
                UseJFileChooser.getChoseDirectoryName(this));
        return;
    }

    public final String getInstallationDirectory() {
        return this.installation_directory_textfield.getText().trim();
    }

    /*
     * installationDirectoryIsValid
     * Check installation_directory is valid
     * (see Validation.directoryPathIsValid)
     * and installation_directory does not exist.
     */

    private boolean installationDirectoryIsValid() {
        String installation_directory = this.getInstallationDirectory();
        if (Validation.argIsEmpty(installation_directory)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_INSTALLATION_DIRECTORY
                    + Define.NOINPUT_MESSAGE_2,
                    this.main_frame);
            return false;
        } else if (!Validation.directoryPathIsValid(installation_directory)) {
            new WarningDialog(Define.DIRECTORY + " \""
        + installation_directory + "\"" + Define.IS_INVALID_MESSAGE,
                    this.main_frame);
            return false;
        } else if (Validation.directoryExist(installation_directory)) {
            new WarningDialog(Define.DIRECTORY + " \""
        + installation_directory + "\"" + Define.ALREADY_EXIST_MESSAGE,
                    this.main_frame);
            return false;
        }
        return true;
    }

    public final void nextButtonPushed() {
        if (this.installationDirectoryIsValid()) {
            this.main_frame.nextPanel(this);
        }
        return;
    }
}
