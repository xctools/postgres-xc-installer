package panel;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.CreateObject;
import common.Define;
import common.JButtonParameter;
import common.JLabelParameter;
import common.JPanelParameter;

import main.MainFrame;


/*
 * CommonPanel
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Create common component such as header, footer, back/next/cancel_button.
 */

public class CommonPanel extends JPanel {

    private static final long serialVersionUID = -8531400192544191020L;

    private final JLabelParameter header1_label_parameter =
            new JLabelParameter(
            0, 0, Define.MAIN_FRAME_WIDTH, Define.HEADER1_PANEL_HEIGHT,
            Define.HEADER1_PING, null);

    protected JPanel header2_panel;
    private JPanelParameter header2_panel_parameter =
            new JPanelParameter(
            0, Define.HEADER1_PANEL_HEIGHT, Define.MAIN_FRAME_WIDTH,
            Define.HEADER2_PANEL_HEIGHT,
            Color.WHITE, Color.BLACK, 1);

    protected JPanel main_panel;
    private final JPanelParameter main_panel_parameter =
            new JPanelParameter(
            0, Define.HEADER1_PANEL_HEIGHT
            + Define.HEADER2_PANEL_HEIGHT, Define.MAIN_FRAME_WIDTH,
            Define.MAIN_PANEL_HEIGHT,
            Color.WHITE, Color.BLACK, 1);

    protected JPanel footer_panel;
    private JPanelParameter footer_panel_parameter =
            new JPanelParameter(
            0, Define.HEADER1_PANEL_HEIGHT
            + Define.HEADER2_PANEL_HEIGHT + Define.MAIN_PANEL_HEIGHT,
            Define.MAIN_FRAME_WIDTH, Define.FOOTER_PANEL_HEIGHT,
            Color.GRAY, Color.BLACK, 1);

    private JButtonParameter back_button_parameter =
            new JButtonParameter(
            Define.MAIN_FRAME_WIDTH / 2, 10, 100, 50, "<Back",
            new Font(Font.SERIF, Font.BOLD, 15),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    backButtonPushed();
                    return;
                }
            });
    protected JButton back_button;

    private JButtonParameter next_button_parameter =
            new JButtonParameter(
            Define.MAIN_FRAME_WIDTH / 2 + 110, 10, 100, 50, "Next>",
            new Font(Font.SERIF, Font.BOLD, 15),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    nextButtonPushed();
                    return;
                }
            });
    protected JButton next_button;

    private JButtonParameter cancel_button_parameter =
            new JButtonParameter(
            Define.MAIN_FRAME_WIDTH / 2 + 220, 10, 100, 50, "Cancel",
            new Font(Font.SERIF, Font.BOLD, 15),
            new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    cancelButtonPushed();
                    return;
                }
            });
    protected JButton cancel_button;

    protected MainFrame main_frame;

    public CommonPanel(final MainFrame main_frame_org) {

        this.main_frame = main_frame_org;
        this.setLayout(null);
        this.setBounds(0, 0, Define.MAIN_FRAME_WIDTH, Define.MAIN_FRAME_HEIGHT);

        this.add(CreateObject.createJLabelImage(this.header1_label_parameter));
        this.header2_panel = CreateObject.createJPanel(
                this.header2_panel_parameter);
        this.main_panel = CreateObject.createJPanel(this.main_panel_parameter);
        this.footer_panel = CreateObject.createJPanel(
                this.footer_panel_parameter);

        this.back_button = CreateObject.createJButton(
                this.back_button_parameter);
        this.footer_panel.add(this.back_button);

        this.next_button = CreateObject.createJButton(
                this.next_button_parameter);
        this.footer_panel.add(this.next_button);

        this.cancel_button = CreateObject.createJButton(
                this.cancel_button_parameter);
        this.footer_panel.add(this.cancel_button);

        this.add(this.header2_panel);
        this.add(this.main_panel);
        this.add(this.footer_panel);

        return;
    }

    public void backButtonPushed() {
        this.main_frame.backPanel(this);
        return;
    }

    public void nextButtonPushed() {
        this.main_frame.nextPanel(this);
        return;
    }

    public void cancelButtonPushed() {
        this.main_frame.getQuestion_dialog().setVisible(true);
        return;
    }
}