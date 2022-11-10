/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes.types;

import Jama.Matrix;
import shapes.utils.Coordinates;

/**
 *
 * @author Sergey
 */
public interface IAffineTransformed {
	Coordinates getPoints();
	Matrix applyAffine(Matrix transformation);
}
