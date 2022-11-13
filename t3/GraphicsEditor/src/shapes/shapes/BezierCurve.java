
package shapes.shapes;
import Jama.Matrix;
import drawers.PixelDrawer;
import shapes.abstracts.AbstractGraphicsObject;
import shapes.utils.Coordinate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BezierCurve extends AbstractGraphicsObject{
	private PixelDrawer pd;

	private static final int DEFAULT_ITERATION_COUNT	= 20;
	private static final int DEFAULT_BEGIN_VECTOR_X		= 0;
	private static final int DEFAULT_BEGIN_VECTOR_Y		= 0;
	private static final int DEFAULT_END_VECTOR_X		= 0;
	private static final int DEFAULT_END_VECTOR_Y		= 0;

	//Матрица для кривой Безъе
	private static final Matrix BEZIER_MATRIX = new Matrix(new double[][] {
			{-1, 3,-3, 1},
			{ 3,-6, 3, 0},
			{-3, 3, 0, 0},
			{ 1, 0, 0, 0}}, 4,4);

	private Coordinate beginPoint; //Начальная точка кривой
	private Coordinate endPoint; //Конечная точка кривой
	private Coordinate beginVector; //Первая опорная точка
	private Coordinate endVector; //Вторая опорная точка

	private int iterationCount;

	private List<Double> tList;
	private List<Double> xList;
	private List<Double> yList;

	public BezierCurve(PixelDrawer pd) {
		this.beginPoint = new Coordinate(2);
		this.endPoint = new Coordinate(2);
		this.beginVector = new Coordinate(null, DEFAULT_BEGIN_VECTOR_X, DEFAULT_BEGIN_VECTOR_Y);
		this.endVector = new Coordinate(null, DEFAULT_END_VECTOR_X, DEFAULT_END_VECTOR_Y);
		this.iterationCount = DEFAULT_ITERATION_COUNT;
		this.pd = pd;
		tList = new ArrayList<Double>();
		xList = new ArrayList<Double>();
		yList = new ArrayList<Double>();
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		beginPoint.set(x1, y2);
		endPoint.set(x2, y2);
		beginVector.set(x1, y1);
		endVector.set(x2, y2);
		//заполняет лист с координатами
		calc();

		//строит точки по координатам из листа х и у
//		for(int i = 0; i < xList.size(); i++){
//			pd.drawPixel((int) (xList.get(i) / 10), (int) (yList.get(i) / 10), Color.BLACK);
//		}
	}

	/**
	 * Метод осуществялет вычисление координат кривой Безъе
	 */
	@Override
	protected void calc() {
		clearCoordinates();
		tList.clear();
		xList.clear();
		yList.clear();

		if(!beginPoint.isCorrect()) {
			return;
		} else if(!endPoint.isCorrect()) {
			addPoint(beginPoint.get(0), beginPoint.get(1));
			return;
		}

		//Вычисление вектора Эрмтовой геометрии
		Matrix hermitGeometryVector = new Matrix(
				new double[][] {
						{beginPoint.get(0), beginPoint.get(1)},
						{beginVector.get(0), beginVector.get(1)},
						{endVector.get(0), endVector.get(1)},
						{endPoint.get(0), endPoint.get(1)}},
				4, 2);

		for(int i = 0; i <= iterationCount; i++) {
			//Вычисление параметра t
			double t = (double)i/iterationCount;
			tList.add(t);
			//Вычисление вектора [t^3 t^2 t 1]
			Matrix tMatrix = new Matrix(new double[][] {{Math.pow(t,3), Math.pow(t,2), Math.pow(t,1), Math.pow(t,0)}}, 1, 4);
			//Вычисление координат точки
			Matrix point = tMatrix.times(BEZIER_MATRIX).times(hermitGeometryVector);
			addPoint((int)point.get(0,0), (int)point.get(0,1));
			xList.add(point.get(0,0));
			yList.add(point.get(0,1));
			//Рисуем точку
			pd.drawPixel((int)point.get(0,0), (int)point.get(0,1), Color.RED);
		}

	}


}
