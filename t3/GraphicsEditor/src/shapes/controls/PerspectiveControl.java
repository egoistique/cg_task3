
package shapes.controls;

import shapes.types.IPerspective;
import shapes.utils.Coordinate;

import java.awt.*;

/**
 *
 * @author Sergey
 */
public class PerspectiveControl implements IGraphicsObjectControl {

	private static final String NAME = "Перспектива";
	private static final int DEFAULT_MARKER_SIZE = 15;
	private static final float ALPHA = 0.4f;
	
	private boolean pressed;
	private IPerspective object;
	
	public PerspectiveControl(IPerspective object) {
		this.object = object;
		this.pressed = false;
	}
	
	@Override
	public boolean processMousePress(int x, int y, int cellSize) {
		Coordinate point = object.getPerspectiveLoockPoint();
		if(x > point.get(0)*cellSize - DEFAULT_MARKER_SIZE/2
				&& x < point.get(0)*cellSize + DEFAULT_MARKER_SIZE/2
				&& y > point.get(1)*cellSize - DEFAULT_MARKER_SIZE/2
				&& y < point.get(1)*cellSize + DEFAULT_MARKER_SIZE/2) {
			pressed = true;
			int z = object.getPerspectiveLoockPoint().get(2);
			object.setPerspectiveLoockPoint(new Coordinate(null, x/cellSize, y/cellSize, z));		
			return true;
		}
		return false;
	}

	@Override
	public boolean processMouseRelease(int x, int y, int cellSize) {
		pressed = false;
		return true;
	}

	@Override
	public boolean processMouseMove(int x, int y, int cellSize) {
		if(pressed) {
			int z = object.getPerspectiveLoockPoint().get(2);
			object.setPerspectiveLoockPoint(new Coordinate(null, x/cellSize, y/cellSize, z));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean processMouseDoubleClick(int x, int y, int cellSize) {
		return false;
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
		
		Coordinate point = object.getPerspectiveLoockPoint();
		
		g.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, 1f));			
		g.drawRect(
			point.get(0)*cellSize - DEFAULT_MARKER_SIZE/2,
			point.get(1)*cellSize - DEFAULT_MARKER_SIZE/2,
			DEFAULT_MARKER_SIZE, DEFAULT_MARKER_SIZE);
		g.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, ALPHA));			
		g.fillRect(
			point.get(0)*cellSize - DEFAULT_MARKER_SIZE/2,
			point.get(1)*cellSize - DEFAULT_MARKER_SIZE/2,
			DEFAULT_MARKER_SIZE, DEFAULT_MARKER_SIZE);
		
		g.setColor(oldColor);
		g.setStroke(oldStroke);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
}
