package shapes.shapes;

import Jama.Matrix;
import canva.CanvaGraphics;
import drawers.PixelDrawer;
import shapes.abstractshapes.AbstractGraphicsObject;
import shapes.utils.Coordinate;
import shapes.utils.Coordinates;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SplineCurve extends AbstractGraphicsObject {
    private PixelDrawer pd;

    private static final int DEFAULT_ITERATION_COUNT = 20;

    //Матрица для сплайна
    private static final Matrix SPLINE_MATRIX = new Matrix(new double[][]{
            {-1, 3, -3, 1},
            {3, -6, 3, 0},
            {-3, 0, 3, 0},
            {1, 4, 1, 0}}, 4, 4);

    //Точки, которые апроксимируются сплайном
    private Coordinates points;
    private int iterationCount;
    boolean complete;

    private List<Double> tList;
    private List<Double> xList;
    private List<Double> yList;

    public SplineCurve(PixelDrawer pd) {
        this.points = new Coordinates();
        this.iterationCount = DEFAULT_ITERATION_COUNT;
        this.complete = false;
        this.pd = pd;
        tList = new ArrayList<Double>();
        xList = new ArrayList<Double>();
        yList = new ArrayList<Double>();
    }

    public void drawLine(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Coordinate coordinate1 = new Coordinate(null, x1, y1);
        points.addPoint(coordinate1);

        Coordinate coordinate2 = new Coordinate(null, x2, y2);
        points.addPoint(coordinate2);

        Coordinate coordinate3 = new Coordinate(null, x3, y3);
        points.addPoint(coordinate3);

        Coordinate coordinate4 = new Coordinate(null, x4, y4);
        points.addPoint(coordinate4);

        //заполняет лист с координатами
        calc();

        //строит точки по координатам из листа х и у
//		for(int i = 0; i < xList.size(); i++){
//			pd.drawPixel((int) (xList.get(i) / 10), (int) (yList.get(i) / 10), Color.BLACK);
//		}
    }

    @Override
    public boolean processMousePress(int x, int y) {
        if (!complete) {
            Coordinate coordinate = new Coordinate(null, x, y);
            points.addPoint(coordinate);
            calc();
            return true;
        } else {
            return false;
        }
    }


    /**
     * Метод осуществляет вычисление координат кривой
     */
    @Override
    protected void calc() {

        clearCoordinates();
        tList.clear();
        xList.clear();
        yList.clear();

        if (points.size() < 4) {
            return;
        }

        //Кривая разбивается на отрезки кривой между соседними точками
        for (int j = 1; j < points.size() - 2; j++) {

            //Вектор Эрмитовой геометрии
            Matrix hermitGeometryVector = new Matrix(
                    new double[][]{
                            {points.get(j - 1).get(0), points.get(j - 1).get(1)},
                            {points.get(j).get(0), points.get(j).get(1)},
                            {points.get(j + 1).get(0), points.get(j + 1).get(1)},
                            {points.get(j + 2).get(0), points.get(j + 2).get(1)}},
                    4, 2);

            for (int i = 0; i <= iterationCount; i++) {
                //Значение параметра t
                double t = (double) i / iterationCount;
                tList.add(t);
                //Вектор [t^3 t^2 t 1]
                Matrix tMatrix = new Matrix(new double[][]{{Math.pow(t, 3), Math.pow(t, 2), Math.pow(t, 1), Math.pow(t, 0)}}, 1, 4);
                //Координаты точки
                Matrix point = tMatrix.times(SPLINE_MATRIX).times(hermitGeometryVector).times(1f / 6f);
                addPoint((int) point.get(0, 0), (int) point.get(0, 1));
                xList.add(point.get(0, 0));
                yList.add(point.get(0, 1));
                //Рисуем точку
                pd.drawPixel((int) point.get(0, 0), (int) point.get(0, 1), Color.PINK);

            }
        }

    }
}
