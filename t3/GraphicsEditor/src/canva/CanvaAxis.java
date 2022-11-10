
package canva;

import javax.swing.*;
import java.awt.*;

/**
 * @author	Gomon Serge
 * 
 * Ось координат. Это элемен JScrollPane, устанавливаются через
 * setColumnHeaderView() для HORIZONTAL и setRowHeaderView() для
 * VERTICAL
 */
public class CanvaAxis extends JComponent {
	
    public enum Type {
        HORIZONTAL, VERTICAL
    }

    private final int	THICKNESS			= 20;
    private final int	VISIBLE_COORD_SIZE	= 20;
    private final int	VISIBLE_GRID_SIZE	= 2;
    private final int	FONT_SIZE			= 9;
    private final int	STROKE_SIZE			= 5;
    private final Color BACKGROUND_COLOR	= Color.white;
    private final Color LINE_COLOR			= Color.black;


    private Type type;
    private int cellSize;
    private int length;

    public CanvaAxis(Type type) {
        this.type = type;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public void setHeight(int ph) {
        length = ph;
        setPreferredSize(new Dimension(THICKNESS, ph * cellSize));
    }

    public void setWidth(int pw) {
        length = pw;
        setPreferredSize(new Dimension(pw * cellSize, THICKNESS));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        if (type == Type.HORIZONTAL) {
			drawHorizontal(g2d);
		} else {
			drawVertical(g2d);
		}
    }

	protected void drawBackground(Graphics2D g) {
        Rectangle drawHere = g.getClipBounds();
        Color oldColor = g.getColor();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(drawHere.x, drawHere.y, drawHere.width, drawHere.height);
        g.setColor(oldColor);
    }
	
    protected void drawHorizontal(Graphics2D g) {
        Color oldColor = g.getColor();
        Stroke oldStroke = g.getStroke();
        Font oldFont = g.getFont();

        g.setColor(LINE_COLOR);
        g.setFont(new Font("SansSerif", Font.PLAIN, FONT_SIZE));

        int xBegin = 0;
        int xEnd = length * cellSize;
        int yBegin = 0;
        int yEnd = THICKNESS;

        //Рисуем саму ось
        g.setStroke(new BasicStroke(5));
        g.drawLine(xBegin, yEnd, xEnd, yEnd);
        g.setStroke(oldStroke);

        if (cellSize < VISIBLE_GRID_SIZE) {
            g.setColor(oldColor);
            g.setFont(oldFont);
            return;
        }

        //Определяем координаты первой и последней черты на оси
        int begin = 0;
        int end = cellSize * length;

        //Рисуем штрихи на координатной сетке
        if (cellSize >= VISIBLE_COORD_SIZE) {
            for (int i = begin; i < end; i += cellSize) {
                g.drawLine(i, yEnd, i, yEnd - 5);
                char[] num = Integer.toString(i / cellSize).toCharArray();
                g.drawChars(num, 0, num.length, i + 3, yEnd - STROKE_SIZE);
            }
            g.drawLine(end, yEnd, end, yEnd - 5);
        } else {
            for (int i = begin; i <= end; i += cellSize) {
                g.drawLine(i, yEnd, i, yEnd - STROKE_SIZE);
            }
        }

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    protected void drawVertical(Graphics2D g) {
        Color oldColor = g.getColor();
        Stroke oldStroke = g.getStroke();
        Font oldFont = g.getFont();

        g.setColor(LINE_COLOR);
        g.setFont(new Font("SansSerif", Font.PLAIN, FONT_SIZE));

        int xBegin = 0;
        int xEnd = THICKNESS;
        int yBegin = 0;
        int yEnd = length * cellSize;

        //Рисуем саму ось
        g.setStroke(new BasicStroke(5));
        g.drawLine(xEnd, yBegin, xEnd, yEnd);
        g.setStroke(oldStroke);

        if (cellSize < VISIBLE_GRID_SIZE) {
            g.setColor(oldColor);
            g.setFont(oldFont);
            return;
        }

        //Определяем координаты первой и последней черты на оси
        int begin = 0;
        int end = length * cellSize;

        //Рисуем штрихи
        if (cellSize >= VISIBLE_COORD_SIZE) {
            for (int i = begin; i < end; i += cellSize) {
                g.drawLine(xEnd - STROKE_SIZE, i, xEnd, i);
                char[] num = Integer.toString(i / cellSize).toCharArray();
                g.drawChars(num, 0, num.length, xEnd - STROKE_SIZE - 13, i + 12);
            }
            g.drawLine(xEnd - STROKE_SIZE, end, xEnd, end);
        } else {
            for (int i = begin; i <= end; i += cellSize) {
                g.drawLine(xEnd - STROKE_SIZE, i, xEnd, i);
            }
        }

        g.setColor(oldColor);
        g.setFont(oldFont);
    }
	
}
