package common;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/*
 * CreateObject
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Create component by using argument_param.
 */

public class CreateObject {

    public static JLabel createJLabel(final JLabelParameter param) {
        JLabel label = new JLabel(param.getText());
        label.setBounds(param.getX(), param.getY(),
                param.getWidth(), param.getHeight());
        label.setFont(param.getFont());
        return label;
    }

    public static JLabel createJLabelImage(final JLabelParameter param) {
        JLabel label = new JLabel(new ImageIcon(param.getText()));
        label.setBounds(param.getX(), param.getY(),
                param.getWidth(), param.getHeight());
        return label;
    }

    public static JTextField createJTextField(final JTextFieldParameter param) {
        JTextField textfield = new JTextField(param.getDefault_text());
        textfield.setBounds(param.getX(), param.getY(),
                param.getWidth(), param.getHeight());
        return textfield;
    }

    public static JPasswordField
    createJPasswordField(final JTextFieldParameter param) {
        JPasswordField passwordfield = new JPasswordField(
                param.getDefault_text());
        passwordfield.setBounds(param.getX(), param.getY(),
                param.getWidth(), param.getHeight());
        return passwordfield;
    }

    public static JButton createJButton(final JButtonParameter param) {
        JButton button = new JButton(param.getText());
        if (param.getAction_listener() != null) {
            button.addActionListener(param.getAction_listener());
        }
        button.setBounds(param.getX(), param.getY(),
                param.getWidth(), param.getHeight());
        button.setFont(param.getFont());
        return button;
    }

    public static JPanel createJPanel(final JPanelParameter param) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(param.getX(), param.getY(),
                param.getWidth(), param.getHeight());
        panel.setBackground(param.getBackground_color());
        panel.setBorder(
                new LineBorder(param.getBorder_color(),
                        param.getBorder_thickness()));
        return panel;
    }

    public static JTextArea createJTextArea(JTextAreaParameter param) {
        JTextArea textarea = new JTextArea();
        textarea.setBounds(param.getX(), param.getY(),
                param.getWidth(), param.getHeight());
        textarea.setBorder(
                new LineBorder(param.getBorder_color(),
                        param.getBorder_thickness()));
        return textarea;
    }

    public static JScrollPane createJScrollPane(
            final JScrollPaneParameter param) {
        JScrollPane scroll_pane = new JScrollPane(param.getComponent());
        scroll_pane.setBounds(param.getX(), param.getY(),
                param.getWidth(), param.getHeight());
        scroll_pane.setBorder(
                new LineBorder(param.getBorder_color(),
                        param.getBorder_thickness()));
        return scroll_pane;
    }
}