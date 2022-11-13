
package shapes.abstracts;

import canva.CanvaGraphics;
import shapes.shapes.Point;
import shapes.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraphicsPrimitive {
	
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

	

	public void draw(CanvaGraphics g) {
		for(Point point: points) {
			point.draw(g);
		}
	}
}
