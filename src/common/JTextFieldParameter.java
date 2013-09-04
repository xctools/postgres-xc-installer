package common;

/*
 * JTextFieldParameter
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Used by CreateObject.createJTextField
 */

public class JTextFieldParameter {

    private int x;
    private int y;
    private int width;
    private int height;
    private String default_text;

    public JTextFieldParameter(final int x_org, final int y_org,
            final int width_org, final int height_org,
            final String default_text_org) {
        this.x = x_org;
        this.y = y_org;
        this.width = width_org;
        this.height = height_org;
        this.default_text = default_text_org;
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

    public final String getDefault_text() {
        return default_text;
    }

    public final void setDefault_text(final String default_text_org) {
        this.default_text = default_text_org;
    }
}
