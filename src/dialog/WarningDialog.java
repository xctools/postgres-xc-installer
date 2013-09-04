package dialog;

import java.awt.Font;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import common.CreateObject;
import common.JButtonParameter;
import common.JLabelParameter;


/*
 * WarningDialog
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Show warning level message.
 */

public class WarningDialog extends JDialog {

    private static final long serialVersionUID = 2092252972012496937L;

    private JLabelParameter warning_message_label_parameter =
            new JLabelParameter(
            10, 10, -1, 50, "", new Font(Font.SERIF, Font.PLAIN, 15));

    private final JButtonParameter ok_button_parameter = new JButtonParameter(
            -1, 60, 100, 30, "OK", new Font(Font.SERIF, Font.BOLD, 15),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    okButtonPushed();
                    return;
                }
            });

    public WarningDialog(final String warning_message, final Frame owner) {
        super(owner, true);

        int width = warning_message.length() * 8 + 20;
        if (width < 400) {
            width = 400;
        }
        this.setBounds(150, 150, width, 150);
        this.setLayout(null);
        this.setTitle("Warning");
        this.warning_message_label_parameter.setWidth(width);
        this.warning_message_label_parameter.setText(warning_message);
        this.ok_button_parameter.setX(width / 2 - 50);
        this.add(CreateObject.createJLabel(
                this.warning_message_label_parameter));
        this.add(CreateObject.createJButton(this.ok_button_parameter));
        this.setVisible(true);
    }

    private void okButtonPushed() {
        this.setVisible(false);
        return;
    }

}