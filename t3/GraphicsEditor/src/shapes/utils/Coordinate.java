package shapes.utils;
import Jama.Matrix;


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

}
