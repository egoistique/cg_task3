
package shapes.shapes;

import shapes.abstractshapes.AbstractGraphicsPrimitive;
import shapes.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gomon Sergey, ZOR
 */
public class Parabola extends AbstractGraphicsPrimitive 
{
	private static final int DEFAUILT_P = 2;
	
	private Coordinate point;
	private int p;
	private boolean complete;
	
	public List<String> pxs;
	public List<Integer> xs;
	public List<Integer> ys;
	public List<Integer> errDs;
	public List<Integer> ds;
	
	public Parabola() {
		this.point = null;
		this.complete = false;
		this.p = DEFAUILT_P;
		this.pxs = new ArrayList<String>();
		this.xs = new ArrayList<Integer>();
		this.ys = new ArrayList<Integer>();
		this.errDs = new ArrayList<Integer>();
		this.ds = new ArrayList<Integer>();
	}
	
	@Override
	public boolean processMousePress(int x, int y) {
		if(!complete) {
			point = new Coordinate(null, x, y);
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
			point = new Coordinate(null, x, y);
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
	
	public void setP(int p) {
		this.p = p;
		calc();
	}
	
	public Object getP() {
		return p;
	}	
	
    protected void calc() {
		
		if(point == null) {
			return;
		}
		
		clear();
		pxs.clear();
		xs.clear();
		ys.clear();
		errDs.clear();
		ds.clear();		
		
		int x = 0;
		int y = 0;
		String px = "F";
		
		//Решением неравенства x > 2*py^2 являются все точки вне пораболы,
		//значит y^2 - 2*px > 0 для всех точек вне параболы
		int errD = (int) (Math.pow(y + 1, 2) + 2*p*(x - 1));
		int d = 0;
		
		//Строим перую точку
		plot(point.get(0), point.get(1));
		pxs.add(px);
		xs.add(point.get(0));
		ys.add(point.get(1));
		errDs.add(0);
		ds.add(0);
		
		//Размер канвы для рисования - 100 пикселей (больше быть не может)
		while(x >= -100 && x < 100 && Math.abs(y) < 100) {
			errDs.add(errD);
			d = 0;
			if(errD == 0) {
				//Идем в диагональный пиксель
				errD += 2*y + 3 - 2*p;
				x--;
				y++;
				px = "D";
			} else if(errD < 0) {
				//Диагоналный пиксель внутри параболы, выбираем между
				//диагональным и вертикальным
				d = -2*errD - 2*p;
				if(d > 0) {
					//Идем в вертикальный пиксель
					errD += 2*y + 3;
					y++;
					px = "V";
				} else {
					//Идем в диагональный пиксель
					errD += 2*y + 3 - 2*p;
					x--;
					y++;	
					px = "D";
				}
			} else {
				//Диагональный пиксель вне параболы, выбираем мужде 
				//дианональным и горизонтальным
				d = 2*errD - 2*y - 1;
				if(d > 0) {
					errD -= 2*p;
					x--;
					px = "H";
				} else {
					errD += 2*y + 3 - 2*p;
					x--;
					y++;
					px = "D";
				}
			}
			//Строим точку
			plot(x + point.get(0), y + point.get(1));
			//Строим отражение точки
			plot(x + point.get(0), point.get(1) - y);
			pxs.add(px);
			xs.add(x + point.get(0));
			ys.add(y + point.get(1));
			ds.add(d);
		}
    }
}
