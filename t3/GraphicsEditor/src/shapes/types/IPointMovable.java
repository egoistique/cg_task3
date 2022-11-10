
package shapes.types;

import shapes.utils.Coordinate;

import java.util.List;

/**
 * @author Sergey
 */
public interface IPointMovable {
	List<Coordinate> getControlPoints();
	void setControlPoints(List<Coordinate> points);	
}
