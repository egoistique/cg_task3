
package shapes.shapes;

import canva.CanvaGraphics;
import shapes.IGraphicsObject;

import java.awt.*;

/**
 * @author Gomon Sergey
 * 
 * Класс точки, которая отображается на экране (не путать с точкой, координаты
 * которой используются при вычислениях)
 */
public class Point implements IGraphicsObject {
    int id;
	public int x;
	public int y;
	public int z;
	private Color color;

	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public Point(Number x, Number y, int id) {
		this.x = x.intValue();
		this.y = y.intValue();
		this.id = id;
	}

	public Point(int x, int y) {
		this(x, y, Color.black);
	}

    public Point(int x, int y, int z) {
        this(x, y, Color.black);
        this.z = z;
    }
	public Point() {
		this.x = -1;
		this.y = -1;
		this.color = Color.black;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

    public int getZ() {
        return z;
    }

    @Override
	public boolean processMousePress(int x, int y) {
		this.x = x;
		this.y = y;
		return true;
	}

	@Override
	public boolean processMouseRelease(int x, int y) {
		this.x = x;
		this.y = y;
		return true;
	}

	@Override
	public boolean processMouseMove(int x, int y) {
		this.x = x;
		this.y = y;
		return true;
	}	
	
	@Override
	public boolean processMouseDoubleClick(int x, int y) {
		this.x = x;
		this.y = y;
		return true;
	}	
	
	@Override
	public void draw(CanvaGraphics g) {
		g.drawPoint(x, y, color);
	}

	@Override
	public boolean isComplete() {
		if(x >= 0 && y >= 0) {
			return true;
		} else {
			return false;
		}
	}

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
