
package shapes.abstractshapes;

import canva.CanvaGraphics;
import shapes.IGraphicsObject;
import shapes.shapes.BresenhamLine;
import shapes.shapes.WuLine;
import shapes.utils.Coordinate;
import shapes.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class AbstractGraphicsObject implements IGraphicsObject {
	
	private Coordinates coordinates;
	private List<AbstractLine> lines;
	boolean antialiasing;

	public AbstractGraphicsObject() {
		coordinates = new Coordinates();
		lines = new ArrayList<AbstractLine>();
		antialiasing = true;
	}
	
	@Override
	public void draw(CanvaGraphics g) {
		for(AbstractLine line: lines) {
			line.draw(g);
		}
	}
	
	public boolean getAntialiasing() {
		return antialiasing;
	}	
	
	public void setAntialiasing(boolean antialiasing) {
		this.antialiasing = antialiasing;
		calc();
	}
	
	public boolean isAntialiasing() {
		return antialiasing;
	}
	
	protected void addPoint(int x, int y) {
		
		boolean pushed = false;
		if(coordinates.isEmpty()) {
			coordinates.addPoint(new Coordinate(null, x, y));
			pushed = true;
		}
		if(!coordinates.isEmpty()) {
			if(antialiasing) {
				AbstractLine line = new WuLine();
				line.processMousePress(coordinates.getTop().get(0), 
						coordinates.getTop().get(1));
				line.processMousePress((int)(x), (int)(y));				
				lines.add(line);				
			} else {
				AbstractLine line = new BresenhamLine();
				line.processMousePress(coordinates.getTop().get(0), 
						coordinates.getTop().get(1));
				line.processMousePress((int)(x), (int)(y));				
				lines.add(line);
			}
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
