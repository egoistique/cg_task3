
package canva;

import java.awt.*;

/*
 * 
 * Класс рисовальщика
 */
public class CanvaGraphics {

    private int cellSize;
	private Color color;
	
    private Graphics2D graphics;

    public CanvaGraphics(int cellSize, Graphics2D graphics) {
        this.cellSize = cellSize;
        this.graphics = graphics;
		this.color = null;
    }

	public void setColor(Color color) {
		this.color = color;
	}

    public void drawPoint(int x, int y, Color c) {
        Color oldColor = graphics.getColor();
		if(color == null) {
			graphics.setColor(c);
		} else {
			graphics.setColor(color);
		}
        graphics.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
        graphics.setColor(oldColor);
    }
}
