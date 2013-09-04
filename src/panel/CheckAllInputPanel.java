package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import common.CreateObject;
import common.Define;
import common.ExecShellCommand;
import common.JLabelParameter;
import common.JPanelParameter;
import common.JScrollPaneParameter;
import common.JTextAreaParameter;
import common.JTextFieldParameter;
import common.LogPrintFile;

import dialog.ErrorDialog;
import dialog.WarningDialog;

import main.MainFrame;


/*
 * CheckAllInputPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class CheckAllInputPanel extends CommonPanel {
    private static final long serialVersionUID = -2135021437545527291L;

    private final JLabelParameter header2_label_parameter = new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.CHECK_ALL_INPUT_PANEL_NAME,
            new Font(Font.SERIF, Font.PLAIN, 20));

    private final JLabelParameter
    installation_directory_label_parameter = new JLabelParameter(
            60, 50, 170, 30, Define.INSTALLATION_DIRECTORY + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField installation_directory_textfield;
    private JTextFieldParameter
    installation_directory_textfield_parameter = new JTextFieldParameter(
            240, 50, 200, 30, "");

    private final JLabelParameter superuser_label_parameter =
            new JLabelParameter(
            60, 90, 170, 30, Define.SUPERUSER + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField superuser_textfield;
    private JTextFieldParameter superuser_textfield_parameter =
            new JTextFieldParameter(
            240, 90, 200, 30, "");

    private final JLabelParameter gtm_label_parameter = new JLabelParameter(
            60, 130, 60, 30, Define.GTM + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));

    private final JLabelParameter gtm_ip_address_label_parameter =
            new JLabelParameter(
            130, 130, 110, 30, Define.IP_ADDRESS + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField gtm_ip_address_textfield;
    private JTextFieldParameter gtm_ip_address_textfield_parameter =
            new JTextFieldParameter(
            250, 130, 150, 30, "");

    private final JLabelParameter gtm_port_laebl_parameter =
            new JLabelParameter(
            410, 130, 120, 30, Define.PORT + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField gtm_port_textfield;
    private JTextFieldParameter gtm_port_textfield_parameter =
            new JTextFieldParameter(
            540, 130, 200, 30, "");

    private final JLabelParameter gtm_node_name_label_parameter =
            new JLabelParameter(
            130, 170, 110, 30, Define.NODE_NAME + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField gtm_node_name_textfield;
    private JTextFieldParameter gtm_node_name_textfield_parameter =
            new JTextFieldParameter(
            250, 170, 150, 30, "");

    private final JLabelParameter gtm_data_directory_label_parameter =
            new JLabelParameter(
            410, 170, 120, 30, Define.DATA_DIRECTORY + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField gtm_data_directory_textfield;
    private JTextFieldParameter gtm_data_directory_textfield_parameter =
            new JTextFieldParameter(
            540, 170, 200, 30, "");

    private final JLabelParameter servers_label_parameter =
            new JLabelParameter(
            60, 210, 65, 30, "Servers :", new Font(Font.SERIF, Font.PLAIN, 15));
    private JPanel servers_panel;
    private final JPanelParameter servers_panel_parameter = new JPanelParameter(
            0, 0, 0, 0, Color.WHITE, Color.BLACK, 0);
    private JScrollPane servers_scroll;
    private final JScrollPaneParameter	servers_scroll_parameter =
            new JScrollPaneParameter(
            135, 210, 605, 160, Color.BLACK, 1, null);

    private JTextField[] server_ip_address_textfield;
    private JTextField[] gtm_proxy_node_name_textfield;
    private JTextField[] coordinator_node_name_textfield;
    private JTextField[] datanode_node_name_textfield;

    private final JLabelParameter pg_hba_entries_label_parameter =
            new JLabelParameter(
            60, 380, 120, 30, "pg_hba entries :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextArea pg_hba_entries_textarea;
    private final JTextAreaParameter
        pg_hba_entries_textarea_parameter = new JTextAreaParameter(
            0, 0, 0, 0, Color.BLACK, 0);
    private JScrollPane pg_hba_entries_scroll;
    private final JScrollPaneParameter	pg_hba_entries_scroll_parameter =
            new JScrollPaneParameter(
            190, 380, 550, 100, Color.BLACK, 1, null);

    private String superuser;
    private String installation_directory;
    private String gtm_ip_address;
    private String gtm_root_password;
    private String gtm_data_directory;
    private String[] server_ip_address;
    private String[] server_root_password;
    private String[] gtm_proxy_data_directory;
    private String[] coordinator_data_directory;
    private String[] datanode_data_directory;

    public CheckAllInputPanel(final MainFrame main_frame_org,
            final String installation_directory_org,
            final String superuser_org,
            final String gtm_ip_address_org,
            final String gtm_port_org,
            final String gtm_node_name_org,
            final String gtm_data_directory_org,
            final String gtm_root_password_org,
            final String[] server_ip_address_org,
            final String[] gtm_proxy_port_org,
            final String[] gtm_proxy_node_name_org,
            final String[] gtm_proxy_data_directory_org,
            final String[] coordinator_port_org,
            final String[] coordinator_node_name_org,
            final String[] coordinator_data_directory_org,
            final String[] coordinator_postgresql_conf_org,
            final String[] coordinator_pooler_port_org,
            final String[] datanode_port_org,
            final String[] datanode_node_name_org,
            final String[] datanode_data_directory_org,
            final String[] datanode_postgresql_conf_org,
            final String[] server_root_password_org,
            final String[] pg_hba_entries_org) {
        super(main_frame_org);

        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_label_parameter));

        this.main_panel.add(CreateObject.createJLabel(
                this.installation_directory_label_parameter));
        this.installation_directory_textfield_parameter.setDefault_text(
                installation_directory_org);
        this.installation_directory_textfield =
                CreateObject.createJTextField(
                        this.installation_directory_textfield_parameter);
        this.installation_directory_textfield.setEditable(false);
        this.main_panel.add(this.installation_directory_textfield);

        this.main_panel.add(CreateObject.createJLabel(
                this.superuser_label_parameter));
        this.superuser_textfield_parameter.setDefault_text(superuser_org);
        this.superuser_textfield = CreateObject.createJTextField(
                this.superuser_textfield_parameter);
        this.superuser_textfield.setEditable(false);
        this.main_panel.add(this.superuser_textfield);

        this.main_panel.add(CreateObject.createJLabel(
                this.gtm_label_parameter));

        this.main_panel.add(CreateObject.createJLabel(
                this.gtm_ip_address_label_parameter));
        this.gtm_ip_address_textfield_parameter.setDefault_text(
                gtm_ip_address_org);
        this.gtm_ip_address_textfield = CreateObject.createJTextField(
                this.gtm_ip_address_textfield_parameter);
        this.gtm_ip_address_textfield.setEditable(false);
        this.main_panel.add(this.gtm_ip_address_textfield);

        this.main_panel.add(CreateObject.createJLabel(
                this.gtm_port_laebl_parameter));
        this.gtm_port_textfield_parameter.setDefault_text(gtm_port_org);
        this.gtm_port_textfield = CreateObject.createJTextField(
                this.gtm_port_textfield_parameter);
        this.gtm_port_textfield.setEditable(false);
        this.main_panel.add(this.gtm_port_textfield);

        this.main_panel.add(CreateObject.createJLabel(
                this.gtm_node_name_label_parameter));
        this.gtm_node_name_textfield_parameter.setDefault_text(
                gtm_node_name_org);
        this.gtm_node_name_textfield = CreateObject.createJTextField(
                this.gtm_node_name_textfield_parameter);
        this.gtm_node_name_textfield.setEditable(false);
        this.main_panel.add(this.gtm_node_name_textfield);

        this.main_panel.add(CreateObject.createJLabel(
                this.gtm_data_directory_label_parameter));
        this.gtm_data_directory_textfield_parameter.setDefault_text(
                gtm_data_directory_org);
        this.gtm_data_directory_textfield =
                CreateObject.createJTextField(
                        this.gtm_data_directory_textfield_parameter);
        this.gtm_data_directory_textfield.setEditable(false);
        this.main_panel.add(this.gtm_data_directory_textfield);

        int number_of_servers = server_ip_address_org.length;
        this.main_panel.add(CreateObject.createJLabel(
                this.servers_label_parameter));
        this.servers_panel = CreateObject.createJPanel(
                this.servers_panel_parameter);
        this.servers_scroll_parameter.setComponent(this.servers_panel);
        this.servers_scroll = CreateObject.createJScrollPane(
                this.servers_scroll_parameter);
        this.servers_panel.setPreferredSize(new Dimension(1860,
                number_of_servers * 160 + 10));
        this.servers_scroll.getViewport().add(this.servers_panel);
        this.main_panel.add(CreateObject.createJScrollPane(
                this.servers_scroll_parameter));
        this.server_ip_address_textfield = new JTextField[number_of_servers];
        this.gtm_proxy_node_name_textfield = new JTextField[number_of_servers];
        this.coordinator_node_name_textfield =
                new JTextField[number_of_servers];
        this.datanode_node_name_textfield = new JTextField[number_of_servers];

        for (int i = 0; i < number_of_servers; i++) {
            JLabelParameter server_label_parameter =
                    new JLabelParameter(
                    5, 10 + 160 * i, 90, 30, "Server " + (i + 1) + " :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    server_label_parameter));

            JLabelParameter server_ip_address_label_parameter =
                    new JLabelParameter(
                    105, 10 + 160 * i, 90, 30, "IP Address :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    server_ip_address_label_parameter));

            JTextFieldParameter server_ip_address_textfield_parameter =
                    new JTextFieldParameter(
                    205, 10 + 160 * i, 90, 30, server_ip_address_org[i]);
            server_ip_address_textfield[i] =
                    CreateObject.createJTextField(
                            server_ip_address_textfield_parameter);
            server_ip_address_textfield[i].setEditable(false);
            this.servers_panel.add(server_ip_address_textfield[i]);

            JLabelParameter gtm_proxy_label_parameter =
                    new JLabelParameter(
                    105, 50 + 160 * i, 110, 30, "GTM-Proxy :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    gtm_proxy_label_parameter));

            JLabelParameter gtm_proxy_port_label_parameter =
                    new JLabelParameter(
                    225, 50 + 160 * i, 40, 30, "Port :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    gtm_proxy_port_label_parameter));

            JTextFieldParameter gtm_proxy_port_textfield_parameter =
                    new JTextFieldParameter(
                    275, 50 + 160 * i, 60, 30, gtm_proxy_port_org[i]);
            JTextField gtm_proxy_port_textfield =
                    CreateObject.createJTextField(
                            gtm_proxy_port_textfield_parameter);
            gtm_proxy_port_textfield.setEditable(false);
            this.servers_panel.add(gtm_proxy_port_textfield);

            JLabelParameter gtm_proxy_node_name_label_parameter =
                    new JLabelParameter(
                    345, 50 + 160 * i, 95, 30, "Node Name :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    gtm_proxy_node_name_label_parameter));

            JTextFieldParameter gtm_proxy_node_name_textfield_parameter =
                    new JTextFieldParameter(
                    450, 50 + 160 * i, 90, 30, gtm_proxy_node_name_org[i]);
            gtm_proxy_node_name_textfield[i] =
                    CreateObject.createJTextField(
                            gtm_proxy_node_name_textfield_parameter);
            gtm_proxy_node_name_textfield[i].setEditable(false);
            this.servers_panel.add(gtm_proxy_node_name_textfield[i]);

            JLabelParameter gtm_proxy_data_directory_label_parameter =
                    new JLabelParameter(
                    550, 50 + 160 * i, 120, 30, "Data Directory :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    gtm_proxy_data_directory_label_parameter));

            JTextFieldParameter gtm_proxy_data_directory_textfield_parameter =
                    new JTextFieldParameter(
                    680, 50 + 160 * i, 200, 30,
                    gtm_proxy_data_directory_org[i]);
            JTextField gtm_proxy_data_directory_textfield =
                    CreateObject.createJTextField(
                            gtm_proxy_data_directory_textfield_parameter);
            gtm_proxy_data_directory_textfield.setEditable(false);
            this.servers_panel.add(gtm_proxy_data_directory_textfield);

            JLabelParameter coordinator_label_parameter =
                    new JLabelParameter(
                    105, 90 + 160 * i, 110, 30, "Coordinator :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    coordinator_label_parameter));

            JLabelParameter coordinator_port_label_parameter =
                    new JLabelParameter(
                    225, 90 + 160 * i, 40, 30, "Port :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    coordinator_port_label_parameter));

            JTextFieldParameter coordinator_port_textfield_parameter =
                    new JTextFieldParameter(
                    275, 90 + 160 * i, 60, 30,
                    coordinator_port_org[i]);
            JTextField coordinator_port_textfield =
                    CreateObject.createJTextField(
                            coordinator_port_textfield_parameter);
            coordinator_port_textfield.setEditable(false);
            this.servers_panel.add(coordinator_port_textfield);

            JLabelParameter coordinator_node_name_label_parameter =
                    new JLabelParameter(
                    345, 90 + 160 * i, 95, 30, "Node Name :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    coordinator_node_name_label_parameter));

            JTextFieldParameter coordinator_node_name_textfield_parameter =
                    new JTextFieldParameter(
                    450, 90 + 160 * i, 90, 30,
                    coordinator_node_name_org[i]);
            coordinator_node_name_textfield[i] =
                    CreateObject.createJTextField(
                            coordinator_node_name_textfield_parameter);
            coordinator_node_name_textfield[i].setEditable(false);
            this.servers_panel.add(coordinator_node_name_textfield[i]);

            JLabelParameter coordinator_data_directory_label_parameter =
                    new JLabelParameter(
                    550, 90 + 160 * i, 120, 30, "Data Directory :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    coordinator_data_directory_label_parameter));

            JTextFieldParameter coordinator_data_directory_textfield_parameter =
                    new JTextFieldParameter(
                    680, 90 + 160 * i, 200, 30,
                    coordinator_data_directory_org[i]);
            JTextField coordinator_data_directory_textfield =
                    CreateObject.createJTextField(
                            coordinator_data_directory_textfield_parameter);
            coordinator_data_directory_textfield.setEditable(false);
            this.servers_panel.add(coordinator_data_directory_textfield);

            JLabelParameter coordinator_postgresql_conf_label_parameter =
                    new JLabelParameter(
                    890, 90 + 160 * i, 130, 30, "postgresql.conf :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    coordinator_postgresql_conf_label_parameter));

            JTextFieldParameter
                coordinator_postgresql_conf_textfield_parameter =
                    new JTextFieldParameter(
                    1030, 90 + 160 * i, 220, 30,
                    coordinator_postgresql_conf_org[i]);
            JTextField coordinator_postgresql_conf_textfield =
                    CreateObject.createJTextField(
                            coordinator_postgresql_conf_textfield_parameter);
            coordinator_postgresql_conf_textfield.setEditable(false);
            this.servers_panel.add(coordinator_postgresql_conf_textfield);

            JLabelParameter coordinator_pooler_port_label_parameter =
                    new JLabelParameter(
                    1260, 90 + 160 * i, 95, 30, "Pooler Port :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    coordinator_pooler_port_label_parameter));

            JTextFieldParameter coordinator_pooler_port_textfield_parameter =
                    new JTextFieldParameter(
                    1365, 90 + 160 * i, 60, 30,
                    coordinator_pooler_port_org[i]);
            JTextField coordinator_pooler_port_textfield =
                    CreateObject.createJTextField(
                            coordinator_pooler_port_textfield_parameter);
            coordinator_pooler_port_textfield.setEditable(false);
            this.servers_panel.add(coordinator_pooler_port_textfield);

            JLabelParameter datanode_label_parameter =
                    new JLabelParameter(
                    105, 130 + 160 * i, 110, 30, "Datanode :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    datanode_label_parameter));

            JLabelParameter datanode_port_label_parameter =
                    new JLabelParameter(
                    225, 130 + 160 * i, 40, 30, "Port :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    datanode_port_label_parameter));

            JTextFieldParameter datanode_port_textfield_parameter =
                    new JTextFieldParameter(
                    275, 130 + 160 * i, 60, 30,
                    datanode_port_org[i]);
            JTextField datanode_textfield =
                    CreateObject.createJTextField(
                            datanode_port_textfield_parameter);
            datanode_textfield.setEditable(false);
            this.servers_panel.add(datanode_textfield);

            JLabelParameter datanode_node_name_label_parameter =
                    new JLabelParameter(
                    345, 130 + 160 * i, 95, 30, "Node Name :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    datanode_node_name_label_parameter));

            JTextFieldParameter datanode_node_name_textfield_parameter =
                    new JTextFieldParameter(
                    450, 130 + 160 * i, 90, 30,
                    datanode_node_name_org[i]);
            datanode_node_name_textfield[i] =
                    CreateObject.createJTextField(
                            datanode_node_name_textfield_parameter);
            datanode_node_name_textfield[i].setEditable(false);
            this.servers_panel.add(datanode_node_name_textfield[i]);

            JLabelParameter datanode_data_directory_label_parameter =
                    new JLabelParameter(
                    550, 130 + 160 * i, 120, 30, "Data Directory :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    datanode_data_directory_label_parameter));

            JTextFieldParameter datanode_data_directory_textfield_parameter =
                    new JTextFieldParameter(
                    680, 130 + 160 * i, 200, 30,
                    datanode_data_directory_org[i]);
            JTextField datanode_data_directory_textfield =
                    CreateObject.createJTextField(
                            datanode_data_directory_textfield_parameter);
            datanode_data_directory_textfield.setEditable(false);
            this.servers_panel.add(datanode_data_directory_textfield);

            JLabelParameter datanode_postgresql_conf_label_parameter =
                    new JLabelParameter(
                    890, 130 + 160 * i, 130, 30, "postgresql.conf :",
                    new Font(Font.SERIF, Font.PLAIN, 15));
            this.servers_panel.add(CreateObject.createJLabel(
                    datanode_postgresql_conf_label_parameter));

            JTextFieldParameter datanode_postgresql_conf_textfield_parameter =
                    new JTextFieldParameter(
                    1030, 130 + 160 * i, 220, 30,
                    datanode_postgresql_conf_org[i]);
            JTextField datanode_postgresql_conf_textfield =
                    CreateObject.createJTextField(
                            datanode_postgresql_conf_textfield_parameter);
            datanode_postgresql_conf_textfield.setEditable(false);
            this.servers_panel.add(datanode_postgresql_conf_textfield);
        }

        int number_of_pg_hba_entries = pg_hba_entries_org.length;
        this.main_panel.add(CreateObject.createJLabel(
                this.pg_hba_entries_label_parameter));
        this.pg_hba_entries_textarea = CreateObject.createJTextArea(
                this.pg_hba_entries_textarea_parameter);
        this.pg_hba_entries_textarea.setEditable(false);
        String pg_hba_entries_all = "";
        for (int i = 0; i < number_of_pg_hba_entries; i++) {
            pg_hba_entries_all += pg_hba_entries_org[i] + "\n";
        }
        pg_hba_entries_textarea.setText(pg_hba_entries_all);
        this.pg_hba_entries_scroll_parameter.setComponent(
                this.pg_hba_entries_textarea);
        this.pg_hba_entries_scroll = CreateObject.createJScrollPane(
                this.pg_hba_entries_scroll_parameter);
        this.pg_hba_entries_textarea.setPreferredSize(new Dimension(
                530, number_of_pg_hba_entries * 15 + 10));
        this.pg_hba_entries_scroll.getViewport().add(
                this.pg_hba_entries_textarea);
        this.main_panel.add(CreateObject.createJScrollPane(
                this.pg_hba_entries_scroll_parameter));

        if (this.invalidInputExist(gtm_ip_address_org,
                server_ip_address_org, gtm_proxy_node_name_org,
                coordinator_node_name_org, datanode_node_name_org)) {
            this.next_button.setEnabled(false);
        }

        this.superuser = superuser_org;
        this.installation_directory = installation_directory_org;
        this.gtm_ip_address = gtm_ip_address_org;
        this.gtm_root_password = gtm_root_password_org;
        this.gtm_data_directory = gtm_data_directory_org;
        this.server_ip_address = new String[number_of_servers];
        this.server_root_password = new String[number_of_servers];
        this.gtm_proxy_data_directory = new String[number_of_servers];
        this.coordinator_data_directory = new String[number_of_servers];
        this.datanode_data_directory = new String[number_of_servers];

        for (int i = 0; i < number_of_servers; i++) {
            this.server_ip_address[i] = server_ip_address_org[i];
            this.server_root_password[i] = server_root_password_org[i];
            this.gtm_proxy_data_directory[i] = gtm_proxy_data_directory_org[i];
            this.coordinator_data_directory[i] =
                    coordinator_data_directory_org[i];
            this.datanode_data_directory[i] = datanode_data_directory_org[i];
        }
    }

    /*
     * invalidInputExist
     * Check each ip_address and each node_name are unique.
     */

    private boolean invalidInputExist(
            final String gtm_ip_address,
            final String[] server_ip_address,
            final String[] gtm_proxy_node_name,
            final String[] coordinator_node_name,
            final String[] datanode_node_name) {

        int number_of_servers = server_ip_address.length;

        for (int i = 0; i < number_of_servers; i++) {
            if (gtm_ip_address.equals(server_ip_address[i])) {
                this.gtm_ip_address_textfield.setForeground(Color.RED);
                this.server_ip_address_textfield[i].setForeground(Color.RED);
                return true;
            }
        }

        for (int i = 0; i < number_of_servers; i++) {
            for (int j = (i + 1); j < number_of_servers; j++) {
                if (server_ip_address[i].equals(server_ip_address[j])) {
                    this.server_ip_address_textfield[i].setForeground(
                            Color.RED);
                    this.server_ip_address_textfield[j].setForeground(
                            Color.RED);
                    return true;
                }
            }
        }

        for (int i = 0; i < number_of_servers; i++) {
            for (int j = (i + 1); j < number_of_servers; j++) {
                if (gtm_proxy_node_name[i].equals(gtm_proxy_node_name[j])) {
                    this.gtm_proxy_node_name_textfield[i].setForeground(
                            Color.RED);
                    this.gtm_proxy_node_name_textfield[j].setForeground(
                            Color.RED);
                    return true;
                }
            }
        }

        for (int i = 0; i < number_of_servers; i++) {
            for (int j = (i + 1); j < number_of_servers; j++) {
                if (coordinator_node_name[i].equals(coordinator_node_name[j])) {
                    this.coordinator_node_name_textfield[i].setForeground(
                            Color.RED);
                    this.coordinator_node_name_textfield[j].setForeground(
                            Color.RED);
                    return true;
                }
            }
        }

        for (int i = 0; i < number_of_servers; i++) {
            for (int j = (i + 1); j < number_of_servers; j++) {
                if (datanode_node_name[i].equals(datanode_node_name[j])) {
                    this.datanode_node_name_textfield[i].setForeground(
                            Color.RED);
                    this.datanode_node_name_textfield[j].setForeground(
                            Color.RED);
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * installationDirectoryNotExist
     * Check Postgres-XC-installation-direcotry does not exist.
     */

    private boolean installationDirectoryNotExist(
            final String ip_address, final String root_password) {
        String[] check_other_directory_command =
            {"sh", Define.CHECK_OTHER_INSTALLATION_DIRECTORY_SH, ip_address,
                root_password, this.installation_directory};
        int ret = ExecShellCommand.execShellCommandWithReturn(
                check_other_directory_command);
        if (ret == 0) {
            return true;
        } else if (ret == 1) {
            new WarningDialog(
                    Define.DIRECTORY + " \""
                            + this.installation_directory + "\""
                            + Define.ALREADY_EXIST_MESSAGE
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
     * directoryIsValid
     * Check the directory does not exist
     * and superuser can create the directory.
     */

    private boolean directoryIsValid(final String ip_address,
            final String root_password, final String directory) {
        String[] check_other_directory_command =
            {"sh", Define.CHECK_OTHER_DIRECTORY_SH, ip_address,
                this.superuser, root_password, directory};
        int ret = ExecShellCommand.execShellCommandWithReturn(
                check_other_directory_command);
        if (ret == 0) {
            return true;
        } else if (ret == 1) {
            new WarningDialog(Define.DIRECTORY + " \"" + directory
                    + "\"" + Define.ALREADY_EXIST_MESSAGE
                    + Define.AT + " \"" + ip_address + "\"",
                    this.main_frame);
            return false;
        } else if (ret == 2) {
            new WarningDialog(Define.USER + " \"" + this.superuser
                    + "\"" + Define.INVALID_BY_PERMISSION + " "
                    + Define.DIRECTORY + " \"" + directory + "\""
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
        if (!this.installationDirectoryNotExist(this.gtm_ip_address,
                this.gtm_root_password)) {
            return;
        } else if (!this.directoryIsValid(this.gtm_ip_address,
                this.gtm_root_password, this.gtm_data_directory)) {
            return;
        }

        int number_of_servers = this.server_ip_address.length;
        for (int i = 0; i < number_of_servers; i++) {
            if (!this.installationDirectoryNotExist(this.server_ip_address[i],
                    this.server_root_password[i])) {
                return;
            } else if (!this.directoryIsValid(this.server_ip_address[i],
                    this.server_root_password[i],
                    this.gtm_proxy_data_directory[i])) {
                return;
            } else if (!this.directoryIsValid(this.server_ip_address[i],
                    this.server_root_password[i],
                    this.coordinator_data_directory[i])) {
                return;
            } else if (!this.directoryIsValid(this.server_ip_address[i],
                    this.server_root_password[i],
                    this.datanode_data_directory[i])) {
                return;
            }
        }

        this.main_frame.nextPanel(this);
        return;
    }
}
