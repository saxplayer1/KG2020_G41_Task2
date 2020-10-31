package Shalitkin.LineDrawers;

import Shalitkin.LineDrawer;
import Shalitkin.PixelDrawers.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        if (x1 - x2 == 0 && y1 - y2 != 0) {
            if (y1 > y2) {
                int temp = y2;
                y2 = y1;
                y1 = temp;
            }
            for (int i = y1; i < y2; i++) {
                pd.setPixel(x1, i, Color.black);
            }
            return;
        }

        if (y1 - y2 == 0 && x1 - x2 != 0) {
            if (x1 > x2) {
                int temp = x2;
                x2 = x1;
                x1 = temp;
            }
            for (int i = x1; i < x2; i++) {
                pd.setPixel(i, y1, Color.black);
            }
            return;
        }
        boolean steep = Math.abs(y1 - y2) > Math.abs(x1 - x2);
        if (steep) {
            int temp = x1; x1 = y1; y1 = temp;
            temp = x2; x2 = y2; y2 = temp;
        }

        if (x1 > x2) {
            int temp = x1; x1 = x2; x2 = temp;
            temp = y1; y1 = y2; y2 = temp;
        }

        double dx = x2 - x1;
        double dy = y2 - y1;
        double gradient = dy / dx;

        double y = y1 + gradient;
        for (int x = x1 + 1; x < x2; x++) {
            int intY = (int) y;
            pd.setPixel(
                    steep ? intY : x, steep ? x : intY,
                    new Color(0, 0, 0, (float)  (1 - (y - intY)))
            );
            pd.setPixel(
                    steep ? intY + 1 : x, steep ? x : intY + 1,
                    new Color(0, 0, 0, (float)  (y - intY))
            );
            y += gradient;
        }
    }
}
