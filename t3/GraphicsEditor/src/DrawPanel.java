import drawers.BresenhamLineDrawer;
import drawers.GraphicsPixelDrawer;
import drawers.LineDrawer;
import functions.Functions;
import shapes.curves.BezierCurve;
import shapes.curves.HermitCurve;
import shapes.curves.SplineCurve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class DrawPanel extends JPanel {
    private final ScreenConverter converter;
    private final Line ox;
    private final Line oy;
    private final Line current;
    private Point lastP;
    public static ArrayList<Color> colors = new ArrayList<>();
    private ArrayList<Functions> func;
    private Timer timer;


    public DrawPanel() {
        timer = new Timer(20, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();

        func = new ArrayList<>();

        colors.add(Color.BLACK);

        converter = new ScreenConverter(800, 600, -2, 2, 4, 4);
        ox = new Line(new RealPoint(-converter.getsWidth(), 0), new RealPoint(converter.getsWidth(), 0));
        oy = new Line(new RealPoint(0, -converter.getsHeight()), new RealPoint(0, converter.getsHeight()));
        current = new Line(new RealPoint(0, 0), new RealPoint(0, 0));

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastP != null) {
                    Point curP = e.getPoint();
                    ScreenPoint delta = new ScreenPoint(curP.x - lastP.x, curP.y - lastP.y);
                    RealPoint deltaR = converter.s2r(delta);
                    converter.setX(deltaR.getX());
                    converter.setY(deltaR.getY());
                    lastP = curP;
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ScreenPoint sp = new ScreenPoint(e.getX(), e.getY());
                current.setP2(converter.s2r(sp));
                repaint();
            }
        });

        this.addMouseWheelListener(e -> {
            int count = e.getWheelRotation();
            double base = count < 0 ? 0.99 : 1.01;
            double coef = 1;
            for (int i = Math.abs(count); i > 0; i--) {
                coef *= base;
            }
            converter.setWidth(converter.getWidth() * coef);
            converter.setHeight(converter.getHeight() * coef);
            repaint();
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    lastP = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    lastP = null;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        converter.setsHeight(getHeight());
        converter.setsWidth(getWidth());

        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D biG = bi.createGraphics();
        biG.setColor(Color.WHITE);
        biG.fillRect(0, 0, getWidth(), getHeight());

        //LineDrawer ld = new GraphicsLineDrawer(biG);
        //LineDrawer ld = new DDALineDrawer(new GraphicsPixelDrawer(biG));
        LineDrawer ld = new BresenhamLineDrawer(new GraphicsPixelDrawer(biG));
        //LineDrawer ld = new WuLineDrawer(new GraphicsPixelDrawer(biG));

        BezierCurve bc = new BezierCurve(new GraphicsPixelDrawer(biG));
        HermitCurve hc = new HermitCurve(new GraphicsPixelDrawer(biG));
        SplineCurve sc = new SplineCurve(new GraphicsPixelDrawer(biG));

        biG.setColor(Color.BLACK);
        drawCoord(ld, converter);
        drawGrid(ld, converter, biG);

        //это если нужно чтобы было несколько функций на одном экране
        for (int i = 0; i < Monolog.anyFunctions.size(); i++) {
            drawAnyFunction(ld, converter, Monolog.anyFunctions.get(i));
        }

        for (int i = 0; i < Monolog.besierFunctions.size(); i++) {
            drawAnyFunctionBezier(bc, converter, Monolog.besierFunctions.get(i));
        }

        for (int i = 0; i < Monolog.hermitFunctions.size(); i++) {
            drawAnyFunctionHermit(hc, converter, Monolog.hermitFunctions.get(i));
        }

        for (int i = 0; i < Monolog.splineFunctions.size(); i++) {
            drawAnyFunctionSpline(sc, converter, Monolog.splineFunctions.get(i));
        }

        g2d.drawImage(bi, 0, 0, null);
        biG.dispose();
    }

    private static void drawAnyFunction(LineDrawer ld, ScreenConverter sc, Functions f) {
        Color c;
        if (f.defineArg() == 'x') {
            double step = sc.getHeight() / sc.getsHeight();
            double y = sc.getY() - 0 * step;
            double x = f.calculate(y);
            for (int i = 1; i < sc.getsHeight(); i++) {
                c = (colors.get(0));
                double y1 = sc.getY() - i * step;
                double x1 = f.calculate(y1);
                if (x1 > sc.getX() && x1 < sc.getX() + sc.getWidth()) {
                    drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1, y1)), c);
                }
                x = x1;
                y = y1;
            }
        }
        if (f.defineArg() == 'y') {
            double step = sc.getWidth() / sc.getsWidth();
            double x = sc.getX() + 0 * step;
            double y = f.calculate(x);
            for (int i = 1; i < sc.getsWidth(); i++) {
                c = (colors.get(0));
                double x1 = sc.getX() + i * step;
                double y1 = f.calculate(x1);
                if (y1 < sc.getY() && y1 > sc.getY() - sc.getHeight()) {
                    drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1, y1)), c);
                }
                x = x1;
                y = y1;
            }
        }
    }

    private static void drawAnyFunctionBezier(BezierCurve bc, ScreenConverter screenConverter, Functions f) {
        if (f.defineArg() == 'x') {
            double step = screenConverter.getHeight() / screenConverter.getsHeight();
            double y = screenConverter.getY() - 0 * step;
            y = y % 10 > 5 ? ((y / 10) * 10) + 10 : (y / 10) * 10;
            double x = f.calculate(y);
            x = x % 10 > 5 ? ((x / 10) * 10) + 10 : (x / 10) * 10;
            for (int i = 1; i < screenConverter.getsHeight(); i++) {
                double y1 = screenConverter.getY() - i * step;
                double x1 = f.calculate(y1);
                if (x1 > screenConverter.getX() && x1 < screenConverter.getX() + screenConverter.getWidth()) {
                    drawLineBezier(bc, screenConverter, new Line(new RealPoint(x, y), new RealPoint(x1, y1)));
                }
                x = x1;
                y = y1;
            }
        }
        if (f.defineArg() == 'y') {
            double step = screenConverter.getWidth() / screenConverter.getsWidth();
            double x = screenConverter.getX() + 0 * step;
            double y = f.calculate(x);
            for (int i = 1; i < screenConverter.getsWidth(); i++) {
                double x1 = screenConverter.getX() + i * step;
                double y1 = f.calculate(x1);
                if (y1 < screenConverter.getY() && y1 > screenConverter.getY() - screenConverter.getHeight()) {
                    drawLineBezier(bc, screenConverter, new Line(new RealPoint(x, y), new RealPoint(x1, y1)));
                }
                x = x1;
                y = y1;
            }
        }
    }

    private static void drawAnyFunctionHermit(HermitCurve hc, ScreenConverter screenConverter, Functions f) {
        if (f.defineArg() == 'x') {
            double step = screenConverter.getHeight() / screenConverter.getsHeight();
            double y = screenConverter.getY() - 0 * step;
            double x = f.calculate(y);
            for (int i = 1; i < screenConverter.getsHeight(); i++) {
                double y1 = screenConverter.getY() - i * step;
                double x1 = f.calculate(y1);
                if (x1 > screenConverter.getX() && x1 < screenConverter.getX() + screenConverter.getWidth()) {
                    drawLineHermit(hc, screenConverter, new Line(new RealPoint(x, y), new RealPoint(x1, y1)));
                }
                x = x1;
                y = y1;
            }
        }
        if (f.defineArg() == 'y') {
            double step = screenConverter.getWidth() / screenConverter.getsWidth();
            double x = screenConverter.getX() + 0 * step;
            double y = f.calculate(x);
            for (int i = 1; i < screenConverter.getsWidth(); i++) {
                double x1 = screenConverter.getX() + i * step;
                double y1 = f.calculate(x1);
                if (y1 < screenConverter.getY() && y1 > screenConverter.getY() - screenConverter.getHeight()) {
                    drawLineHermit(hc, screenConverter, new Line(new RealPoint(x, y), new RealPoint(x1, y1)));
                }
                x = x1;
                y = y1;
            }
        }
    }

    private static void drawAnyFunctionSpline(SplineCurve splineCurve, ScreenConverter screenConverter, Functions f) {
        if (f.defineArg() == 'x') {
            double step = screenConverter.getHeight() / screenConverter.getsHeight();
            double y = screenConverter.getY() - 0 * step;
            double x = f.calculate(y);
            ArrayList<RealPoint> points = new ArrayList<>();
            for (int i = 1; i < screenConverter.getsHeight(); i++) {
                points.add(new RealPoint(x, y));
                double y1 = screenConverter.getY() - i * step;
                double x1 = f.calculate(y1);
                points.add(new RealPoint(x1, y1));

                if (i % 3 == 0) {
                    points = new ArrayList<RealPoint>(new LinkedHashSet<RealPoint>(points));
                    if (x1 > screenConverter.getX() && x1 < screenConverter.getX() + screenConverter.getWidth()) {
                        drawLineSpline(splineCurve, screenConverter, new Line(new RealPoint(points.get(0).getX(), points.get(0).getY()),
                                        new RealPoint(points.get(1).getX(), points.get(1).getY())),
                                new Line(new RealPoint(points.get(4).getX(), points.get(2).getY()),
                                        new RealPoint(points.get(5).getX(), points.get(5).getY())));
                        points.clear();
                    }
                }
                x = x1;
                y = y1;
            }
        }
        if (f.defineArg() == 'y') {
            double step = screenConverter.getWidth() / screenConverter.getsWidth();
            double x = screenConverter.getX() + 0 * step;
            double y = f.calculate(x);
            ArrayList<RealPoint> points = new ArrayList<>();
            for (int i = 1; i < screenConverter.getsWidth(); i++) {
                points.add(new RealPoint(x, y));
                double x1 = screenConverter.getX() + i * step*10;
                double y1 = f.calculate(x1);
                points.add(new RealPoint(x1, y1));
                if (i % 3 == 0) {
                    points = new ArrayList<RealPoint>(new LinkedHashSet<RealPoint>(points));
                    if (y1 < screenConverter.getY() && y1 > screenConverter.getY() - screenConverter.getHeight()) {
                        drawLineSpline(splineCurve, screenConverter, new Line(new RealPoint(points.get(0).getX(), points.get(0).getY()),
                                        new RealPoint(points.get(1).getX(), points.get(1).getY())),
                                new Line(new RealPoint(points.get(4).getX(), points.get(4).getY()),
                                        new RealPoint(points.get(5).getX(), points.get(5).getY())));
                        points.clear();
                    }
                }
                x = x1;
                y = y1;
            }
        }
    }


    private static void drawCoord(LineDrawer ld, ScreenConverter sc) {
        Color c = Color.BLACK;
        double step = sc.getWidth() / sc.getsWidth();
        double x = sc.getX() + 0 * step;
        double y = 0;
        for (int i = 1; i < sc.getsWidth(); i++) {
            double x1 = sc.getX() + i * step;
            double y1 = 0;
            drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1, y1)), c);
            x = x1;
            y = y1;
        }

        step = sc.getHeight() / sc.getsHeight();
        y = sc.getY() - 0 * step;
        x = 0;
        for (int i = 1; i < sc.getsHeight(); i++) {
            double y1 = sc.getY() - i * step;
            double x1 = 0;
            drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1, y1)), c);
            x = x1;
            y = y1;
        }
    }

    private static void drawGrid(LineDrawer ld, ScreenConverter sc, Graphics2D biG) {

        Color c = new Color(169, 169, 169);
        int step = 1;
        if (sc.getWidth() > 1000) {
            int n = 0;
            while (sc.getWidth() > 500 * Math.pow(2, n)) {
                n++;
            }
            step = 50 * (int) Math.pow(2, n - 1);
        } else if (sc.getWidth() > 500) {
            step = 50;
        } else if (sc.getWidth() > 200) {
            step = 20;
        } else if (sc.getWidth() > 50) {
            step = 10;
        } else if (sc.getWidth() > 20) {
            step = 5;
        }
        for (int i = 0; i < sc.getWidth(); i += step) {
            if (i == 0) {
                RealPoint rp = new RealPoint(0.1 * step, 0.1 * step);
                biG.setColor(Color.BLACK);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
            } else {
                RealPoint rp = new RealPoint(i, 0.1 * step);
                biG.setColor(Color.BLACK);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
                drawLine(ld, sc, new Line(new RealPoint(i, -sc.getHeight()), new RealPoint(i, sc.getHeight() * 2)), c);
            }
        }
        for (int i = 0; i > -sc.getWidth(); i -= step) {
            biG.setColor(Color.BLACK);
            if (i == 0) {
                RealPoint rp = new RealPoint(0.1 * step, 0.1 * step);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
            } else {
                RealPoint rp = new RealPoint(i, 0.1 * step);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
                drawLine(ld, sc, new Line(new RealPoint(i, -sc.getHeight()), new RealPoint(i, sc.getHeight() * 2)), c);
            }
        }
        for (int i = 0; i < sc.getHeight(); i += step) {
            biG.setColor(Color.BLACK);
            if (i != 0) {
                RealPoint rp = new RealPoint(0.1 * step, i);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
                drawLine(ld, sc, new Line(new RealPoint(-sc.getWidth(), i), new RealPoint(sc.getWidth(), i)), c);
            }
        }
        for (int i = 0; i > -sc.getHeight(); i -= step) {
            biG.setColor(Color.BLACK);
            if (i != 0) {
                RealPoint rp = new RealPoint(0.1 * step, i);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
                drawLine(ld, sc, new Line(new RealPoint(-sc.getWidth(), i), new RealPoint(sc.getWidth(), i)), c);
            }
        }
    }


    private static void drawLine(LineDrawer ld, ScreenConverter sc, Line l, Color c) {
        ScreenPoint p1 = sc.r2s(l.getP1());
        ScreenPoint p2 = sc.r2s(l.getP2());
        ld.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), c);
    }

    private static void drawLineBezier(BezierCurve bc, ScreenConverter sc, Line l) {
        ScreenPoint p1 = sc.r2s(l.getP1());
        ScreenPoint p2 = sc.r2s(l.getP2());

        bc.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private static void drawLineHermit(HermitCurve hc, ScreenConverter sc, Line l) {
        ScreenPoint p1 = sc.r2s(l.getP1());
        ScreenPoint p2 = sc.r2s(l.getP2());

        hc.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private static void drawLineSpline(SplineCurve splineCurve, ScreenConverter sc, Line l1, Line l2) {
        ScreenPoint p1 = sc.r2s(l1.getP1());
        ScreenPoint p2 = sc.r2s(l1.getP2());
        ScreenPoint p3 = sc.r2s(l2.getP1());
        ScreenPoint p4 = sc.r2s(l2.getP2());

        splineCurve.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY());
    }

}
