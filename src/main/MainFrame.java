package main;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.Define;
import common.ExecShellCommand;
import common.LogPrintFile;

import panel.CheckAllInputPanel;
import panel.CompletingThePostgresXCSetupWizardPanel;
import panel.DatabaseSuperuserPanel;
import panel.GTMConfigurationPanel;
import panel.HostAuthenticationPanel;
import panel.InstallationDirectoryPanel;
import panel.InstallingPanel;
import panel.NumberOfServersPanel;
import panel.ReadyToInstallPanel;
import panel.ServerConfigurationPanel;
import panel.TopPagePanel;

import dialog.QuestionDialog;

/*
 * MainFrame
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Create and show each panel.
 */

public class MainFrame extends JFrame implements Runnable {

    private static final long serialVersionUID = -1451619696341502514L;

    private QuestionDialog question_dialog;

    private TopPagePanel top_page_panel;
    private InstallationDirectoryPanel installation_directory_panel;
    private DatabaseSuperuserPanel database_superuser_panel;
    private GTMConfigurationPanel gtm_configuration_panel;
    private NumberOfServersPanel number_of_servers_panel;
    private int number_of_servers;
    private ServerConfigurationPanel[] server_configuration_panel;
    private HostAuthenticationPanel host_authentication_panel;
    private CheckAllInputPanel check_all_input_panel;
    private ReadyToInstallPanel ready_to_install_panel;
    private InstallingPanel installing_panel;
    private CompletingThePostgresXCSetupWizardPanel
        completing_the_postgres_xc_setup_wizard_panel;

    private String create_config_file_directory;
    private Thread install_thread;

    public MainFrame() {

        this.setLayout(null);
        this.setIconImage(new ImageIcon(Define.ICON_PING).getImage());
        this.setTitle("Postgres-XC-Installer");
        this.setBounds(Define.MAIN_FRAME_X, Define.MAIN_FRAME_Y,
                Define.MAIN_FRAME_WIDTH, Define.MAIN_FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.question_dialog = new QuestionDialog(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                question_dialog.setVisible(true);
                return;
            }
        });

        this.top_page_panel = new TopPagePanel(this);
        this.installation_directory_panel =
                new InstallationDirectoryPanel(this);
        this.database_superuser_panel = new DatabaseSuperuserPanel(this);
        this.gtm_configuration_panel = new GTMConfigurationPanel(this);
        this.number_of_servers_panel = new NumberOfServersPanel(this);
        this.host_authentication_panel = new HostAuthenticationPanel(this);
        this.ready_to_install_panel = new ReadyToInstallPanel(this);
        this.installing_panel = new InstallingPanel(this);
        this.completing_the_postgres_xc_setup_wizard_panel =
                new CompletingThePostgresXCSetupWizardPanel(this);

        ExecShellCommand.setMainFrame(this);

        this.setPanel(this.top_page_panel);
        this.setVisible(true);

