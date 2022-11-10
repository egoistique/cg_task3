package shapes.utils;

import shapes.shapes.Point;

/**
 * Created by IntelliJ IDEA.
 * User: ZOR
 * Date: 03.12.11
 * Time: 17:27
 */
public class Triangle {
    public Point p1, p2, p3;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    @Override
    public String toString() {
        return "(" + p1 + "," + p2 + "," + p3 + ")";
    }
}
