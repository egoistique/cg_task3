/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes.controls;

import Jama.Matrix;
import shapes.types.IAffineTransformed;
import shapes.utils.Coordinate;

import java.awt.*;

/**
 *
 * @author Sergey
 */
public class MoveControl implements IGraphicsObjectControl {

	private static final String NAME = "Перемещение";
	private static final float ALPHA = 0.4f;
	private static final int MARGIN = 10;

	private IAffineTransformed object;
	private boolean mousePressed;
	private Coordinate mousePressPos;
	
	public MoveControl(IAffineTransformed object) {
		this.object = object;
	}
	
	@Override
	public boolean processMousePress(int x, int y, int cellSize) {
		int recLeftX = object.getPoints().getMin(0) * cellSize - MARGIN;
		int recRightX = recLeftX + (object.getPoints().getMax(0) - object.getPoints().getMin(0) + 1) * cellSize + 2*MARGIN;
		int recTopY = object.getPoints().getMin(1) * cellSize - MARGIN;
		int recBottomY = recTopY + (object.getPoints().getMax(1) - object.getPoints().getMin(1) + 1) * cellSize + 2*MARGIN;
		if(x > recLeftX && x < recRightX && y > recTopY && y < recBottomY) {
			mousePressed = true;
			mousePressPos = new Coordinate(null, x/cellSize, y/cellSize);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean processMouseRelease(int x, int y, int cellSize) {
		mousePressed = false;
		return true;
	}

	@Override
	public boolean processMouseMove(int x, int y, int cellSize) {
		if(mousePressed) {
			int dx = x/cellSize - mousePressPos.get(0);
			int dy = y/cellSize - mousePressPos.get(1);
			Matrix transfirmMatrix = new Matrix(new double[][] {
				 {1,0,0},
				 {0,1,0},
				 {dx,dy,1}}, 3,3);
			object.applyAffine(transfirmMatrix);
			mousePressPos = new Coordinate(null, x/cellSize, y/cellSize);
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
		
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(2));		
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g.drawRect(
			object.getPoints().getMin(0) * cellSize - MARGIN, 
			object.getPoints().getMin(1) * cellSize - MARGIN, 
			(object.getPoints().getMax(0) - object.getPoints().getMin(0) + 1) * cellSize + 2*MARGIN, 
			(object.getPoints().getMax(1) - object.getPoints().getMin(1) + 1) * cellSize + 2*MARGIN);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ALPHA));			
		g.fillRect(
			object.getPoints().getMin(0) * cellSize - MARGIN, 
			object.getPoints().getMin(1) * cellSize - MARGIN, 
			(object.getPoints().getMax(0) - object.getPoints().getMin(0) + 1) * cellSize + 2*MARGIN, 
			(object.getPoints().getMax(1) - object.getPoints().getMin(1) + 1) * cellSize + 2*MARGIN);	
		
		g.setColor(oldColor);
		g.setStroke(oldStroke);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));		
	}
}
