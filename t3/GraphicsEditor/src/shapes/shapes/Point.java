
package shapes.shapes;

import canva.CanvaGraphics;
import shapes.IGraphicsObject;

import java.awt.*;

/**
 * 
 * Класс точки, которая отображается на экране (не путать с точкой, координаты
 * которой используются при вычислениях)
 */
public class Point implements IGraphicsObject {
	public int x;
	public int y;
	private Color color;

	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}


    @Override
	public boolean processMousePress(int x, int y) {
		this.x = x;
		this.y = y;
		return true;
	}

	
	@Override
	public void draw(CanvaGraphics g) {
		g.drawPoint(x, y, color);
	}


    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
