package panel;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import common.CreateObject;
import common.Define;
import common.ExecShellCommand;
import common.JButtonParameter;
import common.JLabelParameter;

import dialog.ErrorDialog;

import main.MainFrame;


/*
 * TopPagePanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class TopPagePanel extends CommonPanel {

    private static final long serialVersionUID = 7975909421301582706L;

    private final JLabelParameter header2_text_label_parameter =
            new JLabelParameter(
            Define.MAIN_FRAME_WIDTH / 2 + 100, 10, 80,
            Define.HEADER2_PANEL_HEIGHT - 20,
            "Packaged by:", new Font(Font.SERIF, Font.BOLD, 12));

    private final JLabelParameter header2_image_label_parameter =
            new JLabelParameter(
            Define.MAIN_FRAME_WIDTH / 2 + 180, 0,
            Define.MAIN_FRAME_WIDTH / 2 - 180, 50,
            Define.HEADER2_PING, null);

    private final JButtonParameter install_button_parameter =
            new JButtonParameter(
            50, 50, 300, 50, "Install Postgres-XC",
            new Font(Font.SERIF, Font.BOLD, 15),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    installPostgresXcButtonPushed();
                    return;
                }
            });

    private final JButtonParameter uninstall_button_parameter =
            new JButtonParameter(
            50, 110, 300, 50, "Uninstall Postgres-XC",
            new Font(Font.SERIF, Font.BOLD, 15), null);

    public TopPagePanel(final MainFrame main_frame_org) {

        super(main_frame_org);

        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_text_label_parameter));
        this.header2_panel.add(CreateObject.createJLabelImage(
                this.header2_image_label_parameter));
        this.main_panel.add(CreateObject.createJButton(
                this.install_button_parameter));
        JButton uninstall_button = CreateObject.createJButton(
                this.uninstall_button_parameter);
        this.main_panel.add(uninstall_button);
        uninstall_button.setEnabled(false);

        this.back_button.setEnabled(false);
        this.next_button.setEnabled(false);

        return;
    }

    public final void installPostgresXcButtonPushed() {
        if (this.thisUserIsRoot()) {
            this.main_frame.nextPanel(this);
        } else {
            new ErrorDialog(Define.ROOT_ONLY_MESSAGE, this.main_frame);
        }
        return;
    }

    private boolean thisUserIsRoot() {
        String[] whoami_command = {"sh", "-c", "whoami"};
        String this_user = ExecShellCommand.execShellCommand(whoami_command);
        return this_user.equals("root");
    }
}