        return;
    }

    /*
     * backPanel
     * To process back_button-push, change panel from now_panel.
     */

    public final void backPanel(final JPanel now_panel) {
        if (now_panel.equals(this.installation_directory_panel)) {
            this.setPanel(this.top_page_panel);
            return;
        } else if (now_panel.equals(this.database_superuser_panel)) {
            this.setPanel(this.installation_directory_panel);
            return;
        } else if (now_panel.equals(this.gtm_configuration_panel)) {
            this.setPanel(this.database_superuser_panel);
            return;
        } else if (now_panel.equals(this.number_of_servers_panel)) {
            this.setPanel(this.gtm_configuration_panel);
            return;
        } else if (now_panel.equals(this.server_configuration_panel[0])) {
            this.setPanel(this.number_of_servers_panel);
            return;
        } else {
            for (int i = 1; i < this.number_of_servers; i++) {
                if (now_panel.equals(this.server_configuration_panel[i])) {
                    this.setPanel(this.server_configuration_panel[i - 1]);
                    return;
                }
            }
            if (now_panel.equals(this.host_authentication_panel)) {
                this.setPanel(
                        this.server_configuration_panel[this.number_of_servers - 1]);
                return;
           } else if (now_panel.equals(this.check_all_input_panel)) {
                this.setPanel(this.host_authentication_panel);
                return;
            } else if (now_panel.equals(this.ready_to_install_panel)) {
                this.setPanel(this.check_all_input_panel);
                return;
            }
        }
    }

    /*
     * nextPanel
     * To process next_button-push, change panel from now_panel.
     */

    public final void nextPanel(final JPanel now_panel) {
        if (now_panel.equals(this.top_page_panel)) {
            this.setPanel(this.installation_directory_panel);
            return;
        } else if (now_panel.equals(this.installation_directory_panel)) {
            this.setPanel(this.database_superuser_panel);
            return;
        } else if (now_panel.equals(this.database_superuser_panel)) {
            /*
             * Give superuser value.
             */
            this.gtm_configuration_panel.setDataDirectory("/home/"
        + this.database_superuser_panel.getSuperuser() + "/pgxc/gtm");
            this.gtm_configuration_panel.setSuperuser(
                    this.database_superuser_panel.getSuperuser());
            this.setPanel(this.gtm_configuration_panel);
            return;
        } else if (now_panel.equals(this.gtm_configuration_panel)) {
            this.setPanel(this.number_of_servers_panel);
            return;
        } else if (now_panel.equals(this.number_of_servers_panel)) {
            /*
             * Give superuser value.
             */
            this.number_of_servers =
                    Integer.parseInt(
                            this.number_of_servers_panel.getNumberOfServers());
            String superuser = this.database_superuser_panel.getSuperuser();
            this.server_configuration_panel =
                    new ServerConfigurationPanel[this.number_of_servers];
            for (int i = 0; i < this.number_of_servers; i++) {
                this.server_configuration_panel[i] =
                        new ServerConfigurationPanel(this, i,
                                this.number_of_servers);
                this.server_configuration_panel[i].setGtmProxyDataDirectory(
                        "/home/" + superuser + "/pgxc/gtm_proxy");
                this.server_configuration_panel[i].setCoordinatorDataDirectory(
                        "/home/" + superuser + "/pgxc/coord");
                this.server_configuration_panel[i].setDatanodeDataDirectory(
                        "/home/" + superuser + "/pgxc/datanode");
                this.server_configuration_panel[i].setSuperuser(
                        this.database_superuser_panel.getSuperuser());
            }
            this.setPanel(this.server_configuration_panel[0]);
            return;
        } else {
            for (int i = 0; i < (this.number_of_servers - 1); i++) {
                if (now_panel.equals(this.server_configuration_panel[i])) {
                    /*
                     * Move "server_configuration_panel_i" to "server_configuration_panel_i+1".
                     */
                    this.setPanel(this.server_configuration_panel[i + 1]);
                    return;
                }
            }
            if (now_panel.equals(
                    this.server_configuration_panel[this.number_of_servers - 1])) {

                /*
                 * Move "Last server_configuration_panel" to "host_authentication_panel".
                 */
                this.setPanel(this.host_authentication_panel);
                return;
            } else if (now_panel.equals(this.host_authentication_panel)) {
                /*
                 * Move "host_authentication_panel" to "check_all_input_panel".
                 * Give all value.
                 */
                String[] server_ip_address = new String[this.number_of_servers];
                String[] gtm_proxy_port = new String[this.number_of_servers];
                String[] gtm_proxy_node_name =
                        new String[this.number_of_servers];
                String[] gtm_proxy_data_directory =
                        new String[this.number_of_servers];
                String[] coordinator_port = new String[this.number_of_servers];
                String[] coordinator_node_name =
                        new String[this.number_of_servers];
                String[] coordinator_data_directory =
                        new String[this.number_of_servers];
                String[] coordinator_postgresql_conf =
                        new String[this.number_of_servers];
                String[] coordinator_pooler_port =
                        new String[this.number_of_servers];
                String[] datanode_port = new String[this.number_of_servers];
                String[] datanode_node_name =
                        new String[this.number_of_servers];
                String[] datanode_data_directory =
                        new String[this.number_of_servers];
                String[] datanode_postgresql_conf =
                        new String[this.number_of_servers];
                String[] server_root_password =
                        new String[this.number_of_servers];
                for (int i = 0; i < this.number_of_servers; i++) {
                    server_ip_address[i] =
                            this.server_configuration_panel[i].getIPAddress();
                    gtm_proxy_port[i] =
                            this.server_configuration_panel[i].getGtmProxyPort();
                    gtm_proxy_node_name[i] =
                            this.server_configuration_panel[i].getGtmProxyNodeName();
                    gtm_proxy_data_directory[i] =
                            this.server_configuration_panel[i].getGtmProxyDataDirectory();
                    coordinator_port[i] =
                            this.server_configuration_panel[i].getCoordinatorPort();
                    coordinator_node_name[i] =
                            this.server_configuration_panel[i].getCoordinatorNodeName();
                    coordinator_data_directory[i] =
                            this.server_configuration_panel[i].getCoordinatorDataDirectory();
                    coordinator_postgresql_conf[i] =
                            this.server_configuration_panel[i].getCoordinatorPostgresqlConf();
                    coordinator_pooler_port[i] =
                            this.server_configuration_panel[i].getCoordinatorPoolerPort();
                    datanode_port[i] =
                            this.server_configuration_panel[i].getDatanodePort();
                    datanode_node_name[i] =
                            this.server_configuration_panel[i].getDatanodeNodeName();
                    datanode_data_directory[i] =
                            this.server_configuration_panel[i].getDatanodeDataDirectory();
                    datanode_postgresql_conf[i] =
                            this.server_configuration_panel[i].getDatanodePostgresqlConf();
                    server_root_password[i] =
                            this.server_configuration_panel[i].getRootPassword();
                }
                this.check_all_input_panel = new CheckAllInputPanel(this,
                        this.installation_directory_panel.getInstallationDirectory(),
                        this.database_superuser_panel.getSuperuser(),
                        this.gtm_configuration_panel.getIPAddress(),
                        this.gtm_configuration_panel.getPort(),
                        this.gtm_configuration_panel.getNodeName(),
                        this.gtm_configuration_panel.getDataDirectory(),
                        this.gtm_configuration_panel.getRootPassword(),
                        server_ip_address,
                        gtm_proxy_port,
                        gtm_proxy_node_name,
                        gtm_proxy_data_directory,
                        coordinator_port,
                        coordinator_node_name,
                        coordinator_data_directory,
                        coordinator_postgresql_conf,
                        coordinator_pooler_port,
                        datanode_port,
                        datanode_node_name,
                        datanode_data_directory,
                        datanode_postgresql_conf,
                        server_root_password,
                        this.host_authentication_panel.getPgHbaEntries());
                this.setPanel(this.check_all_input_panel);
                return;
            } else if (now_panel.equals(this.check_all_input_panel)) {
                this.setPanel(this.ready_to_install_panel);
                return;
            } else if (now_panel.equals(this.ready_to_install_panel)) {
                /*
                 * Start install.
                 */
                this.setPanel(this.installing_panel);
                this.installing_panel.startStdLogThread();
                this.install_thread = new Thread(this);
                this.install_thread.start();
                return;
            } else if (now_panel.equals(this.installing_panel)) {
                /*
                 * Give coordinator's ipaddress and port.
                 */
                this.completing_the_postgres_xc_setup_wizard_panel.setCoordinatorIPAddressAndPort(
                        this.server_configuration_panel[0].getIPAddress(),
                        this.server_configuration_panel[0].getCoordinatorPort());
                this.setPanel(
                        this.completing_the_postgres_xc_setup_wizard_panel);
                return;
            } else if (now_panel.equals(
                    this.completing_the_postgres_xc_setup_wizard_panel)) {
                System.exit(0);
                return;
            }
        }
    }

    /*
     * run
     * Install Postgres-XC
     */

    public final void run() {
        /*Install start*/
        String superuser = this.database_superuser_panel.getSuperuser();
        String password = this.database_superuser_panel.getPassword();
        String installation_directory =
                this.installation_directory_panel.getInstallationDirectory();

        /*Create new user in owner PC*/
        String[] create_superuser_command =
            {"sh", Define.CREATE_SUPERUSER_SH, superuser, password};
        ExecShellCommand.execShellCommandWithPrintLogToPanel(
                create_superuser_command, this.installing_panel);

        /*Create new user in other Servers*/
        String gtm_ip_address = this.gtm_configuration_panel.getIPAddress();
        String gtm_root_password =
                this.gtm_configuration_panel.getRootPassword();
        String[] create_user_in_gtm_command =
            {"sh", Define.CREATE_OTHER_SUPERUSER_SH,
                gtm_ip_address, superuser, gtm_root_password, password};
        ExecShellCommand.execShellCommandWithoutWrite(
                 create_user_in_gtm_command);
        for (int i = 0; i < this.number_of_servers; i++) {
            String ip_address =
                    this.server_configuration_panel[i].getIPAddress();
            if (ip_address.equals(gtm_ip_address)) {
                continue;
            }
            String root_password =
                    this.server_configuration_panel[i].getRootPassword();
            String[] create_other_user_command =
                {"sh", Define.CREATE_OTHER_SUPERUSER_SH,
                    ip_address, superuser, root_password, password};
            ExecShellCommand.execShellCommandWithoutWrite(
                    create_other_user_command);
        }

        /*Install Postgres-XC and pgxc_monitor in owner PC*/
        String[] install_postgres_xc_command =
            {"sh", Define.INSTALL_POSTGRES_XC_AND_PGXC_MONITOR_SH,
                installation_directory};
        ExecShellCommand.execShellCommandWithPrintLogToPanel(
                install_postgres_xc_command, this.installing_panel);

        /*Add pgxcInstallDir and verbose=y
         * and fix pgxc_ctl_log file name and create_template*/
        this.create_config_file_directory = installation_directory;
        String[] create_config_file_template_command =
            {"sh", Define.CREATE_CONFIG_FILE_TEMPLATE_SH,
                this.create_config_file_directory,
                LogPrintFile.getLog_time()};
        ExecShellCommand.execShellCommandWithPrintLogToPanel(
                create_config_file_template_command, this.installing_panel);

        /*pgxc_ctl.log print start*/
        this.installing_panel.startPgxcCtlLogThread(
                this.create_config_file_directory);

        /*Add pgxcOwner and pgxcUser*/
        this.addParameter("pgxcOwner", superuser);
        this.addParameter("pgxcUser", superuser);

        /*Add gtm_parameter*/
        this.addParameter("gtmName",
                this.gtm_configuration_panel.getNodeName());
        this.addParameter("gtmMasterServer",
                this.gtm_configuration_panel.getIPAddress());
        this.addParameter("gtmMasterPort",
                this.gtm_configuration_panel.getPort());
        this.addParameter("gtmMasterDir",
                this.gtm_configuration_panel.getDataDirectory());
        this.addParameter("gtmSlave", "n");
        this.addParameter("gtmProxyDir",
                this.server_configuration_panel[0].getGtmProxyDataDirectory());
        this.addParameter("gtmProxy", "y");

        /*Add gtm_proxy_parameter*/
        String gtm_proxy_names = "(";
        String gtm_proxy_servers = "(";
        String gtm_proxy_ports = "(";
        String gtm_proxy_dirs = "(";
        for (int i = 0; i < this.number_of_servers; i++) {
            gtm_proxy_names += " "
        + this.server_configuration_panel[i].getGtmProxyNodeName();
            gtm_proxy_servers += " "
        + this.server_configuration_panel[i].getIPAddress();
            gtm_proxy_ports += " "
        + this.server_configuration_panel[i].getGtmProxyPort();
            gtm_proxy_dirs += " "
        + this.server_configuration_panel[i].getGtmProxyDataDirectory();
        }
        gtm_proxy_names += " )";
        gtm_proxy_servers += " )";
        gtm_proxy_ports += " )";
        gtm_proxy_dirs += " )";
        this.addParameter("gtmProxyNames", gtm_proxy_names);
        this.addParameter("gtmProxyServers", gtm_proxy_servers);
        this.addParameter("gtmProxyPorts", gtm_proxy_ports);
        this.addParameter("gtmProxyDirs", gtm_proxy_dirs);

        /*Add coordinator_parameter*/
        this.addParameter("coordMasterDir",
                this.server_configuration_panel[0].getCoordinatorDataDirectory());
        String coord_names = "(";
        String coord_ports = "(";
        String pooler_ports = "(";
        String coord_master_servers = "(";
        String coord_master_dirs = "(";

        for (int i = 0; i < this.number_of_servers; i++) {
            coord_names += " "
        + this.server_configuration_panel[i].getCoordinatorNodeName();
            coord_ports += " "
        + this.server_configuration_panel[i].getCoordinatorPort();
            pooler_ports += " "
        + this.server_configuration_panel[i].getCoordinatorPoolerPort();
            coord_master_servers += " "
        + this.server_configuration_panel[i].getIPAddress();
            coord_master_dirs += " "
        + this.server_configuration_panel[i].getCoordinatorDataDirectory();
        }
        coord_names += " )";
        coord_ports += " )";
        pooler_ports += " )";
        coord_master_servers += " )";
        coord_master_dirs += " )";
        this.addParameter("coordNames", coord_names);
        this.addParameter("coordPorts", coord_ports);
        this.addParameter("poolerPorts", pooler_ports);
        this.addParameter("coordMasterServers", coord_master_servers);
        this.addParameter("coordMasterDirs", coord_master_dirs);
        this.addParameter("coordSlave", "n");
        String coord_pg_hba_entries = "(";
        String[] pg_hba_entries =
                this.host_authentication_panel.getPgHbaEntries();
        for (int i = 0; i < pg_hba_entries.length; i++) {
            coord_pg_hba_entries += " " + pg_hba_entries[i];
        }
        coord_pg_hba_entries += " )";
        this.addParameter("coordPgHbaEntries", coord_pg_hba_entries);

        /*Add datanode_parameter*/
        this.addParameter("datanodeMasterDir",
                this.server_configuration_panel[0].getDatanodeDataDirectory());
        String datanode_names = "(";
        String datanode_ports = "(";
        String datanode_master_servers = "(";
        String datanode_master_dirs = "(";
        for (int i = 0; i < this.number_of_servers; i++) {
            datanode_names += " "
        + this.server_configuration_panel[i].getDatanodeNodeName();
            datanode_ports += " "
        + this.server_configuration_panel[i].getDatanodePort();
            datanode_master_servers += " "
        + this.server_configuration_panel[i].getIPAddress();
            datanode_master_dirs += " "
        + this.server_configuration_panel[i].getDatanodeDataDirectory();
        }
        datanode_names += " )";
        datanode_ports += " )";
        datanode_master_servers += " )";
        datanode_master_dirs += " )";
        this.addParameter("datanodeNames", datanode_names);
        this.addParameter("datanodePorts", datanode_ports);
        this.addParameter("datanodeMasterServers", datanode_master_servers);
        this.addParameter("datanodeMasterDirs", datanode_master_dirs);
        this.addParameter("datanodeSlave", "n");
        String datanode_pg_hba_entries = "(";
        for (int i = 0; i < pg_hba_entries.length; i++) {
            datanode_pg_hba_entries += " " + pg_hba_entries[i];
        }
        datanode_pg_hba_entries += " )";
        this.addParameter("datanodePgHbaEntries", datanode_pg_hba_entries);

        /*chown postgres-xc to superuser*/
        String[] chmod_postgres_xc_command = {"sh", Define.CHOWN_POSTGRES_XC_SH,
                superuser, installation_directory};
        ExecShellCommand.execShellCommandWithPrintLogToPanel(
                chmod_postgres_xc_command, this.installing_panel);

        /*Create installtion directory in other Servers*/
        String[] create_installation_directory_in_gtm_command =
            {"sh", Define.CREATE_OTHER_INSTALLATION_DIRECTORY,
                gtm_ip_address, installation_directory,
                gtm_root_password, superuser};
        ExecShellCommand.execShellCommandWithPrintLogToPanel(
                create_installation_directory_in_gtm_command,
                this.installing_panel);
        for (int i = 0; i < this.number_of_servers; i++) {
            String ip_address =
                    this.server_configuration_panel[i].getIPAddress();
            if (ip_address.equals(gtm_ip_address)) {
                continue;
            }
            String root_password =
                    this.server_configuration_panel[i].getRootPassword();
            String[] create_other_installation_directory_command =
                {"sh", Define.CREATE_OTHER_INSTALLATION_DIRECTORY,
                    ip_address, installation_directory,
                    root_password, superuser};
            ExecShellCommand.execShellCommandWithPrintLogToPanel(
                    create_other_installation_directory_command,
                    this.installing_panel);
        }

        /*Construct nopass ssh superuser(owner PC) to superuser(other Servers)*/
        String ip_address_list = gtm_ip_address;
        for (int i = 0; i < this.number_of_servers; i++) {
            String ip_address =
                    this.server_configuration_panel[i].getIPAddress();
            if (ip_address.equals(gtm_ip_address)) {
                continue;
            }
            ip_address_list += " " + ip_address;
        }
        String[] construct_nopass_ssh_command =
            {"sh", Define.CONSTRUCT_NOPASS_SSH_SH,
                superuser, password, ip_address_list};
        ExecShellCommand.execShellCommandWithPrintLogToPanel(
                construct_nopass_ssh_command, this.installing_panel);

        /*Pgxc deploy all*/
        String[] deploy_all_command =
            {"sh", Define.DEPLOY_ALL_SH, superuser};
        ExecShellCommand.execShellCommandWithPrintLogToPanel(
                deploy_all_command, this.installing_panel);

        /*Setup bashrc*/
        String[] setup_bashrc_command =
            {"sh", Define.SETUP_BASHRC_SH, superuser};
        ExecShellCommand.execShellCommandWithPrintLogToPanel(
                setup_bashrc_command, this.installing_panel);

        /*Init all*/
        String[] init_all_command = {"sh",
                Define.INIT_ALL_SH, installation_directory, superuser};
        ExecShellCommand.execShellCommandWithPrintLogToPanel(
                init_all_command, this.installing_panel);

        /*Copy postgresql.conf*/
        for (int i = 0; i < this.number_of_servers; i++) {
            String ip_address =
                    this.server_configuration_panel[i].getIPAddress();
            String root_password =
                    this.server_configuration_panel[i].getRootPassword();
            String coordinator_postgresql_conf =
                    this.server_configuration_panel[i].getCoordinatorPostgresqlConf();
            if (!"".equals(coordinator_postgresql_conf)) {
                String coordinator_data_directory =
                        this.server_configuration_panel[i].getCoordinatorDataDirectory();
                String[] copy_postgresql_conf_command =
                    {"sh", Define.COPY_POSTGRESQL_CONF_SH,
                        coordinator_postgresql_conf, ip_address,
                        coordinator_data_directory, root_password,
                        superuser, installation_directory, "coordinator"};
                ExecShellCommand.execShellCommandWithPrintLogToPanel(
                        copy_postgresql_conf_command, this.installing_panel);
            }
            String datanode_postgresql_conf =
                    this.server_configuration_panel[i].getDatanodePostgresqlConf();
            if (!"".equals(datanode_postgresql_conf)) {
                String datanode_data_directory =
                        this.server_configuration_panel[i].getDatanodeDataDirectory();
                String[] copy_postgresql_conf_command =
                    {"sh", Define.COPY_POSTGRESQL_CONF_SH,
                        datanode_postgresql_conf, ip_address,
                        datanode_data_directory, root_password,
                        superuser, installation_directory, "datanode"};
                ExecShellCommand.execShellCommandWithPrintLogToPanel(
                        copy_postgresql_conf_command, this.installing_panel);
            }
        }

        /*Kill Log print frame*/
        this.installing_panel.killThread();
        String[] kill_command = {"sh", Define.KILL_TAIL_SH,
                LogPrintFile.getLog_time()};
        ExecShellCommand.execShellCommand(kill_command);

        /*Install finish.*/
        this.installing_panel.installFinish();
        return;

    }

    /*
     * addParameter
     * Add "parameter_name = value" to pgxcConf
     */

    private void addParameter(final String parameter_name, final String value) {
        try {
            File log_file = new File(this.create_config_file_directory
                    + "/" + Define.PGXC_CONF);
            PrintWriter print_writer =
                    new PrintWriter(new BufferedWriter(
                            new FileWriter(log_file, true)));
            print_writer.println(parameter_name + "=" + value);
            print_writer.close();
        } catch (Exception e) { }
        return;
    }

    /*
     * setPanel
     * Change showing-panel to next_panel
     */

    private void setPanel(final JPanel next_panel) {
        while (true) {
            try {
                this.getContentPane().removeAll();
                this.add(next_panel);
                this.revalidate();
                this.repaint();
                break;
            } catch (Exception e) { }
        }
        return;
    }

    public final QuestionDialog getQuestion_dialog() {
        return question_dialog;
    }
}