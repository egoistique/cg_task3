
package shapes.types;

import shapes.utils.Coordinate;

/**
 *
 * @author Gomon Sergey, ZOR
 */
public interface IPerspective {
	void applyPerspective(int dx, int dy, int dz);
	Coordinate getPerspectiveLoockPoint();
	void setPerspectiveLoockPoint(Coordinate perspectiveLoockPoint);
}
