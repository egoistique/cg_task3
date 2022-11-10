
package shapes.panels;

import canva.Canva;
import shapes.IGraphicsObject;
import shapes.shapes.*;

/**
 * Фабрика панелей инструментов для фигуры.
 * 
 * @author Gomon Sergey
 */
public class GraphicsObjectPanelFuctory {
	
	public static AbstractGraphicsObjectPanel createGraphicsObjectPanel(
			IGraphicsObject graphicsObject, Canva canva) {
		
		AbstractGraphicsObjectPanel panel = null;
		
		if(graphicsObject instanceof BresenhamLine) {
			panel = new BresenhamLinePanel((BresenhamLine)graphicsObject, canva);
		} if(graphicsObject instanceof WuLine) {
			panel = new WuLinePanel((WuLine)graphicsObject, canva);
		} else if(graphicsObject instanceof HermitCurve) {
			panel = new HermitCurvePanel((HermitCurve)graphicsObject, canva);
		} else if(graphicsObject instanceof BezierCurve) {
			panel = new BezierCurvePanel((BezierCurve)graphicsObject, canva);
		} else if(graphicsObject instanceof SplineCurve) {
			panel = new SplineCurvePanel((SplineCurve)graphicsObject, canva);
		} else if(graphicsObject instanceof Polygon) {
			panel = new PolygonPanel((Polygon)graphicsObject, canva);
		} else if(graphicsObject instanceof Ellipse) {
			panel = new EllipsePanel((Ellipse)graphicsObject, canva);
		} else if(graphicsObject instanceof Parabola) {
			panel = new ParabolaPanel((Parabola)graphicsObject, canva);
		} else if(graphicsObject instanceof Cube) {
			panel = new CubePanel((Cube)graphicsObject, canva);
		}
		
		return panel;
	}
}
