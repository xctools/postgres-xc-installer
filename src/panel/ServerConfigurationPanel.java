package panel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import common.CreateObject;
import common.Define;
import common.ExecShellCommand;
import common.JButtonParameter;
import common.JLabelParameter;
import common.JTextFieldParameter;
import common.LogPrintFile;
import common.UseJFileChooser;
import common.Validation;

import dialog.ErrorDialog;
import dialog.WarningDialog;

import main.MainFrame;


/*
 * ServerConfiguration
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class ServerConfigurationPanel extends CommonPanel {
    private static final long serialVersionUID = 1871296317847685756L;

    private final String DEFAULT_IP_ADDRESS = "";
    private final String DEFAULT_ROOT_PASSWORD = "";
    private final String DEFAULT_GTM_PROXY_PORT = "16666";
    private final String DEFAULT_COORDINATOR_PORT = "5432";
    private final String DEFAULT_DATANODE_PORT = "15432";
    private final String DEFAULT_GTM_PROXY_NODE_NAME = "gtm_pxy";
    private final String DEFAULT_COORDINATOR_NODE_NAME = "coord";
    private final String DEFAULT_DATANODE_NODE_NAME = "datanode";
    private final String DEFAULT_GTM_PROXY_DATA_DIRECTORY = "";
    private final String DEFAULT_COORDINATOR_DATA_DIRECTORY = "";
    private final String DEFAULT_DATANODE_DATA_DIRECTORY = "";
    private final String DEFAULT_COORDINATOR_POSTGRESQL_CONF = "";
    private final String DEFAULT_DATANODE_POSTGRESQL_CONF = "";
    private final String DEFAULT_COORDINATOR_POOLER_PORT = "5433";
    private final String INPUT_ERROR_IP_ADDRESS =
            "\"" + Define.IP_ADDRESS + "\"";
    private final String INPUT_ERROR_ROOT_PASSWORD =
            "\"" + Define.ROOT_PASSWORD + "\"";
    private final String INPUT_ERROR_PORT = "\"" + Define.PORT + "\"";
    private final String INPUT_ERROR_NODE_NAME = "\"" + Define.NODE_NAME + "\"";
    private final String INPUT_ERROR_DATA_DIRECTORY =
            "\"" + Define.DATA_DIRECTORY + "\"";

    private final JLabelParameter header2_label_parameter = new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.SERVER_CONFIGURATION_PANEL_NAME,
            new Font(Font.SERIF, Font.PLAIN, 20));
    private final JLabelParameter please_label_parameter = new JLabelParameter(
            50, 50, Define.MAIN_FRAME_WIDTH, 30,
            "Please select Server Configuration.",
            new Font(Font.SERIF, Font.PLAIN, 18));
    private final JLabelParameter ip_address_label_parameter =
            new JLabelParameter(
            60, 90, 150, 30, Define.IP_ADDRESS + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField ip_address_textfield;
    private final JTextFieldParameter ip_address_textfield_parameter =
            new JTextFieldParameter(
            220, 90, 200, 30, this.DEFAULT_IP_ADDRESS);
    private final JLabelParameter root_password_label_parameter =
            new JLabelParameter(
            60, 130, 150, 30, Define.ROOT_PASSWORD + "  :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JPasswordField root_password_passwordfield;
    private final JTextFieldParameter
        root_password_passwordfield_parameter = new JTextFieldParameter(
            220, 130, 200, 30, this.DEFAULT_ROOT_PASSWORD);
    private final JLabelParameter gtm_proxy_label_parameter = new JLabelParameter(
            220, 170, 180, 30, Define.GTM_PROXY, new Font(Font.SERIF, Font.PLAIN, 15));
    private final JLabelParameter coordinator_label_parameter = new JLabelParameter(
            410, 170, 180, 30, Define.COORDINATOR, new Font(Font.SERIF, Font.PLAIN, 15));
    private final JLabelParameter datanode_label_parameter = new JLabelParameter(
            600, 170, 180, 30, Define.DATANODE, new Font(Font.SERIF, Font.PLAIN, 15));
    private final JLabelParameter port_label_parameter = new JLabelParameter(
            60, 210, 150, 30, Define.PORT + " :", new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField gtm_proxy_port_textfield;
    private final JTextFieldParameter gtm_proxy_port_textfield_parameter = new JTextFieldParameter(
            220, 210, 180, 30, this.DEFAULT_GTM_PROXY_PORT);
    private JTextField coordinator_port_textfield;
    private final JTextFieldParameter coordinator_port_textfield_parameter = new JTextFieldParameter(
            410, 210, 180, 30, this.DEFAULT_COORDINATOR_PORT);
    private JTextField datanode_port_textfield;
    private JTextFieldParameter datanode_port_textfield_parameter =
            new JTextFieldParameter(
            600, 210, 180, 30, this.DEFAULT_DATANODE_PORT);
    private final JLabelParameter node_name_label_parameter =
            new JLabelParameter(
            60, 250, 150, 30, Define.NODE_NAME + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField gtm_proxy_node_name_textfield;
    private final JTextFieldParameter gtm_proxy_node_name_textfield_parameter =
            new JTextFieldParameter(
            220, 250, 180, 30, this.DEFAULT_GTM_PROXY_NODE_NAME);
    private JTextField coordinator_node_name_textfield;
    private final JTextFieldParameter
        coordinator_node_name_textfield_parameter =
            new JTextFieldParameter(
            410, 250, 180, 30, this.DEFAULT_COORDINATOR_NODE_NAME);
    private JTextField datanode_node_name_textfield;
    private final JTextFieldParameter datanode_node_name_textfield_parameter =
            new JTextFieldParameter(
            600, 250, 180, 30, this.DEFAULT_DATANODE_NODE_NAME);
    private final JLabelParameter data_directory_label_parameter =
            new JLabelParameter(
            60, 290, 150, 30, Define.DATA_DIRECTORY + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField gtm_proxy_data_directory_textfield;
    private final JTextFieldParameter
        gtm_proxy_data_directory_textfield_parameter =
        new JTextFieldParameter(
            220, 290, 180, 30, this.DEFAULT_GTM_PROXY_DATA_DIRECTORY);
    private JTextField coordinator_data_directory_textfield;
    private final JTextFieldParameter
        coordinator_data_directory_textfield_parameter =
        new JTextFieldParameter(
            410, 290, 180, 30, this.DEFAULT_COORDINATOR_DATA_DIRECTORY);
    private JTextField datanode_data_directory_textfield;
    private final JTextFieldParameter
        datanode_data_directory_textfield_parameter =
        new JTextFieldParameter(
            600, 290, 180, 30, this.DEFAULT_DATANODE_DATA_DIRECTORY);
    private JLabelParameter postgresql_conf_label_parameter =
            new JLabelParameter(
            60, 330, 150, 30, Define.POSTGRESQL_CONF + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField coordinator_postgresql_conf_textfield;
    private JTextFieldParameter
        coordinator_postgresql_conf_textfield_parameter =
        new JTextFieldParameter(
            410, 330, 180, 30, this.DEFAULT_COORDINATOR_POSTGRESQL_CONF);
    private final JButtonParameter
        coordinator_postgresql_conf_button_parameter =
        new JButtonParameter(
            510, 360, 80, 30, "Browse", new Font(Font.SERIF, Font.BOLD, 12),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    coordinatorPostgresqlConfButtonPushed();
                    return;
                }
            });
    private JTextField datanode_postgresql_conf_textfield;
    private JTextFieldParameter
        datanode_postgresql_conf_textfield_parameter =
        new JTextFieldParameter(
            600, 330, 180, 30, this.DEFAULT_DATANODE_POSTGRESQL_CONF);
    private final JButtonParameter
        datanode_postgresql_conf_button_parameter =
        new JButtonParameter(
            700, 360, 80, 30, "Browse", new Font(Font.SERIF, Font.BOLD, 12),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    datanodePostgresqlConfButtonPushed();
                    return;
                }
            });
    private JLabelParameter pooler_port_label_parameter = new JLabelParameter(
            60, 400, 150, 30, Define.POOLER_PORT + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField coordinator_pooler_port_textfield;
    private final JTextFieldParameter
        coordinator_pooler_port_textfield_parameter =
        new JTextFieldParameter(
            410, 400, 180, 30, this.DEFAULT_COORDINATOR_POOLER_PORT);

    private String superuser = "";

    public ServerConfigurationPanel(final MainFrame main_frame_org,
            final int number_of_this_server, final int number_of_servers) {
        super(main_frame_org);

        this.header2_label_parameter.setText(
                this.header2_label_parameter.getText()
                + "(" + (number_of_this_server + 1) + "/"
                        + number_of_servers + ")");
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
                this.root_password_label_parameter));
        this.root_password_passwordfield =
                CreateObject.createJPasswordField(
                        this.root_password_passwordfield_parameter);
        this.main_panel.add(this.root_password_passwordfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.gtm_proxy_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.coordinator_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.datanode_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.port_label_parameter));
        this.gtm_proxy_port_textfield = CreateObject.createJTextField(
                this.gtm_proxy_port_textfield_parameter);
        this.main_panel.add(this.gtm_proxy_port_textfield);
        this.coordinator_port_textfield =
                CreateObject.createJTextField(
                        this.coordinator_port_textfield_parameter);
        this.main_panel.add(this.coordinator_port_textfield);
        this.datanode_port_textfield =
                CreateObject.createJTextField(
                        this.datanode_port_textfield_parameter);
        this.main_panel.add(this.datanode_port_textfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.node_name_label_parameter));
        this.gtm_proxy_node_name_textfield_parameter.setDefault_text(
                this.gtm_proxy_node_name_textfield_parameter.getDefault_text()
                + (number_of_this_server + 1));
        this.gtm_proxy_node_name_textfield =
                CreateObject.createJTextField(
                        this.gtm_proxy_node_name_textfield_parameter);
        this.main_panel.add(this.gtm_proxy_node_name_textfield);
        this.coordinator_node_name_textfield_parameter.setDefault_text(
                this.coordinator_node_name_textfield_parameter.getDefault_text()
                + (number_of_this_server + 1));
        this.coordinator_node_name_textfield =
                CreateObject.createJTextField(
                        this.coordinator_node_name_textfield_parameter);
        this.main_panel.add(this.coordinator_node_name_textfield);
        this.datanode_node_name_textfield_parameter.setDefault_text(
                this.datanode_node_name_textfield_parameter.getDefault_text()
                + (number_of_this_server + 1));
        this.datanode_node_name_textfield =
                CreateObject.createJTextField(
                        this.datanode_node_name_textfield_parameter);
        this.main_panel.add(this.datanode_node_name_textfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.data_directory_label_parameter));
        this.gtm_proxy_data_directory_textfield =
                CreateObject.createJTextField(
                        this.gtm_proxy_data_directory_textfield_parameter);
        this.main_panel.add(this.gtm_proxy_data_directory_textfield);
        this.coordinator_data_directory_textfield =
                CreateObject.createJTextField(
                        this.coordinator_data_directory_textfield_parameter);
        this.main_panel.add(this.coordinator_data_directory_textfield);
        this.datanode_data_directory_textfield =
                CreateObject.createJTextField(
                        this.datanode_data_directory_textfield_parameter);
        this.main_panel.add(this.datanode_data_directory_textfield);
        this.main_panel.add(CreateObject.createJLabel(
                this.postgresql_conf_label_parameter));
        this.coordinator_postgresql_conf_textfield =
                CreateObject.createJTextField(
                        this.coordinator_postgresql_conf_textfield_parameter);
        this.main_panel.add(this.coordinator_postgresql_conf_textfield);
        this.main_panel.add(CreateObject.createJButton(
                this.coordinator_postgresql_conf_button_parameter));
        this.datanode_postgresql_conf_textfield =
                CreateObject.createJTextField(
                        this.datanode_postgresql_conf_textfield_parameter);
        this.main_panel.add(this.datanode_postgresql_conf_textfield);
        this.main_panel.add(CreateObject.createJButton(
                this.datanode_postgresql_conf_button_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.pooler_port_label_parameter));
        this.coordinator_pooler_port_textfield =
                CreateObject.createJTextField(
                        this.coordinator_pooler_port_textfield_parameter);
        this.main_panel.add(this.coordinator_pooler_port_textfield);

        return;
    }

    private void coordinatorPostgresqlConfButtonPushed() {
        this.coordinator_postgresql_conf_textfield.setText(
                UseJFileChooser.getChoseFileName(this));
        return;
    }

    private void datanodePostgresqlConfButtonPushed() {
        this.datanode_postgresql_conf_textfield.setText(
                UseJFileChooser.getChoseFileName(this));
        return;
    }

    public final String getIPAddress() {
        return this.ip_address_textfield.getText().trim();
    }

    public final String getRootPassword() {
        return String.valueOf(this.root_password_passwordfield.getPassword());
    }

    public final String getGtmProxyPort() {
        return this.gtm_proxy_port_textfield.getText().trim();
    }

    public final String getCoordinatorPort() {
        return this.coordinator_port_textfield.getText().trim();
    }

    public final String getDatanodePort() {
        return this.datanode_port_textfield.getText().trim();
    }

    public final String getGtmProxyNodeName() {
        return this.gtm_proxy_node_name_textfield.getText().trim();
    }

    public final String getCoordinatorNodeName() {
        return this.coordinator_node_name_textfield.getText().trim();
    }

    public final String getDatanodeNodeName() {
        return this.datanode_node_name_textfield.getText().trim();
    }

    public final void setGtmProxyDataDirectory(final String data_directory) {
        this.gtm_proxy_data_directory_textfield.setText(data_directory);
        return;
    }

    public final String getGtmProxyDataDirectory() {
        return this.gtm_proxy_data_directory_textfield.getText().trim();
    }

    public final void setCoordinatorDataDirectory(final String data_directory) {
        this.coordinator_data_directory_textfield.setText(data_directory);
        return;
    }

    public final String getCoordinatorDataDirectory() {
        return this.coordinator_data_directory_textfield.getText().trim();
    }

    public final void setDatanodeDataDirectory(final String data_directory) {
        this.datanode_data_directory_textfield.setText(data_directory);
        return;
    }

    public final String getDatanodeDataDirectory() {
        return this.datanode_data_directory_textfield.getText().trim();
    }

    public final String getCoordinatorPostgresqlConf() {
        return this.coordinator_postgresql_conf_textfield.getText().trim();
    }

    public final String getDatanodePostgresqlConf() {
        return this.datanode_postgresql_conf_textfield.getText().trim();
    }

    public final String getCoordinatorPoolerPort() {
        return this.coordinator_pooler_port_textfield.getText().trim();
    }

    public final void setSuperuser(final String superuser_org) {
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
     * rootPasswordIsValid
     * Check you can login Coordinator_and_Datanode_server
     * by using root_password.
     */

    private boolean rootPasswordIsValid() {
        String root_password = this.getRootPassword();
        if (Validation.argIsEmpty(root_password)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_ROOT_PASSWORD + Define.NOINPUT_MESSAGE_2,
                    this.main_frame);
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
     * portIsValid
     * Check port is valid(see Validation.portIsValid).
     */

    private boolean portIsValid(final String port, final String name) {
        if (Validation.argIsEmpty(port)) {
            new WarningDialog("[" + name + "]"
        + Define.NOINPUT_MESSAGE_1 + this.INPUT_ERROR_PORT
        + Define.NOINPUT_MESSAGE_2, this.main_frame);
            return false;
        } else if (!Validation.portIsValid(port)) {
            new WarningDialog("[" + name + "]"
        + Define.PORT_OUT_OF_RANGE_MESSAGE, this.main_frame);
            return false;
        }
        return true;
    }

    /*
     * allPortsAreUnique
     * Check all ports(gtm_proxy_port, coordinator_port,
     * datanode_port, coordinator_pooler_port)
     * are unique.
     */

    private boolean allPortsAreUnique() {
        List<String> port_list = new ArrayList<String>();
        port_list.add(this.getGtmProxyPort());
        port_list.add(this.getCoordinatorPort());
        port_list.add(this.getDatanodePort());
        port_list.add(this.getCoordinatorPoolerPort());

        boolean all_ports_are_unique = true;

        for (int i = 0; i < port_list.size(); i++) {
            for (int j = (i + 1); j < port_list.size(); j++) {
                if (port_list.get(i).equals(port_list.get(j))) {
                    all_ports_are_unique = false;
                }
            }
        }

        if (all_ports_are_unique) {
            return true;
        } else {
            new WarningDialog(Define.PORT_UNIQUE_MESSAGE, this.main_frame);
            return false;
        }
    }

    /*
     * nodeNameIsValid
     * Check node_name is not empty.
     */

    private boolean nodeNameIsValid(final String node_name, final String name) {
        if (Validation.argIsEmpty(node_name)) {
            new WarningDialog("[" + name + "]"
        + Define.NOINPUT_MESSAGE_1 + this.INPUT_ERROR_NODE_NAME
        + Define.NOINPUT_MESSAGE_2, this.main_frame);
            return false;
        }
        return true;
    }

    /*
     * dataDirectoryIsValid
     * Check data_directory is valid(see Validation.directoryPathIsValid).
     */

    private boolean dataDirectoryIsValid(
            final String data_directory, final String name) {
        if (Validation.argIsEmpty(data_directory)) {
            new WarningDialog("[" + name + "]"
        + Define.NOINPUT_MESSAGE_1 + this.INPUT_ERROR_DATA_DIRECTORY
        + Define.NOINPUT_MESSAGE_2, this.main_frame);
            return false;
        } else if (!Validation.directoryPathIsValid(data_directory)) {
            new WarningDialog("[" + name + "]" + Define.DIRECTORY
                    + " \"" + data_directory + "\""
                    + Define.IS_INVALID_MESSAGE, this.main_frame);
            return false;
        }
        return true;
    }

    /*
     * allDirectoriesAreUnique
     * Check all directories(gtm_proxy_data_directory,
     * coordinator_data_directory,
     * datanode_data_directory) are unique.
     */

    private boolean allDirectoriesAreUnique() {
        List<String> directory_list = new ArrayList<String>();
        directory_list.add(this.getGtmProxyDataDirectory());
        directory_list.add(this.getCoordinatorDataDirectory());
        directory_list.add(this.getDatanodeDataDirectory());

        boolean all_directories_are_unique = true;

        for (int i = 0; i < directory_list.size(); i++) {
            for (int j = (i + 1); j < directory_list.size(); j++) {
                if (directory_list.get(i).equals(directory_list.get(j))) {
                    all_directories_are_unique = false;
                }
            }
        }

        if (all_directories_are_unique) {
            return true;
        } else {
            new WarningDialog(Define.DIRECTORY_UNIQUE_MESSAGE, this.main_frame);
            return false;
        }
    }

    /*
     * postgresqlConfIsValid
     * Check postgresql_conf exist.
     */

    private boolean postgresqlConfIsValid(
            final String postgresql_conf, final String name) {
        if (Validation.argIsEmpty(postgresql_conf)) {
            return true;
        } else if (!Validation.postgresqlConfExist(postgresql_conf)) {
            new WarningDialog("[" + name + "]"
        + Define.POSTGRESQL_CONF_NOT_FOUND_MESSAGE, this.main_frame);
            return false;
        }
        return true;
    }

    /*
     * superuserIsValid
     * Check superuser does not exist in Coordinator_and_Datanode_server.
     */

    private boolean superuserIsValid() {
        String ip_address = this.ip_address_textfield.getText();
        String root_password = this.getRootPassword();
        String[] check_other_superuser_command =
            {"sh", Define.CHECK_OTHER_SUPERUSER_SH, ip_address,
                this.superuser, root_password};
        int ret = ExecShellCommand.execShellCommandWithReturn(
                check_other_superuser_command);
        if (ret == 0) {
            return true;
        } else if (ret == 1) {
            new WarningDialog(Define.USER + "\"" + this.superuser
                    + "\"" + Define.ALREADY_EXIST_MESSAGE + " "
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

    public final void nextButtonPushed() {
        if (this.ipAddressIsValid()
                && this.rootPasswordIsValid()
                && this.portIsValid(this.getGtmProxyPort(), Define.GTM_PROXY)
                && this.portIsValid(this.getCoordinatorPort(),
                        Define.COORDINATOR)
                && this.portIsValid(this.getDatanodePort(), Define.DATANODE)
                && this.portIsValid(this.getCoordinatorPoolerPort(),
                        Define.POOLER_PORT)
                && this.allPortsAreUnique()
                && this.nodeNameIsValid(this.getGtmProxyNodeName(),
                        Define.GTM_PROXY)
                && this.nodeNameIsValid(this.getCoordinatorNodeName(),
                        Define.COORDINATOR)
                && this.nodeNameIsValid(this.getDatanodeNodeName(),
                        Define.DATANODE)
                && this.dataDirectoryIsValid(this.getGtmProxyDataDirectory(),
                        Define.GTM_PROXY)
                && this.dataDirectoryIsValid(this.getCoordinatorDataDirectory(),
                        Define.COORDINATOR)
                && this.dataDirectoryIsValid(this.getDatanodeDataDirectory(),
                        Define.DATANODE)
                && this.allDirectoriesAreUnique()
                && this.postgresqlConfIsValid(
                        this.getCoordinatorPostgresqlConf(),
                        Define.COORDINATOR)
                && this.postgresqlConfIsValid(
                        this.getDatanodePostgresqlConf(), Define.DATANODE)
                && this.superuserIsValid()) {
            this.main_frame.nextPanel(this);
        }
        return;
    }
}