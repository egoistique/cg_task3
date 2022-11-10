
package shapes.shapes;

import shapes.abstractshapes.AbstractGraphicsPrimitive;
import shapes.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gomon Sergey, ZOR
 */
public class Ellipse extends AbstractGraphicsPrimitive {

	private static final double EPSILON = 0.000000001;
	
	private Coordinate center;
	private int a;
	private int b;
	private boolean complete;
	
	public List<String> pxs;
	public List<Integer> xs;
	public List<Integer> ys;
	public List<Integer> Dis;
	public List<Integer> ds;
	
	public Ellipse() {
		this.complete = false;
		this.center = null;
		this.pxs = new ArrayList<String>();
		this.xs = new ArrayList<Integer>();
		this.ys = new ArrayList<Integer>();
		this.Dis = new ArrayList<Integer>();
		this.ds = new ArrayList<Integer>();
		this.a = 0;
		this.b = 0;
	}
	
	@Override
	public boolean processMousePress(int x, int y) {
		if(!complete) {
			center = new Coordinate(null, x, y);
			calc();
			return true;
		}
		return false;
	}

	@Override
	public boolean processMouseRelease(int x, int y) {
		if(!complete) {
			complete = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean processMouseMove(int x, int y) {
		if(!complete) {
			a = Math.abs(center.get(0) - x);
			b = Math.abs(center.get(1) - y);
			calc();
			return true;
		}
		return false;
	}

	@Override
	public boolean processMouseDoubleClick(int x, int y) {
		return false;
	}

	@Override
	public boolean isComplete() {
		return complete;
	}
	
	public void setAB(int a, int b) {
		this.a = a;
		this.b = b;
		calc();
	}
	
	public int getA() {
		return a;
	}
	
	public int getB() {
		return b;
	}
	
    protected void calc() {
		
		if(center == null) {
			return;
		}
		
		clear();
		xs.clear();
		ys.clear();
		ds.clear();
		Dis.clear();
		pxs.clear();
		
		int x = 0;
		int y = b;
		
		if(a == 0 || b == 0) {
			for(int i = 0; i <= b + a; i++) {
				plot(center.get(0) + x, center.get(1) + y);
				plot(center.get(0) - x, center.get(1) + y);
				plot(center.get(0) - x, center.get(1) - y);
				plot(center.get(0) + x, center.get(1) - y);
				xs.add(center.get(0) + x);
				ys.add(center.get(1) + y);
				if(a == 0) {
					y--;
				} else {
					x++;
				}
			}
			return;
		}
		
		int Di = (int) (Math.pow((x+1)*b,2) + Math.pow((y-1)*a,2) - Math.pow(a*b,2));
		int d = 0;
		String px = "F";
		
		plot(center.get(0), center.get(1) + y);
		plot(center.get(0), center.get(1) - y);
		xs.add(center.get(0) + x);
		ys.add(center.get(1) + y);
		ds.add(d);
		Dis.add(Di);
		pxs.add("F");
		
		while (x <= a && y >= 0) {

			xs.add(center.get(0) + x);
			ys.add(center.get(1) + y);
			ds.add(d);
			Dis.add(Di);
			pxs.add(px);			
			
			if (Math.abs(Di) < EPSILON) {
				x++;
				y--;
				Di += Math.pow(a, 2)*(1-2*y) + Math.pow(b, 2)*(2*x+1);
			} else if (Di > 0) {
				d = (int) (2*(Di - Math.pow(b, 2)*x) - 1);
				if (d <= 0) {
					x++;
					y--;
					Di += Math.pow(a, 2)*(1-2*y) + Math.pow(b, 2)*(2*x+1);
					px = "D";
				} else {
					y--;
					Di += Math.pow(a, 2)*(1-2*y);
					px = "V";
				}
			} else if (Di < 0) {
				d = (int) (2*(Di + Math.pow(a, 2)*y) - 1);
				if (d > 0) {
					x++;
					y--;
					Di += Math.pow(a, 2)*(1-2*y) + Math.pow(b, 2)*(2*x+1);
					px = "D";
				} else {
					x++;
					Di += Math.pow(b, 2)*(2*x+1);
					px = "H";
				}
			}
			
			plot(center.get(0) + x, center.get(1) + y);
			plot(center.get(0) - x, center.get(1) + y);
			plot(center.get(0) - x, center.get(1) - y);
			plot(center.get(0) + x, center.get(1) - y);
		}	
	}
}
