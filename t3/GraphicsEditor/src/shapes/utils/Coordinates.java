
package shapes.utils;

import Jama.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Контейнер однородных координат
 *
 */
public class Coordinates implements Cloneable {
	
	private final static int POS_INFINITE = 999999999;
	private final static int NEG_INFINITE = -999999999;
	
    public List<Coordinate> coord;
    
    public Coordinates() {
		coord = new ArrayList<Coordinate>();
    }
    
	private Coordinates(Coordinates coordinates) {
		coord = new ArrayList<Coordinate>();
		for(Coordinate coordinate: coordinates.toList()) {
			coord.add(coordinate.clone());
		}
	}	

    public void addPoint(Coordinate coordinate) {
		coord.add(coordinate.clone());
    }
    
    public void clear() {
		coord.clear();
    }
    
	public boolean isEmpty() {
		return coord.isEmpty();
	}
	
	public int size() {
		return coord.size();
	}
	
	public Coordinate get(int i) {
		return coord.get(i).clone();
	}

	public List<Coordinate> toList() {
		List<Coordinate> list = new ArrayList<Coordinate>();
		for(Coordinate coordinate: coord) {
			list.add(coordinate.clone());
		}
		return list;
	}
	
	@Override
	public Coordinates clone() {
		return new Coordinates(this);
	}
}
