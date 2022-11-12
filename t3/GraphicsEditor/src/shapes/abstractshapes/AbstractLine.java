
package shapes.abstractshapes;


import shapes.utils.Coordinate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Базовый класс для прямых, которые рисуются разными алгоритмами.
 */
public abstract class AbstractLine extends AbstractGraphicsPrimitive {
    
	private Coordinate beginPoint;
	private Coordinate endPoint;
	
	public AbstractLine() {
		this.beginPoint = new Coordinate(2);
		this.endPoint = new Coordinate(2);
	}
	
	protected abstract void calc();
	

	
	protected Coordinate getBeginPoint() {
		return beginPoint;
	}
	
	protected Coordinate getEndPoint() {
		return endPoint;
	}
	

	

	@Override
    public boolean processMousePress(int x, int y) {
		if(!beginPoint.isCorrect()) {
			beginPoint.set(x, y);
		} else {
			endPoint.set(x, y);
		}
		calc();
		return true;
    }
	

	
    protected void plot(int x, int y, boolean xLarger, Color c) {
		if (xLarger) {
			plot(x, y, c);
		} else {
			plot(y, x, c);
		}
    }	
	
    protected int getSign(int x) {
		return x > 0 ? 1 : (x == 0 ? 0 : -1);
    }	
	
    protected boolean plotDirectLine(int x0, int y0, int x1, int y1) {
        if (x0 == x1) {
            plotVerticalLine(y0, y1, x0);
            return true;
        } else if (y0 == y1) {
            plotHorizontalLine(x0, x1, y0);
            return true;
        } else if (Math.abs(x1 - x0) == Math.abs(y1 - y0)) {
            plot45AngleLine(x0, y0, x1, y1);
            return true;
        }
        return false;
    }

    private void plotHorizontalLine(int xBegin, int xEnd, int y) {
        clear();

        if (xBegin > xEnd) {
            xBegin += xEnd;
            xEnd = xBegin - xEnd;
            xBegin = xBegin - xEnd;
        }

        for (int x = xBegin; x <= xEnd; x++) {
            plot(x, y);
        }
    }

    private void plotVerticalLine(int yBegin, int yEnd, int x) {
        clear();

        if (yBegin > yEnd) {
            yBegin += yEnd;
            yEnd = yBegin - yEnd;
            yBegin = yBegin - yEnd;
        }

        for (int y = yBegin; y <= yEnd; y++) {
            plot(x, y);
        }
    }

    private void plot45AngleLine(int xBegin, int yBegin, int xEnd, int yEnd) {
        clear();

        int xInc = (xEnd - xBegin) / Math.abs(xEnd - xBegin);
        int yInc = (yEnd - yBegin) / Math.abs(yEnd - yBegin);

        for (int x = xBegin, y = yBegin; x != xEnd; x += xInc, y += yInc) {
            plot(x, y);
        }
		
        plot(xEnd, yEnd);
    }
}
