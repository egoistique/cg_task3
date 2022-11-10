/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes.controls;

import canva.CanvaGraphics;
import java.awt.Color;
import java.awt.Graphics2D;
import shapes.shapes.Polygon;
import shapes.types.ICyrusBeck;
import shapes.utils.Coordinate;
import shapes.utils.Coordinates;

/**
 *
 * @author Sergey
 */
public class CyrusBeckControl implements IGraphicsObjectControl {

	private static final String NAME = "Метод Кируса-Бека";	//Имя в строке состояния
	private static final float ALPHA = 0.4f;
	
	private Coordinates screen;
	private Coordinate prevMousePos;
	private boolean complete;
	private boolean mousePressed;
	private ICyrusBeck object;
	
	public CyrusBeckControl(ICyrusBeck object) {
		this.complete = false;
		this.mousePressed = false;
		this.object = object;
		this.screen = new Coordinates();
	}
	
	@Override
	public boolean processMousePress(int x, int y, int cellSize) {
		mousePressed = true;
		prevMousePos = new Coordinate(null, x, y);
		if(!complete) {
			screen.addPoint(new Coordinate(null, (double)x/cellSize, (double)y/cellSize));
			if(!isConvex(screen)) {
				screen.removeTop();
			}
			return true;
		} else if(x > screen.getMin(0)*cellSize && x < screen.getMax(0)*cellSize
			&& y > screen.getMin(1)*cellSize && y < screen.getMax(1)*cellSize) {
			return true;
		}
		object.setCBTrunckateScreen(null);
		return false;
	}

	@Override
	public boolean processMouseRelease(int x, int y, int cellSize) {
		mousePressed = false;
		return false;
	}

	@Override
	public boolean processMouseMove(int x, int y, int cellSize) {
		if(mousePressed && complete) {
			Coordinate currMousePos = new Coordinate(null, x, y);
			Coordinate shift = currMousePos.minus(prevMousePos);
			for(int i = 0; i < screen.size(); i++) {
				screen.getNoClone(i).set(screen.getNoClone(i).get(0)+shift.get(0)/cellSize,
						screen.getNoClone(i).get(1)+shift.get(1)/cellSize);
			}
			if(shift.get(0)/cellSize != 0 || shift.get(1)/cellSize != 0) {
				prevMousePos = currMousePos;
			}
		}
		return false;
	}

	@Override
	public boolean processMouseDoubleClick(int x, int y, int cellSize) {
		if(!complete) {
			complete = true;
			object.setCBTrunckateScreen(screen);
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void draw(Graphics2D g, int cellSize) {
		
		CanvaGraphics gg = new CanvaGraphics(
				1000, 1000, 1000, 1000, cellSize, g);
		
		gg.setColor(Color.BLUE);
		Polygon p = new Polygon();
		p.setAntialiasing(false);
		
		for(int i = 0; i < screen.size(); i++) {
			p.processMousePress(
					screen.get(i).get(0), 
					screen.get(i).get(1));
		}
		
		if(complete) {
			p.processMouseDoubleClick(screen.get(0).get(0), screen.get(0).get(1));
		}
		
		p.draw(gg);
	}
	
	private boolean isConvex(Coordinates shape) {
		
		if(shape.size() < 4) {
			return true;
		}

		Coordinate lastVector = shape.get(0).minus(shape.get(shape.size()-1));
		Coordinate prevVector = lastVector;
		
		int nPrevSign = 0;
		int nCurrSign = 0;
		
		for(int i = 0; i < shape.size(); i++) {
			
			Coordinate currVector = shape.get((i+1)%shape.size()).minus(shape.get(i));
			nCurrSign = currVector.get(1)*prevVector.get(0)
					- currVector.get(0)*prevVector.get(1);
			
			if(Math.signum(nCurrSign) == Math.signum(nPrevSign) 
					|| nCurrSign == 0 || nPrevSign == 0) {
				nPrevSign = nCurrSign != 0 ? nCurrSign : nPrevSign;
				nPrevSign = nCurrSign;
				prevVector = currVector;
			} else {
				return false;
			}
		}	
		
		return true;
	}
}
