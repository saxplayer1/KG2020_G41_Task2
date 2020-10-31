package Shalitkin;

import Shalitkin.DrawUtils.DrawUtils;
import Shalitkin.LineDrawers.BresenhamLineDrawer;
import Shalitkin.LineDrawers.DDALineDrawer;
import Shalitkin.LineDrawers.WuLineDrawer;
import Shalitkin.PixelDrawers.GraphicsPixelDrawer;
import Shalitkin.PixelDrawers.PixelDrawer;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import static java.awt.event.MouseEvent.BUTTON2;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseInputListener {
    private Point position = new Point(0, 0);

    public void setCurrentLD(int currentLD) {
        this.currentLD = currentLD;
    }

    private int currentLD = 0;


    public DrawPanel() {
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bI = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics bi_g = bI.createGraphics();
        bi_g.setColor(Color.white);
        bi_g.fillRect(0,0,getWidth(),getHeight());
        bi_g.setColor(Color.black);
        PixelDrawer pd = new GraphicsPixelDrawer(bi_g);

        LineDrawer ld = new DDALineDrawer(pd);
        DrawUtils.DrawSnowflake(ld, getWidth() / 4, getHeight() / 4, 100, 50);

        ld = new BresenhamLineDrawer(pd);
        DrawUtils.DrawSnowflake(ld, getWidth() / 2, getHeight() / 4, 100, 50);

        ld = new WuLineDrawer(pd);
        DrawUtils.DrawSnowflake(ld, getWidth() * 3 / 4, getHeight() / 4, 100, 50);

        switch (currentLD) {
            case (0) : {
                ld = new DDALineDrawer(pd);
                break;
            }
            case (1) : {
                ld = new BresenhamLineDrawer(pd);
                break;
            }
            case (2) : {
                ld = new WuLineDrawer(pd);
                break;
            }
        }
        ld.drawLine(getWidth() / 2, getHeight() * 3 / 4, position.x, position.y);
        drawAll(ld);
        g.drawImage(bI, 0, 0, null);
        bi_g.dispose();
    }

    private void drawAll(LineDrawer ld) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        position = new Point(e.getX(),e.getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position = new Point(e.getX(),e.getY());
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2) {
            switch (currentLD) {
                case (0) : {
                    setCurrentLD(1);
                    break;
                }
                case (1) : {
                    setCurrentLD(2);
                    break;
                }
                case (2) : {
                    setCurrentLD(0);
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
