package dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import common.CreateObject;
import common.JButtonParameter;
import common.JLabelParameter;


/*
 * ErrorDialog
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Show error level message.
 */

public class ErrorDialog extends JDialog {

    private static final long serialVersionUID = 2092252972012496937L;

    private JLabelParameter error_message_label_parameter = new JLabelParameter(
            10, 10, -1, 50, "", new Font(Font.SERIF, Font.PLAIN, 15));

    private final JButtonParameter ok_button_parameter = new JButtonParameter(
            -1, 60, 100, 30, "OK", new Font(Font.SERIF, Font.BOLD, 15),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    System.exit(0);
                    return;
                }
            });

    public ErrorDialog(final String error_message, final Frame owner) {
        super(owner, true);

        int width = error_message.length() * 8 + 20;
        if (width < 400) {
            width = 400;
        }
        this.setBounds(150, 150, width, 150);
        this.setLayout(null);
        this.setTitle("Error");
        this.error_message_label_parameter.setWidth(width);
        this.error_message_label_parameter.setText(error_message);
        this.ok_button_parameter.setX(width / 2 - 50);
        this.add(CreateObject.createJLabel(this.error_message_label_parameter));
        this.add(CreateObject.createJButton(this.ok_button_parameter));

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
                return;
            }
        });

        this.setVisible(true);
    }
}
