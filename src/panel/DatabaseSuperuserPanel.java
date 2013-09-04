package panel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import common.CreateObject;
import common.Define;
import common.ExecShellCommand;
import common.JLabelParameter;
import common.JTextFieldParameter;
import common.LogPrintFile;
import common.Validation;

import dialog.ErrorDialog;
import dialog.WarningDialog;

import main.MainFrame;


/*
 * DatabaseSuperuserPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class DatabaseSuperuserPanel extends CommonPanel {

    private static final long serialVersionUID = 2221604202090862318L;

    private final String DEFAULT_SUPERUSER = "postgres";
    private final String DEFAULT_PASSWORD = "";
    private final String DEFAULT_RETYPE_PASSWORD = "";
    private final String INPUT_ERROR_SUPERUSER = "\"" + Define.SUPERUSER + "\"";
    private final String INPUT_ERROR_PASSWORD = "\"" + Define.PASSWORD + "\"";

    private final JLabelParameter header2_label_parameter = new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.DATABASE_SUPERUSER_PANEL_NAME,
            new Font(Font.SERIF, Font.PLAIN, 20));
    private final JLabelParameter please_specify_label_parameter =
            new JLabelParameter(
            50, 50, Define.MAIN_FRAME_WIDTH, 30,
            "Please specify the database superuser and password.",
            new Font(Font.SERIF, Font.PLAIN, 18));
    private final JLabelParameter superuser_label_parameter =
            new JLabelParameter(
            60, 90, 150, 30, Define.SUPERUSER + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField superuser_textfield;
    private final JTextFieldParameter superuser_textfield_parameter =
            new JTextFieldParameter(
            220, 90, 200, 30, this.DEFAULT_SUPERUSER);
    private final JLabelParameter password_label_parameter =
            new JLabelParameter(
            60, 130, 150, 30,
            Define.PASSWORD + " :", new Font(Font.SERIF, Font.PLAIN, 15));
    private JPasswordField password_passwordfield;
    private final JTextFieldParameter password_passwordfield_parameter =
            new JTextFieldParameter(
            220, 130, 200, 30, this.DEFAULT_PASSWORD);
    private final JLabelParameter retype_password_label_parameter =
            new JLabelParameter(
            60, 170, 150, 30,
            Define.RETYPE_PASSWORD + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JPasswordField retype_password_passwordfield;
    private final JTextFieldParameter retype_password_passwordfield_parameter =
            new JTextFieldParameter(
            220, 170, 200, 30, this.DEFAULT_RETYPE_PASSWORD);

    public DatabaseSuperuserPanel(final MainFrame main_frame_org) {
        super(main_frame_org);

        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.please_specify_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.superuser_label_parameter));
        this.superuser_textfield = CreateObject.createJTextField(
                this.superuser_textfield_parameter);
        this.main_panel.add(this.superuser_textfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.password_label_parameter));
        this.password_passwordfield =
                CreateObject.createJPasswordField(
                        this.password_passwordfield_parameter);
        this.main_panel.add(this.password_passwordfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.retype_password_label_parameter));
        this.retype_password_passwordfield =
                CreateObject.createJPasswordField(
                        this.retype_password_passwordfield_parameter);
        this.main_panel.add(this.retype_password_passwordfield);

        return;
    }

    public final String getSuperuser() {
        return this.superuser_textfield.getText().trim();
    }

    public final String getPassword() {
        return String.valueOf(this.password_passwordfield.getPassword());
    }

    /*
     * superuserIsValid
     * Check superuser does not exist.
     */

    private boolean superuserIsValid() {
        String superuser = this.getSuperuser();
        if (Validation.argIsEmpty(superuser)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_SUPERUSER + Define.NOINPUT_MESSAGE_2,
                    this.main_frame);
            return false;
        } else {
            String[] check_superuser_command =
                {"sh", Define.CHECK_SUPERUSER_SH, superuser};
            int ret = ExecShellCommand.execShellCommandWithReturn(
                    check_superuser_command);
            if (ret == 0) {
                return true;
            } else if (ret == 1) {
                new WarningDialog(Define.USER + " \"" + superuser
                        + "\"" + Define.ALREADY_EXIST_MESSAGE,
                        this.main_frame);
                return false;
            } else {
                new ErrorDialog(Define.ERROR_MESSAGE_1
                        + LogPrintFile.getLog_file_name()
                        + Define.ERROR_MESSAGE_2, main_frame);
                return false;
            }
        }
    }

    /*
     * passwordIsValid
     * Check password equals retype_password.
     */

    private boolean passwordIsValid() {
        String password = this.getPassword();
        String retype_password = String.valueOf(
                this.retype_password_passwordfield.getPassword());
        if (Validation.argIsEmpty(password)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_PASSWORD + Define.NOINPUT_MESSAGE_2,
                    this.main_frame);
            return false;
        } else if (!password.equals(retype_password)) {
            new WarningDialog(Define.PASSWORD_DO_NOT_MATCH_MESSAGE,
                    this.main_frame);
            return false;
        }
        return true;
    }

    public final void nextButtonPushed() {
        if (this.superuserIsValid() && this.passwordIsValid()) {
            this.main_frame.nextPanel(this);
        }
        return;
    }
}