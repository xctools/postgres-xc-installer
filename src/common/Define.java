package common;

/*
 * Define
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Define constants.
 */

public class Define {

    public static final int
        MAIN_FRAME_X = 100;
    public static final int
        MAIN_FRAME_Y = 0;
    public static final int
        MAIN_FRAME_WIDTH = 800;
    public static final int
        MAIN_FRAME_HEIGHT = 750;

    public static final int
        HEADER1_PANEL_HEIGHT = 100;
    public static final int
        HEADER2_PANEL_HEIGHT = 50;
    public static final int
        MAIN_PANEL_HEIGHT = 500;
    public static final int
        FOOTER_PANEL_HEIGHT =
            MAIN_FRAME_HEIGHT
            - (HEADER1_PANEL_HEIGHT + HEADER2_PANEL_HEIGHT + MAIN_PANEL_HEIGHT);

    public static final String
        RESOURCE = "./resource";

    public static final String
        IMAGE = RESOURCE + "/image";
    public static final String
        ICON_PING = IMAGE + "/icon.png";
    public static final String
        HEADER1_PING = IMAGE + "/header1.png";
    public static final String
        HEADER2_PING = IMAGE + "/header2.png";

    public static final String
        SHELL = RESOURCE + "/shell";
    public static final String
        CHECK_SUPERUSER_SH = SHELL + "/check_superuser.sh";
    public static final String
        CHECK_ROOT_PASSWORD_SH = SHELL + "/check_root_password.sh";
    public static final String
        CHECK_OTHER_SUPERUSER_SH = SHELL + "/check_other_superuser.sh";
    public static final String
        CHECK_OTHER_INSTALLATION_DIRECTORY_SH =
            SHELL + "/check_other_installation_directory.sh";
    public static final String
        CHECK_OTHER_DIRECTORY_SH = SHELL + "/check_other_directory.sh";
    public static final String
        CREATE_SUPERUSER_SH = SHELL + "/create_superuser.sh";
    public static final String
        CREATE_OTHER_SUPERUSER_SH = SHELL + "/create_other_superuser.sh";
    public static final String
        INSTALL_POSTGRES_XC_AND_PGXC_MONITOR_SH =
            SHELL + "/install_postgres_xc_and_pgxc_monitor.sh";
    public static final String
        CREATE_CONFIG_FILE_TEMPLATE_SH =
            SHELL + "/create_config_file_template.sh";
    public static final String
        CHOWN_POSTGRES_XC_SH = SHELL + "/chown_postgres_xc.sh";
    public static final String
        CREATE_OTHER_INSTALLATION_DIRECTORY =
            SHELL + "/create_other_installation_directory.sh";
    public static final String
        CONSTRUCT_NOPASS_SSH_SH =
            SHELL + "/construct_nopass_ssh.sh";
    public static final String
        DEPLOY_ALL_SH = SHELL + "/deploy_all.sh";
    public static final String
        SETUP_BASHRC_SH = SHELL + "/setup_bashrc.sh";
    public static final String
        INIT_ALL_SH = SHELL + "/init_all.sh";
    public static final String
        COPY_POSTGRESQL_CONF_SH = SHELL + "/copy_postgresql_conf.sh";
    public static final String
        KILL_TAIL_SH = SHELL + "/kill_tail.sh";

    public static final String
        INSTALLATION_DIRECTORY = "Installation Directory";
    public static final String
        SUPERUSER = "Superuser";
    public static final String
        PASSWORD = "Password";
    public static final String
        RETYPE_PASSWORD = "Retype password";
    public static final String
        GTM = "GTM";
    public static final String
        IP_ADDRESS = "IP Address";
    public static final String
        PORT = "Port";
    public static final String
        NODE_NAME = "Node Name";
    public static final String
        DATA_DIRECTORY = "Data Directory";
    public static final String
        ROOT_PASSWORD = "root password";
    public static final String
        NUMBER_OF_SERVERS = "Number of Servers";
    public static final String
        GTM_PROXY = "GTM-Proxy";
    public static final String
        COORDINATOR = "Coordinator";
    public static final String
        DATANODE = "Datanode";
    public static final String
        POSTGRESQL_CONF = "postgresql.conf";
    public static final String
        POOLER_PORT = "Pooler Port";
    public static final String
        PG_HBA_ENTRIES = "pg_hba entries";
    public static final String
        PGXC_CONF = "pgxcConf";

    public static final String
        TOP_PAGE_PANEL_NAME = "Top Page";
    public static final String
        INSTALLATION_DIRECTORY_PANEL_NAME = "Installation Directory";
    public static final String
        DATABASE_SUPERUSER_PANEL_NAME = "Database Superuser";
    public static final String
        GTM_CONFIGURATION_PANEL_NAME = "GTM Configuration";
    public static final String
        NUMBER_OF_SERVERS_PANEL_NAME = "Number of Servers";
    public static final String
        SERVER_CONFIGURATION_PANEL_NAME = "Server Configuration";
    public static final String
        HOST_AUTHENTICATION_PANEL_NAME = "Host Authentication";
    public static final String
        CHECK_ALL_INPUT_PANEL_NAME = "Check All Input";
    public static final String
        READY_TO_INSTALL_PANEL_NAME = "Ready to Install";
    public static final String
        INSTALLING_PANEL_NAME = "Installing";
    public static final String
        COMPLETING_THE_POSTGRES_XC_SETUP_WIZARD_PANEL_NAME =
            "Completing the Postgres-XC Setup Wizard";

    public static final String
        ERROR_MESSAGE_1 = "Error! Please check [";
    public static final String
        ERROR_MESSAGE_2 = "] and [pgxc_ctl_log]";

    public static final String
        ROOT_ONLY_MESSAGE = "For root only";
    public static final String
        PASSWORD_DO_NOT_MATCH_MESSAGE = "Passwords do not match";
    public static final String
        NOINPUT_MESSAGE_1 = "You must enter ";
    public static final String
        NOINPUT_MESSAGE_2 = " to continue";
    public static final String
        ALREADY_EXIST_MESSAGE = " already exists";
    public static final String
        IS_INVALID_MESSAGE = " is invalid";
    public static final String
        DIRECTORY = "Directory";
    public static final String
        USER = "User";
    public static final String
        AT = " at";
    public static final int
        PORT_MIN = 1024;
    public static final int
        PORT_MAX = 65535;
    public static final String
        PORT_OUT_OF_RANGE_MESSAGE =
            "The port number must be an integer between "
            + Define.PORT_MIN + " and " + Define.PORT_MAX;
    public static final String
        INVALID_BY_PERMISSION = " can not use";
    public static final int
        NUMBER_OF_SERVERS_MIN = 0;
    public static final String
        NUMBER_OF_SERVERS_OUT_OF_RANGE_MESSAGE =
            "The number of servers must be an integer more than "
            + NUMBER_OF_SERVERS_MIN;
    public static final String
        PORT_UNIQUE_MESSAGE = "All Ports must be unique";
    public static final String
        DIRECTORY_UNIQUE_MESSAGE = "All Directories must be unique";
    public static final String
        POSTGRESQL_CONF_NOT_FOUND_MESSAGE = "postgresql.conf file not found";

    public static final int
        LOG_PRINT_FRAME_X = 200;
    public static final int
        LOG_PRINT_FRAME_Y = 200;
    public static final int
        LOG_PRINT_FRAME_WIDTH = 650;
    public static final int
        LOG_PRINT_FRAME_HEIGHT = 500;
}