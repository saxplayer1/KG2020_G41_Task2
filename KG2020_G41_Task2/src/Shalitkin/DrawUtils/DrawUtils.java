package Shalitkin.DrawUtils;

import Shalitkin.LineDrawer;

public class DrawUtils {
    public static void DrawSnowflake(LineDrawer ld, int x, int y, int r, int n) {
        double da = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double dx = r * Math.cos(da * i);
            double dy = r * Math.sin(da * i);
            ld.drawLine(x, y, x + (int)dx, y + (int)dy);
        }
    }
}
