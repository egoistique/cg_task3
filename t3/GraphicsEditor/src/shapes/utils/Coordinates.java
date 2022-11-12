
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
	
	public Coordinate getNoClone(int i) {
		return coord.get(i);
	}
	
	public Coordinate getTop() {
		if(coord.isEmpty()) {
			return null;
		} else {
			return coord.get(coord.size()-1).clone();
		}
	}

	public void replaceTop(Coordinate coordinate) {
		if(!coord.isEmpty()) {
			coord.remove(coord.size()-1);
		}
		coord.add(coordinate.clone());
	}

    /**
     * Возвращает одну из координат как список целых чисел. 
     * Значения координат округляются до ближайшего целого числа.
     * 
     * @param i номер проекции, которую необходимо взять. 0 - взять
     * координату x, 1 - взять координату y, 2 - взять масштабный
     * коэффициент
     * 
     * @return список координат соответствующей проекции
     */
    public List<Integer> getProjection(int i) {
		List<Integer> c = new ArrayList<Integer>();
		for(Coordinate coordinate: coord) {
			c.add(coordinate.get(i));
		}
		return c;
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
