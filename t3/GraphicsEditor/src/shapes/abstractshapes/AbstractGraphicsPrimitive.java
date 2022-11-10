
package shapes.abstractshapes;

import canva.CanvaGraphics;
import shapes.IGraphicsObject;
import shapes.shapes.Point;
import shapes.utils.Coordinate;
import shapes.utils.Coordinates;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gomon Sergey
 * 
 * Базовый класс для графических примитивов. Под примтивом пониается
 * графическйи объект, который рисуется по некоторуму алгоритму, и
 * не состаит из других графических объектов, кроме точек.
 * 
 * @see Point
 */
public abstract class AbstractGraphicsPrimitive implements IGraphicsObject {
	
	private List<Point> points;
	private Coordinates coordinates;
	
	public AbstractGraphicsPrimitive() {
		points = new ArrayList<Point>();
		coordinates = new Coordinates();
	}
	
	public List<Integer> getX() {
		return coordinates.getProjection(0);
	}
	
	public List<Integer> getY() {
		return coordinates.getProjection(1);
	}
	
	public int getPointsCount() {
		return points.size();
	}
	
	@Override
	public void draw(CanvaGraphics g) {
		for(Point point: points) {
			point.draw(g);
		}
	}
	
	protected void clear() {
		points.clear();
		coordinates.clear();
	}
	
	protected void plot(int x, int y, Color c) {
		points.add(new Point(x, y, c));
		coordinates.addPoint(new Coordinate(null, x, y));
	}	
	
	protected void plot(int x, int y) {
		plot(x, y, Color.black);
	}

}
