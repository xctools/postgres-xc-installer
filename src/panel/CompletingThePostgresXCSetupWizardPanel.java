package panel;

import java.awt.Font;

import common.CreateObject;
import common.Define;
import common.JLabelParameter;

import main.MainFrame;


/*
 * CompletingThePostgresXCSetupWizardPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class CompletingThePostgresXCSetupWizardPanel extends CommonPanel {

    private static final long serialVersionUID = 8644438343572936275L;

    private final JLabelParameter header2_label_parameter =
            new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.COMPLETING_THE_POSTGRES_XC_SETUP_WIZARD_PANEL_NAME,
            new Font(Font.SERIF, Font.PLAIN, 20));
    private final JLabelParameter	setup_label_parameter =
            new JLabelParameter(
            50, 50, Define.MAIN_FRAME_WIDTH, 30,
            "Setup has finished installing Postgres-XC on your servers.",
            new Font(Font.SERIF, Font.PLAIN, 18));
    private final JLabelParameter	please_label_parameter =
            new JLabelParameter(
            50, 90, Define.MAIN_FRAME_WIDTH, 30,
            "Please connect Coordinator with this command.",
            new Font(Font.SERIF, Font.PLAIN, 18));
    private JLabelParameter			psql_label_parameter =
            new JLabelParameter(
            60, 130, Define.MAIN_FRAME_WIDTH, 30,
            "", new Font(Font.SERIF, Font.PLAIN, 18));

    public final void setCoordinatorIPAddressAndPort(
            final String coordinator_ip_address,
            final String coordinator_port) {
        this.psql_label_parameter.setText("psql –h "
            + coordinator_ip_address + " –p " + coordinator_port + " postgres");
        this.main_panel.add(CreateObject.createJLabel(
                this.psql_label_parameter));
        return;
    }

    public CompletingThePostgresXCSetupWizardPanel(
            final MainFrame main_frame_org) {
        super(main_frame_org);

        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.setup_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.please_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.psql_label_parameter));
        this.back_button.setEnabled(false);
        this.next_button.setText("Finish");
        this.cancel_button.setEnabled(false);

        return;
    }
}
