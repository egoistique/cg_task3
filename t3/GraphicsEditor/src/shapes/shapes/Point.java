
package shapes.shapes;

import canva.CanvaGraphics;

import java.awt.*;


public class Point {
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


	public void draw(CanvaGraphics g) {
		g.drawPoint(x, y, color);
	}

}
