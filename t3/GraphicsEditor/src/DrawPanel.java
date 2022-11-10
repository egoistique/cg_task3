import drawers.BresenhamLineDrawer;
import drawers.GraphicsPixelDrawer;
import drawers.LineDrawer;
import functions.AnyFunctions;
import functions.IFunction;
import shapes.shapes.BezierCurve;
import shapes.shapes.HermitCurve;
import shapes.shapes.SplineCurve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class DrawPanel extends JPanel {
    private final ScreenConverter converter;
    private final Line ox;
    private final Line oy;
    private final Line current;
    private Point lastP;
    private ArrayList<IFunction> functions;
    private ArrayList<Color> colors;
    private Map<String, Double> params;
    private ArrayList<AnyFunctions> anyFunc;
    private Timer timer;

    public DrawPanel() {
        timer = new Timer(20, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();

        params = new HashMap<>();

        functions = new ArrayList<>();
        anyFunc = new ArrayList<>();

        colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.PINK);
        colors.add(Color.BLACK);


        converter = new ScreenConverter(800,600, -2,2, 4, 4);
        ox = new Line(new RealPoint(-converter.getsWidth(),0), new RealPoint(converter.getsWidth(),0));
        oy = new Line(new RealPoint(0,-converter.getsHeight()), new RealPoint(0,converter.getsHeight()));
        current = new Line(new RealPoint(0,0), new RealPoint(0,0));

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(lastP != null){
                    Point curP = e.getPoint();
                    ScreenPoint delta = new ScreenPoint(curP.x - lastP.x,curP.y - lastP.y);
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
        //drawLine(ld,converter,ox);
        //drawLine(ld,converter,oy);
        drawCoord(ld, converter);
        drawMarking(ld, converter, biG);



//        for(int i = 0; i < functions.size(); i++){
//            biG.setColor(colors.get(i%7));
//            if(functions.get(i) instanceof SpecialFunction){
//                drawSpecialFunction(ld, converter, functions.get(i), Monolog.params);
//            } else {
//                drawFunction(ld, converter, functions.get(i), Monolog.params);
//            }
//        }

        //это если нужно чтобы было несколько функций на одном экране
        for (int i = 0; i < Monolog.anyFunctions.size(); i++) {
            biG.setColor(colors.get(i%7));
            drawAnyFunction(ld, converter, Monolog.anyFunctions.get(i));
        }

        for (int i = 0; i < Monolog.besierFunctions.size(); i++) {
            biG.setColor(colors.get(i%7));
            drawAnyFunctionBezier(bc, converter, Monolog.besierFunctions.get(i));
        }

        for (int i = 0; i < Monolog.hermitFunctions.size(); i++) {
            biG.setColor(colors.get(i%7));
            drawAnyFunctionHermit(hc, converter, Monolog.hermitFunctions.get(i));
        }

        for (int i = 0; i < Monolog.splineFunctions.size(); i++) {
            biG.setColor(colors.get(i%7));
            drawAnyFunctionSpline(sc, converter, Monolog.splineFunctions.get(i));
        }



        //drawAnyFunctionBezier(bc, converter, Monolog.anyFunctions.get(Monolog.anyFunctions.size() - 1));



        g2d.drawImage(bi, 0, 0, null);
        biG.dispose();
    }

//    private static void drawFunction(LineDrawer ld, ScreenConverter sc, IFunction f, Map<String, Double> params){
//        double step = sc.getWidth() / sc.getsWidth();
//        double x = sc.getX() + 0 * step;
//        double y = f.compute(x, params);
//        for(int i = 1; i < sc.getsWidth(); i++) {
//            double x1 = sc.getX() + i * step;
//            double y1 = f.compute(x1, params);
//            if (y1 < sc.getY() && y1 > sc.getY() - sc.getHeight()){
//                drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
//            }
//            x = x1;
//            y = y1;
//        }
//    }
//
//    private static void drawSpecialFunction(LineDrawer ld, ScreenConverter sc, IFunction f, Map<String, Double> params){
//        double step = sc.getHeight() / sc.getsHeight();
//        double y = sc.getY() - 0 * step;
//        double x = f.compute(y, params);
//        for(int i = 1; i < sc.getsHeight(); i++) {
//            double y1 = sc.getY() - i * step;
//            double x1 = f.compute(y1, params);
//            if (x1 > sc.getX() && x1 < sc.getX() + sc.getWidth()){
//                drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
//            }
//            x = x1;
//            y = y1;
//        }
//    }

    public void paintBezier(BezierCurve bc){
        drawAnyFunctionBezier(bc, converter, Monolog.anyFunctions.get(Monolog.anyFunctions.size() - 1));
    }




    private static void drawAnyFunction(LineDrawer ld, ScreenConverter sc, AnyFunctions f){
        if(f.defineArg() == 'x'){
            double step = sc.getHeight() / sc.getsHeight();
            double y = sc.getY() - 0 * step;
            double x = f.compute(y);
            for(int i = 1; i < sc.getsHeight(); i++) {
                double y1 = sc.getY() - i * step;
                double x1 = f.compute(y1);
                if (x1 > sc.getX() && x1 < sc.getX() + sc.getWidth()){
                    drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
                }
                x = x1;
                y = y1;
            }
        }
        if(f.defineArg() == 'y'){
            double step = sc.getWidth() / sc.getsWidth();
            double x = sc.getX() + 0 * step;
            double y = f.compute(x);
            for(int i = 1; i < sc.getsWidth(); i++) {
                double x1 = sc.getX() + i * step;
                double y1 = f.compute(x1);
                if (y1 < sc.getY() && y1 > sc.getY() - sc.getHeight()){
                    drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
                }
                x = x1;
                y = y1;
            }
        }
    }

    private static void drawAnyFunctionBezier(BezierCurve bc, ScreenConverter sc, AnyFunctions f){
        if(f.defineArg() == 'x'){
            double step = sc.getHeight() / sc.getsHeight();
            double y = sc.getY() - 0 * step;
            double x = f.compute(y);
            for(int i = 1; i < sc.getsHeight(); i++) {
                double y1 = sc.getY() - i * step;
                double x1 = f.compute(y1);
                if (x1 > sc.getX() && x1 < sc.getX() + sc.getWidth()){
                    drawLineBezier(bc, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
                }
                x = x1;
                y = y1;
            }
        }
        if(f.defineArg() == 'y'){
            double step = sc.getWidth() / sc.getsWidth();
            double x = sc.getX() + 0 * step;
            double y = f.compute(x);
            for(int i = 1; i < sc.getsWidth(); i++) {
                double x1 = sc.getX() + i * step;
                double y1 = f.compute(x1);
                if (y1 < sc.getY() && y1 > sc.getY() - sc.getHeight()){
                    drawLineBezier(bc, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
                }
                x = x1;
                y = y1;
            }
        }
    }

    private static void drawAnyFunctionHermit(HermitCurve hc, ScreenConverter sc, AnyFunctions f){
        if(f.defineArg() == 'x'){
            double step = sc.getHeight() / sc.getsHeight();
            double y = sc.getY() - 0 * step;
            double x = f.compute(y);
            for(int i = 1; i < sc.getsHeight(); i++) {
                double y1 = sc.getY() - i * step;
                double x1 = f.compute(y1);
                if (x1 > sc.getX() && x1 < sc.getX() + sc.getWidth()){
                    drawLineHermit(hc, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
                }
                x = x1;
                y = y1;
            }
        }
        if(f.defineArg() == 'y'){
            double step = sc.getWidth() / sc.getsWidth();
            double x = sc.getX() + 0 * step;
            double y = f.compute(x);
            for(int i = 1; i < sc.getsWidth(); i++) {
                double x1 = sc.getX() + i * step;
                double y1 = f.compute(x1);
                if (y1 < sc.getY() && y1 > sc.getY() - sc.getHeight()){
                    drawLineHermit(hc, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
                }
                x = x1;
                y = y1;
            }
        }
    }

    private static void drawAnyFunctionSpline(SplineCurve splineCurve, ScreenConverter sc, AnyFunctions f){
        if(f.defineArg() == 'x'){
            double step = sc.getHeight() / sc.getsHeight();
            double y = sc.getY() - 0 * step;
            double x = f.compute(y);
            ArrayList<RealPoint> points = new ArrayList<>();
            for(int i = 1; i < sc.getsHeight(); i++) {
                points.add(new RealPoint(x, y));
                double y1 = sc.getY() - i * step;
                double x1 = f.compute(y1);
                points.add(new RealPoint(x1, y1));

                if(i % 3 == 0) {
                    points = new ArrayList<RealPoint>(new LinkedHashSet<RealPoint>(points));
                    if (x1 > sc.getX() && x1 < sc.getX() + sc.getWidth()) {
                        drawLineSpline(splineCurve, sc,new Line(new RealPoint(points.get(0).getX(), points.get(0).getY()),
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
        if(f.defineArg() == 'y'){
            double step = sc.getWidth() / sc.getsWidth();
            double x = sc.getX() + 0 * step;
            double y = f.compute(x);
            ArrayList<RealPoint> points = new ArrayList<>();
            for(int i = 1; i < sc.getsWidth(); i++) {
                points.add(new RealPoint(x, y));
                double x1 = sc.getX() + i * step;
                double y1 = f.compute(x1);
                points.add(new RealPoint(x1, y1));
                if(i % 3 == 0) {
                    points = new ArrayList<RealPoint>(new LinkedHashSet<RealPoint>(points));
                    if (y1 < sc.getY() && y1 > sc.getY() - sc.getHeight()) {
                        drawLineSpline(splineCurve, sc,new Line(new RealPoint(points.get(0).getX(), points.get(0).getY()),
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


    private static void drawCoord(LineDrawer ld, ScreenConverter sc){
        double step = sc.getWidth() / sc.getsWidth();
        double x = sc.getX() + 0 * step;
        double y = 0;
        for(int i = 1; i < sc.getsWidth(); i++) {
            double x1 = sc.getX() + i * step;
            double y1 = 0;
            drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
            x = x1;
            y = y1;
        }

        step = sc.getHeight() / sc.getsHeight();
        y = sc.getY() - 0 * step;
        x = 0;
        for(int i = 1; i < sc.getsHeight(); i++) {
            double y1 = sc.getY() - i * step;
            double x1 = 0;
            drawLine(ld, sc, new Line(new RealPoint(x, y), new RealPoint(x1,y1)));
            x = x1;
            y = y1;
        }
    }

    private static void drawMarking(LineDrawer ld,ScreenConverter sc, Graphics2D biG){
        int step = 1;
        if (sc.getWidth() > 1000) {
            int n = 0;
            while(sc.getWidth() > 500*Math.pow(2, n)){
                n++;
            }
            step = 50*(int)Math.pow(2, n-1);
        }else if (sc.getWidth() > 500) {
            step = 50;
        }else if (sc.getWidth() > 200) {
            step = 20;
        }else if (sc.getWidth() > 50) {
            step = 10;
        }else if (sc.getWidth() > 20) {
            step = 5;
        }
        for (int i = 0; i < sc.getWidth(); i+=step) {
            if (i == 0) {
                RealPoint rp = new RealPoint(0.1*step, 0.1*step);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
            } else {
                RealPoint rp = new RealPoint(i, 0.1 * step);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
                drawLine(ld, sc, new Line(new RealPoint(i, -0.05 * step), new RealPoint(i, 0.05 * step)));
            }
        }
        for (int i = 0; i > -sc.getWidth(); i-=step) {
            if (i == 0) {
                RealPoint rp = new RealPoint(0.1*step, 0.1*step);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
            } else {
                RealPoint rp = new RealPoint(i, 0.1 * step);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
                drawLine(ld, sc, new Line(new RealPoint(i, -0.05 * step), new RealPoint(i, 0.05 * step)));
            }
        }
        for (int i = 0; i < sc.getHeight(); i+=step) {
            if (i != 0) {
                RealPoint rp = new RealPoint(0.1 * step, i);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
                drawLine(ld, sc, new Line(new RealPoint(-0.05 * step, i), new RealPoint(0.05 * step, i)));
            }
        }
        for (int i = 0; i > -sc.getHeight(); i-=step) {
            if (i != 0) {
                RealPoint rp = new RealPoint(0.1 * step, i);
                biG.drawString(String.valueOf(i), (float) sc.r2s(rp).getX(), (float) sc.r2s(rp).getY());
                drawLine(ld, sc, new Line(new RealPoint(-0.05 * step, i), new RealPoint(0.05 * step, i)));
            }
        }
    }


    private static void drawLine(LineDrawer ld, ScreenConverter sc, Line l) {
        ScreenPoint p1 = sc.r2s(l.getP1());
        ScreenPoint p2 = sc.r2s(l.getP2());
        ld.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
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

    public void setParams(Map<String, Double> params) {
        this.params = params;
        repaint();
    }
}
