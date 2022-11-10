
package canva;

import java.awt.*;

/*
 * 
 * Класс рисовальщика. Для каждой фигуры в списке, объект класса Canva передает
 * его в метод draw().
 */
public class CanvaGraphics {
	
    private int widhtPx;
    private int heightPx;
    private int widhtCells;
    private int heightCells;
    private int cellSize;
	private Color color;
	
    private Graphics2D graphics;

    public CanvaGraphics(int widhtPx, int heightPx, 
			int widhtCells, int heightCells, 
			int cellSize, Graphics2D graphics) {
        this.widhtPx = widhtPx;
        this.heightPx = heightPx;
		this.widhtCells = widhtCells;
		this.heightCells = heightCells;
        this.cellSize = cellSize;
        this.graphics = graphics;
		this.color = null;
    }

	public void setColor(Color color) {
		this.color = color;
	}
	
    public void drawPoint(int x, int y) {
		if(x < widhtPx && y < heightPx) {
			drawPoint(x, y, Color.black);
		}
    }

    public void drawPoint(int x, int y, Color c) {
        /*if (x < 0 || y < 0 || x >= widhtCells || y >= heightCells) {
			return;
		}*/
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
