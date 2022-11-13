
package shapes.utils;

import Jama.Matrix;


/**
 * Однородная координата (точка в однородных координатах)
 *
 */
public class Coordinate implements Comparable, Cloneable  {
	
	private static final double EPSILON = 0.0000001f;
	boolean correct;
	Matrix point;
	
	public Coordinate(Coordinate other) {
		this.correct = other.correct;
		this.point = other.point.copy();
	}	
	
	public Coordinate(int dim) {
		dim += 1;
		double[] row = new double[dim];
		for(int i = 0; i < row.length; i++) {
			row[i] = 0;
		}
		row[row.length-1] = 1;
		double[][] array = new double[1][dim];
		array[0] = row;
		point = new Matrix(array, 1, dim);
		correct = false;
	}
	
	public Coordinate(String s, Integer... a) {
		this(a.length);
		for(int i = 0; i < a.length; i++) {
			point.set(0, i, a[i]);
		}
		correct = true;
	}
	

	
	/**
	 * Так как этот класс еще используется для представления векторов,
	 * что не есть гуд, но выбора особого не было, то массштабный
	 * коэффициент ставится равным 1, т. к. никаких аффинных преобразований
	 * над векторами не производится.
	 */

	
	public Coordinate plus(Coordinate other) {
		Coordinate copy = this.clone();
		copy.point = copy.point.plus(other.point);
		copy.point.set(0, copy.point.getColumnDimension() - 1, 1);
		return copy;
	}

	
	public void set(int... a) {
		for(int i = 0; i < a.length; i++) {
			point.set(0, i, a[i]);
		}
		correct = true;
	}
	

	public int get(int i) {
		return (int)(point.get(0, i)/point.get(0, point.getColumnDimension() - 1) + 0.5*Math.signum(point.get(0, i)));
	}

	
	public boolean isCorrect() {
		return correct;
	}

	@Override
	public int compareTo(Object o) {
		Coordinate other = (Coordinate)o;
		if(other.point.getRowDimension() != point.getRowDimension()
				|| other.point.getColumnDimension() != point.getColumnDimension()) {
			return -1;
		}
		for(int i = 0; i < point.getColumnDimension(); i++) {
			if(Math.abs(other.point.get(0, i)/other.point.get(0, other.point.getColumnDimension() - 1) 
					- point.get(0, i)/point.get(0, point.getColumnDimension() - 1)) > EPSILON) {
				return -1;
			}
		}
		return 0;
	}
	
	@Override
	public Coordinate clone() {
		return new Coordinate(this);
	}	
	
	@Override
	public String toString() {
		String res = "(";
		for(int i = 0; i < point.getColumnDimension(); i++) {
			res += point.get(0, i);
			if(i != point.getColumnDimension() - 1) {
				res += "\t";
			}
		}
		return res + ")";
	}
}
