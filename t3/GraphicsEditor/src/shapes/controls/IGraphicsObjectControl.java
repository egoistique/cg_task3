
package shapes.controls;

import java.awt.*;

/**
 * @author Gomon Sergey
 */
public interface IGraphicsObjectControl {
	boolean processMousePress(int x, int y, int cellSize);
	boolean processMouseRelease(int x, int y, int cellSize);
	boolean processMouseMove(int x, int y, int cellSize);
	boolean processMouseDoubleClick(int x, int y, int cellSize);
	String getName();
	void draw(Graphics2D g, int cellSize);
}
