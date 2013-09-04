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
 * QuestionDialog
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Show question level message.
 */

public class QuestionDialog extends JDialog {

    private static final long serialVersionUID = -6698008622760922584L;
    private final JLabelParameter do_you_want_label_parameter =
            new JLabelParameter(
            10, 10, 380, 50, "Do you want to abort the installation process?",
            new Font(Font.SERIF, Font.PLAIN, 15));
    private final JButtonParameter yes_button_parameter = new JButtonParameter(
            70, 70, 100, 30, "Yes", new Font(Font.SERIF, Font.BOLD, 15),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    System.exit(0);
                    return;
                }
            });
    private final JButtonParameter no_button_parameter = new JButtonParameter(
            180, 70, 100, 30, "No", new Font(Font.SERIF, Font.BOLD, 15),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setVisible(false);
                    return;
                }
            });

    public QuestionDialog(final Frame owner) {
        super(owner, true);
        this.setLayout(null);
        this.setBounds(150, 150, 380, 150);
        this.setTitle("Question");
        this.add(CreateObject.createJLabel(this.do_you_want_label_parameter));
        this.add(CreateObject.createJButton(this.yes_button_parameter));
        this.add(CreateObject.createJButton(this.no_button_parameter));
        this.setVisible(false);
    }
}
