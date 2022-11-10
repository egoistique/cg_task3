package shapes.utils;

import shapes.shapes.Point;

/**
 * Created by IntelliJ IDEA.
 * User: ZOR
 * Date: 03.12.11
 * Time: 17:31
 */
public class Edge {
    public Point p1, p2;

    public Edge(Point x, Point y) {
        this.p1 = x;
        this.p2 = y;
    }

    @Override
    public boolean equals(Object o) {
        Edge otherEdge = (Edge) o;
        return ((this.p1 == otherEdge.p2) && (this.p2 == otherEdge.p1)) || ((this.p1 == otherEdge.p1) && (this.p2 == otherEdge.p2));
    }

    @Override
    public String toString() {
        return "[" + p1 + "," + p2 + "]";
    }
}
