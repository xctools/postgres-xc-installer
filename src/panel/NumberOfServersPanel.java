package panel;
import java.awt.Font;

import javax.swing.JTextField;

import common.CreateObject;
import common.Define;
import common.JLabelParameter;
import common.JTextFieldParameter;
import common.Validation;

import dialog.WarningDialog;

import main.MainFrame;


/*
 * NumberOfServersPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class NumberOfServersPanel extends CommonPanel {
    private static final long serialVersionUID = -204672765879410880L;

    private final String DEFAULT_NUMBER_OF_SERVERS = "1";
    private final String INPUT_ERROR_NUMBER_OF_SERVERS = "\""
    + Define.NUMBER_OF_SERVERS + "\"";

    private final JLabelParameter header2_label_parameter = new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.NUMBER_OF_SERVERS_PANEL_NAME,
            new Font(Font.SERIF, Font.PLAIN, 20));
    private final JLabelParameter please_label_parameter = new JLabelParameter(
            50, 50, Define.MAIN_FRAME_WIDTH, 30,
            "Please select Number of Servers.",
            new Font(Font.SERIF, Font.PLAIN, 18));
    private final JLabelParameter number_of_servers_label_parameter =
            new JLabelParameter(
            60, 90, 170, 30, Define.NUMBER_OF_SERVERS + " :",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private JTextField number_of_servers_textfield;
    private final JTextFieldParameter number_of_servers_textfield_parameter =
            new JTextFieldParameter(
            240, 90, 50, 30, this.DEFAULT_NUMBER_OF_SERVERS);

    public NumberOfServersPanel(final MainFrame main_frame_org) {
        super(main_frame_org);

        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.please_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.number_of_servers_label_parameter));
        this.number_of_servers_textfield =
                CreateObject.createJTextField(
                        this.number_of_servers_textfield_parameter);
        this.main_panel.add(this.number_of_servers_textfield);

        return;
    }

    public final String getNumberOfServers() {
        return this.number_of_servers_textfield.getText().trim();
    }

    /*
     * numberOfServersIsValid
     * Check number_of_servers is valid(see Validation.numberOfServersIsValid).
     */

    private boolean numberOfServersIsValid() {
        String number_of_servers = this.getNumberOfServers();
        if (Validation.argIsEmpty(number_of_servers)) {
            new WarningDialog(Define.NOINPUT_MESSAGE_1
                    + this.INPUT_ERROR_NUMBER_OF_SERVERS
                    + Define.NOINPUT_MESSAGE_2,
                    this.main_frame);
            return false;
        } else if (!Validation.numberOfServersIsValid(number_of_servers)) {
            new WarningDialog(
                    Define.NUMBER_OF_SERVERS_OUT_OF_RANGE_MESSAGE,
                    this.main_frame);
            return false;
        }
        return true;
    }

    public final void nextButtonPushed() {
        if (this.numberOfServersIsValid()) {
            this.main_frame.nextPanel(this);
        }
        return;
    }
}
