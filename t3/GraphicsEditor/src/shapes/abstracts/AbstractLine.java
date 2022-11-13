
package shapes.abstracts;

import shapes.utils.Coordinate;


public abstract class AbstractLine extends AbstractGraphicsPrimitive {
    
	private Coordinate beginPoint;
	private Coordinate endPoint;
	
	public AbstractLine() {
		this.beginPoint = new Coordinate(2);
		this.endPoint = new Coordinate(2);
	}

}
