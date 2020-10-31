package Shalitkin.PixelDrawers;

import Shalitkin.PixelDrawers.PixelDrawer;

import java.awt.*;

public class GraphicsPixelDrawer implements PixelDrawer {
    public GraphicsPixelDrawer(Graphics g) {
        this.g = g;
    }

    private Graphics g;
    @Override
    public void setPixel(int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x,y,1,1);
    }
}
