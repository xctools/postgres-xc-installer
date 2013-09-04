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
 * GTMConfigurationPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class GTMConfigurationPanel extends CommonPanel {
    private static final long serialVersionUID = -5847841218497989701L;

    private final String DEFAULT_IP_ADDRESS = "";
    private final String DEFAULT_PORT = "6666";
    private final String DEFAULT_NODE_NAME = "gtm";
    private final String DEFAULT_DATA_DIRECTORY = "";
    private final String DEFAULT_ROOT_PASSWORD = "";
    private final String INPUT_ERROR_IP_ADDRESS =
            "\"" + Define.IP_ADDRESS + "\"";
    private final String INPUT_ERROR_PORT = "\"" + Define.PORT + "\"";
    private final String INPUT_ERROR_NODE_NAME = "\"" + Define.NODE_NAME + "\"";
    private final String INPUT_ERROR_DATA_DIRECTORY =
            "\"" + Define.DATA_DIRECTORY + "\"";
    private final String INPUT_ERROR_ROOT_PASSWORD =
            "\"" + Define.ROOT_PASSWORD + "\"";

    private final JLabelParameter header2_label_parameter = new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.GTM_CONFIGURATION_PANEL_NAME,
            new Font(Font.SERIF, Font.PLAIN, 20));
    private final JLabelParameter please_label_parameter = new JLabelParameter(
            50, 50, Define.MAIN_FRAME_WIDTH, 30,
            "Please select a GTM Configuration.",
            new Font(Font.SERIF, Font.PLAIN, 18));
    private final JLabelParameter ip_address_label_parameter =
            new JLabelParameter(
            60, 90, 150, 30, Define.IP_ADDRESS + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField ip_address_textfield;
    private final JTextFieldParameter ip_address_textfield_parameter =
            new JTextFieldParameter(
            220, 90, 200, 30, this.DEFAULT_IP_ADDRESS);
    private JTextField port_textfield;
    private final JLabelParameter port_label_parameter = new JLabelParameter(
            60, 130, 150, 30, Define.PORT + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private final JTextFieldParameter	port_textfield_parameter =
            new JTextFieldParameter(
            220, 130, 200, 30, this.DEFAULT_PORT);
    private final JLabelParameter node_name_label_parameter =
            new JLabelParameter(
            60, 170, 150, 30, Define.NODE_NAME + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField node_name_textfield;
    private final JTextFieldParameter node_name_textfield_parameter =
            new JTextFieldParameter(
            220, 170, 200, 30, this.DEFAULT_NODE_NAME);
    private final JLabelParameter data_directory_label_parameter =
            new JLabelParameter(
            60, 210, 150, 30, Define.DATA_DIRECTORY + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField data_directory_textfield;
    private final JTextFieldParameter data_directory_textfield_parameter =
            new JTextFieldParameter(
            220, 210, 200, 30, this.DEFAULT_DATA_DIRECTORY);
    private final JLabelParameter root_password_label_parameter =
            new JLabelParameter(
            60, 250, 150, 30, Define.ROOT_PASSWORD + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JPasswordField root_password_passwordfield;
    private final JTextFieldParameter root_password_passwordfield_parameter =
            new JTextFieldParameter(
            220, 250, 200, 30, this.DEFAULT_ROOT_PASSWORD);

    private String superuser = "";

    public GTMConfigurationPanel(final MainFrame main_frame_org) {
        super(main_frame_org);

        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.please_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.ip_address_label_parameter));
        this.ip_address_textfield = CreateObject.createJTextField(
                this.ip_address_textfield_parameter);
        this.main_panel.add(this.ip_address_textfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.port_label_parameter));
        this.port_textfield = CreateObject.createJTextField(
                this.port_textfield_parameter);
        this.main_panel.add(this.port_textfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.node_name_label_parameter));
        this.node_name_textfield = CreateObject.createJTextField(
                this.node_name_textfield_parameter);
        this.main_panel.add(this.node_name_textfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.data_directory_label_parameter));
        this.data_directory_textfield = CreateObject.createJTextField(
                this.data_directory_textfield_parameter);
        this.main_panel.add(this.data_directory_textfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.root_password_label_parameter));
        this.root_password_passwordfield =
                CreateObject.createJPasswordField(
                        this.root_password_passwordfield_parameter);
        this.main_panel.add(this.root_password_passwordfield);

        return;
    }

    public final String getIPAddress() {
        return this.ip_address_textfield.getText().trim();
    }

    public final String getPort() {
        return this.port_textfield.getText().trim();
    }

    public final String getNodeName() {
        return this.node_name_textfield.getText().trim();
    }

    public final void setDataDirectory(String data_directory) {
        this.data_directory_textfield.setText(data_directory);
        return;
    }

    public final String getDataDirectory() {
        return this.data_directory_textfield.getText().trim();
    }

    public final String getRootPassword() {
        return String.valueOf(this.root_password_passwordfield.getPassword());
    }

    public final void setSuperuser(String superuser_org) {
        this.superuser = superuser_org;
        return;
    }

    /*
     * ipAddressIsValid
     * Check ping to ip_address succeeds
     */

    private boolean ipAddressIsValid() {
        String ip_address = this.getIPAddress();
        if (Validation.argIsEmpty(ip_address)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_IP_ADDRESS + Define.NOINPUT_MESSAGE_2,
                    this.main_frame);
            return false;
        } else if (!Validation.succeedsPing(ip_address)) {
            new WarningDialog(Define.IP_ADDRESS + " \""
            + ip_address + "\"" + Define.IS_INVALID_MESSAGE,
                        this.main_frame);
            return false;
        }
        return true;
    }

    /*
     * portIsValid
     * Check port is valid(see Validation.portIsValid).
     */

    private boolean portIsValid() {
        String port = this.getPort();
        if (Validation.argIsEmpty(port)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_PORT + Define.NOINPUT_MESSAGE_2,
                    this.main_frame);
            return false;
        } else if (!Validation.portIsValid(port)) {
            new WarningDialog(Define.PORT_OUT_OF_RANGE_MESSAGE,
                    this.main_frame);
            return false;
        }
        return true;
    }

    /*
     * nodeNameIsValid
     * Check node_name is not empty.
     */

    private boolean nodeNameIsValid() {
        String node_name = this.getNodeName();
        if (Validation.argIsEmpty(node_name)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_NODE_NAME
                    + Define.NOINPUT_MESSAGE_2, this.main_frame);
            return false;
        }
        return true;
    }

    /*
     * rootPasswordIsValid
     * Check you can login GTM_server by using root_password.
     */

    private boolean rootPasswordIsValid() {
        String root_password = this.getRootPassword();
        if (Validation.argIsEmpty(root_password)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_ROOT_PASSWORD
                    + Define.NOINPUT_MESSAGE_2, this.main_frame);
            return false;
        } else {
            String ip_address = this.ip_address_textfield.getText();
            String[] check_root_password_command =
                {"sh", Define.CHECK_ROOT_PASSWORD_SH, ip_address,
                    root_password};
            int ret = ExecShellCommand.execShellCommandWithReturn(
                    check_root_password_command);
            if (ret == 0) {
                return true;
            } else if (ret == 1) {
                new WarningDialog(this.INPUT_ERROR_ROOT_PASSWORD
                        + Define.IS_INVALID_MESSAGE, this.main_frame);
                return false;
            } else {
                new ErrorDialog(Define.ERROR_MESSAGE_1
                        + LogPrintFile.getLog_file_name()
                        + Define.ERROR_MESSAGE_2, this.main_frame);
                return false;
            }
        }
    }

    /*
     * superuserIsValid
     * Check superuser does not exist in GTM_server.
     */

    private boolean superuserIsValid() {
        String ip_address = this.ip_address_textfield.getText();
        String root_password = this.getRootPassword();
        String[] check_other_superuser_command =
            {"sh", Define.CHECK_OTHER_SUPERUSER_SH, ip_address, this.superuser, root_password};
        int ret = ExecShellCommand.execShellCommandWithReturn(
                check_other_superuser_command);
        if(ret == 0) {
            return true;
        } else if (ret == 1) {
            new WarningDialog(Define.USER + "\""
        + this.superuser + "\"" + Define.ALREADY_EXIST_MESSAGE + " "
                    + Define.AT + " \"" + ip_address + "\"",
                    this.main_frame);
            return false;
        } else {
            new ErrorDialog(Define.ERROR_MESSAGE_1
                    + LogPrintFile.getLog_file_name()
                    + Define.ERROR_MESSAGE_2, this.main_frame);
            return false;
        }
    }

    /*
     * dataDirectoryIsValid
     * Check data_directory is valid(see Validation.directoryPathIsValid).
     */

    private boolean dataDirectoryIsValid() {
        String data_directory = this.getDataDirectory();
        if (Validation.argIsEmpty(data_directory)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_DATA_DIRECTORY
                    + Define.NOINPUT_MESSAGE_2, this.main_frame);
            return false;
        } else if (!Validation.directoryPathIsValid(data_directory)) {
            new WarningDialog(Define.DIRECTORY + " \""
        + data_directory + "\"" + Define.IS_INVALID_MESSAGE, this.main_frame);
            return false;
        }
        return true;
    }

    public final void nextButtonPushed() {
        if (this.ipAddressIsValid() && this.portIsValid()
                && this.nodeNameIsValid() && this.dataDirectoryIsValid()
                && this.rootPasswordIsValid() && this.superuserIsValid()) {
            this.main_frame.nextPanel(this);
        }
        return;
    }
}
