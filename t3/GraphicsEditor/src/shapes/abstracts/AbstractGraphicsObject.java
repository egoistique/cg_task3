
package shapes.abstracts;

import canva.CanvaGraphics;


import shapes.utils.Coordinate;
import shapes.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class AbstractGraphicsObject {
	
	private Coordinates coordinates;
	private List<AbstractLine> lines;
	boolean antialiasing;

	public AbstractGraphicsObject() {
		coordinates = new Coordinates();
		lines = new ArrayList<AbstractLine>();
		antialiasing = true;
	}
	

	public void draw(CanvaGraphics g) {
		for(AbstractLine line: lines) {
			line.draw(g);
		}
	}

	protected void addPoint(int x, int y) {
		boolean pushed = false;
		if(coordinates.isEmpty()) {
			coordinates.addPoint(new Coordinate(null, x, y));
			pushed = true;
		}
		if(!pushed) {
			coordinates.addPoint(new Coordinate(null, x, y));
		}
	}
	
	protected void clearCoordinates() {
		coordinates.clear();
		lines.clear();
	}
	
	protected abstract void calc();
}
