
package shapes.controls;

import shapes.types.IPointMovable;
import shapes.utils.Coordinate;

import java.awt.*;
import java.util.List;

/**
 * 
 * 
 * @author Gomon Sergey
 */
public class PointsMoveControl implements IGraphicsObjectControl {

	private static final String NAME = "Перемещение точек";
	private static final int DEFAULT_MARKER_SIZE = 30;
	private static final float ALPHA = 0.4f;
	
	private IPointMovable object;
	private List<Coordinate> points;
	Coordinate currPoint;
	private boolean pointSelected;

	public PointsMoveControl(IPointMovable object) {
		this.object = object;
		this.pointSelected = false;
		this.points = object.getControlPoints();
		
	}
	
	@Override
	public boolean processMousePress(int x, int y, int cellSize) {
		this.points = object.getControlPoints();
		for(Coordinate point: points) {
			int leftX = getTopLerftCornerCoordX(point.get(0), cellSize);
			int topY = getTopLerftCornerCoordY(point.get(1), cellSize);
			if((x > leftX && x < leftX + DEFAULT_MARKER_SIZE)
					&& (y > topY && y < topY + DEFAULT_MARKER_SIZE)) {
				pointSelected = true;
				currPoint = point;
				return true;
			}
		}
		pointSelected = false;
		return false;
	}

	@Override
	public boolean processMouseRelease(int x, int y, int cellSize) {
		pointSelected = false;
		object.setControlPoints(points);
		return true;
	}

	@Override
	public boolean processMouseMove(int x, int y, int cellSize) {
		if(pointSelected) {
			currPoint.set(x/cellSize, y/cellSize);
			object.setControlPoints(points);
		}
		return true;
	}
	
	@Override
	public boolean processMouseDoubleClick(int x, int y, int cellSize) {
		return true;
	}	
	
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public void draw(Graphics2D g, int cellSize) {
		
		Color oldColor = g.getColor();
		Stroke oldStroke = g.getStroke();
		
		g.setColor(Color.ORANGE);
		g.setStroke(new BasicStroke(2));		
		
		for(Coordinate point: points) {
			g.setComposite(AlphaComposite
					.getInstance(AlphaComposite.SRC_OVER, 1f));			
			g.drawRect(
				getTopLerftCornerCoordX(point.get(0), cellSize), 
				getTopLerftCornerCoordY(point.get(1), cellSize), 
				DEFAULT_MARKER_SIZE, DEFAULT_MARKER_SIZE);
			g.setComposite(AlphaComposite
					.getInstance(AlphaComposite.SRC_OVER, ALPHA));			
			g.fillRect(
				getTopLerftCornerCoordX(point.get(0), cellSize), 
				getTopLerftCornerCoordY(point.get(1), cellSize), 
				DEFAULT_MARKER_SIZE, DEFAULT_MARKER_SIZE);
		}
		
		g.setColor(oldColor);
		g.setStroke(oldStroke);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));		
	}
	
	private int getTopLerftCornerCoordX(int cellX, int cellSize) {
		return cellX*cellSize - DEFAULT_MARKER_SIZE/2 + cellSize/2;
	}
	
	private int getTopLerftCornerCoordY(int cellY, int cellSize) {
		return cellY*cellSize - DEFAULT_MARKER_SIZE/2 + cellSize/2;
	}
}
