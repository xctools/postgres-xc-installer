package common;

import java.awt.Color;

/*
 * JPanelParameter
 *
 * Copyright (C) 2013 NTT Software Corporation.
 */

/*
 * Used by CreateObject.createJPanel
 */

public class JPanelParameter {

    private int x;
    private int y;
    private int width;
    private int height;
    private Color background_color;
    private Color border_color;
    private int border_thickness;

    public JPanelParameter(final int x_org, final int y_org,
            final int width_org, final int height_org,
            final Color background_color_org,
            final Color border_color_org, final int border_thickness_org) {
        this.x = x_org;
        this.y = y_org;
        this.width = width_org;
        this.height = height_org;
        this.background_color = background_color_org;
        this.border_color = border_color_org;
        this.border_thickness = border_thickness_org;
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

    public final Color getBackground_color() {
        return background_color;
    }

    public final Color getBorder_color() {
        return border_color;
    }

    public final int getBorder_thickness() {
        return border_thickness;
    }
}
