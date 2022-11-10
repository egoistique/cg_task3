
package shapes.utils;

/**
 *
 * @author Gomon Sergey
 */
public class Area {
	
	private Coordinate point;
	private int height;
	private int width;
	
	public Area() {
	
	}
	
	public Area(Coordinate point, int hightWidth) {
		this.point = point;
		this.height = hightWidth;
		this.width = hightWidth;
	}
	
	public Area(Coordinate point, int hight, int width) {
		this.point = point;
		this.height = hight;
		this.width = width;
	}	
	
	public void setArea(Coordinate point, double hight, double width) {
		this.point = point;
		this.height = (int)(hight + 0.5*Math.signum(hight));
		this.width = (int)(width + 0.5*Math.signum(width));	
	}
	
	public void setArea(Coordinate point, int hight, int width) {
		this.point = point;
		this.height = hight;
		this.width = width;	
	}
	
	//Проверяет, чтобы height и width не равнялись 0
	public void check() {
		if(height < 0) {
			point.set(point.get(0), point.get(1) + height - 1);
			height = -height;
		}
		
		if(width < 0) {
			point.set(point.get(0) + width - 1, point.get(1));
			width = -width;
		}
	}
	
	public void setCenter(Coordinate center) {
		point = new Coordinate(null, 
				center.get(0) - width/2, 
				center.get(1) - height/2);
	}
	
	public Coordinate getCenter() {
		return new Coordinate(null, 
				point.get(0) + width/2, 
				point.get(1) + height/2);
	}
	
	public Coordinate getTopLeft() {
		return new Coordinate(point.clone());
	}
	
	public Coordinate getBottomLeft() {
		return new Coordinate(null, point.get(0), point.get(1) + height - 1);
	}
	
	public Coordinate getTopRight() {
		return new Coordinate(null, point.get(0) + width - 1, point.get(1));
	}
	
	public Coordinate getBottomRight() {
		return new Coordinate(null, point.get(0) + width - 1, point.get(1) + height - 1);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isInside(Coordinate point) {
		if(point.get(0) > this.point.get(0)
				&& point.get(0) < this.point.get(0) + width
				&& point.get(1) > this.point.get(1)
				&& point.get(1) < this.point.get(1) + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getTop() {
		return point.get(1);
	}
	
	public int getLeft() {
		return point.get(0);
	}	
	
	public int getBottom() {
		return getTop() + height - 1;
	}
	
	public int getRight() {
		return getLeft() + width - 1;
	}	
}
