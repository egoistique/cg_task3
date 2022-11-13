
package shapes.curves;

import shapes.utils.Coordinate;
import shapes.utils.Coordinates;

public abstract class Curve {
	
	private Coordinates coordinates;

	public Curve() {
		coordinates = new Coordinates();
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
	}
	
	protected abstract void calc();
}
