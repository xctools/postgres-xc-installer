package panel;
import java.awt.Font;

import common.CreateObject;
import common.Define;
import common.JLabelParameter;

import main.MainFrame;


/*
 * ReadyToInstallPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

public class ReadyToInstallPanel extends CommonPanel {
    private static final long serialVersionUID = 1129671642413315901L;

    private final JLabelParameter header2_label_parameter = new JLabelParameter(
            10, 10, Define.MAIN_FRAME_WIDTH, Define.HEADER2_PANEL_HEIGHT - 20,
            Define.READY_TO_INSTALL_PANEL_NAME,
            new Font(Font.SERIF, Font.PLAIN, 20));
    private final JLabelParameter setup_label_parameter = new JLabelParameter(
            50, 50, Define.MAIN_FRAME_WIDTH, 30,
            "Setup is now ready to begin installing Postgres-XC on your servers.",
            new Font(Font.SERIF, Font.PLAIN, 18));

    public ReadyToInstallPanel(final MainFrame main_frame_org) {
        super(main_frame_org);
        this.header2_panel.add(CreateObject.createJLabel(
                this.header2_label_parameter));
        this.main_panel.add(CreateObject.createJLabel(
                this.setup_label_parameter));
        this.next_button.setText("Install>");
        return;
    }
}
