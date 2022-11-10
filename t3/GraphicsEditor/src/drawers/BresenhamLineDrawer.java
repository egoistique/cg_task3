package drawers;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer{
    private PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    private int sign(int x) {
        return Integer.compare(x, 0);
        // x равен нулю -> 0 ; x < 0 -> -1, x > 0 -> 1
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        int pdx, pdy, xORyWay1, xORyWay2, a;

        int dx = x2 - x1;
        int dy = y2 - y1;

        if (Math.abs(dx) > Math.abs(dy)) {
            pdx = sign(dx);
            pdy = 0;
            xORyWay1 = Math.abs(dy);
            xORyWay2 = Math.abs(dx);
        } else {
            pdx = 0;
            pdy = sign(dy);
            xORyWay1 = Math.abs(dx);
            xORyWay2 = Math.abs(dy);
        }

        a = xORyWay2 / 2;
        pd.drawPixel(x1, y1, Color.BLACK);

        for (int t = 0; t < xORyWay2; t++) {
            a -= xORyWay1;
            if (a < 0) {
                a += xORyWay2;
                x1 += sign(dx);
                y1 += sign(dy);
            } else {
                x1 += pdx;
                y1 += pdy;
            }

            pd.drawPixel(x1, y1, Color.BLACK);
        }
    }
}
