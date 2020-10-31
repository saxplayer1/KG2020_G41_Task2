package Shalitkin.LineDrawers;

import Shalitkin.LineDrawer;
import Shalitkin.PixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {
    private PixelDrawer pd;
    private Graphics g;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int xstart, int ystart, int xend, int yend) {
        int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;
        dx = xend - xstart;
        dy = yend - ystart;

        incx = sign(dx);
        incy = sign(dy);

        dx = Math.abs(dx);
        dy = Math.abs(dy);

        if (dx > dy)
        {
            pdx = incx; pdy = 0;
            es = dy; el = dx;
        }
        else
        {
            pdx = 0; pdy = incy;
            es = dx; el = dy;
        }

        x = xstart;
        y = ystart;
        err = el / 2;
        pd.setPixel(x, y,Color.green);
        for (int t = 0; t < el; t++)
        {
            err -= es;
            if (err < 0)
            {
                err += el;
                x += incx;
                y += incy;
            }
            else
            {
                x += pdx;
                y += pdy;
            }
            pd.setPixel(x, y,Color.green);
        }


    }
    private int sign(int x)
    {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
    }
}
