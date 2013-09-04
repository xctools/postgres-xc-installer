package common;

import java.awt.Font;
import java.awt.event.ActionListener;

/*
 * JButtonParameter
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Used by CreateObject.createJButton
 */

public class JButtonParameter {

    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    private Font font;
    private ActionListener action_listener;

    public JButtonParameter(int x_org, int y_org, int width_org, int height_org,
            final String text_org, final Font font_org,
            final ActionListener action_listener_org) {
        this.x = x_org;
        this.y = y_org;
        this.width = width_org;
        this.height = height_org;
        this.text = text_org;
        this.font = font_org;
        this.action_listener = action_listener_org;
    }

    public JButtonParameter(final JButtonParameter org) {
        this.x = org.x;
        this.y = org.y;
        this.width = org.width;
        this.height = org.height;
        this.text = org.text;
        this.font = org.font;
        this.action_listener = org.action_listener;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final String getText() {
        return text;
    }

    public final Font getFont() {
        return font;
    }

    public final ActionListener getAction_listener() {
        return action_listener;
    }

    public final void setX(final int x_org) {
        this.x = x_org;
    }

}