package common;

import java.awt.Font;

/*
 * JLabelParameter
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Used by CreateObject.createJLabel and CreateObject.createJLabelImage.
 */

public class JLabelParameter {

    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    private Font font;

    public JLabelParameter(final int x_org, final int y_org,
            final int width_org, final int height_org,
            final String text_org, final Font font_org) {

        this.x = x_org;
        this.y = y_org;
        this.width = width_org;
        this.height = height_org;
        this.text = text_org;
        this.font = font_org;
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

    public Font getFont() {
        return font;
    }

    public final void setWidth(final int width_org) {
        this.width = width_org;
    }

    public final void setText(final String text_org) {
        this.text = text_org;
    }
}
