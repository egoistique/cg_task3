package shapes.shapes;

import canva.CanvaGraphics;
import shapes.abstractshapes.AbstractGraphicsObject;
import shapes.utils.Edge;
import shapes.utils.Triangle;
import utils.DelaunayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZOR
 * Date: 03.12.11
 * Time: 16:35
 */
public class Triangulat extends AbstractGraphicsObject {
    private List<Point> points = new ArrayList<Point>();
    private List<Triangle> triangles = Collections.emptyList();

    @Override
    protected void calc() {
        if (points.size() > 2) {
            int i;
            int j;
            int nv = points.size();
            int trimax = 4 * nv;
            int xmin = points.get(0).x;
            int ymin = points.get(0).y;
            int xmax = xmin;
            int ymax = ymin;
            int c = 0;
            for (Point p : points) {
                p.id = c++;
                if (p.x < xmin) xmin = p.x;
                if (p.x > xmax) xmax = p.x;
                if (p.y < ymin) ymin = p.y;
                if (p.y > ymax) ymax = p.y;
            }
            int dx = xmax - xmin;
            int dy = ymax - ymin;
            int dmax = (dx > dy) ? dx : dy;
            double xmid = (xmax + xmin) * 0.5;
            double ymid = (ymax + ymin) * 0.5;

            // Set up the supertriangle
            // This is a triangle which encompasses all the sample points.
            // The supertriangle coordinates are added to the end of the
            // vertex list. The supertriangle is the first triangle in
            // the triangle list.

            points.add(new Point((xmid - 2 * dmax), (ymid - dmax), nv + 0));
            points.add(new Point(xmid, (ymid + 2 * dmax), nv + 1));
            points.add(new Point((xmid + 2 * dmax), (ymid - dmax), nv + 2));

            List<Triangle> triangles = new ArrayList<Triangle>(); //array type de triangles

//            points[nv].id = nv;
//            points[nv + 1].id = nv + 1;
//            points[nv + 2].id = nv + 2;

            Triangle triangleSuper = new Triangle(points.get(nv), points.get(nv + 1), points.get(nv + 2));
            triangles.add(triangleSuper); //SuperTriangle placed at index 0
            System.out.println("СФормирован супертреугольник" + triangleSuper);


            // Include each point one at a time into the existing mesh
            for (i = 0; i < nv; i++) {
                System.out.println("Рассматривается точка_"+i+"("+points.get(i).x+","+points.get(i).y+")");
                List<Edge> Edges = new ArrayList<Edge>(); // [trimax * 3];

                // Set up the edge buffer.
                // If the point (Vertex(i).x,Vertex(i).y) lies inside the circumcircle then the
                // three edges of that triangle are added to the edge buffer and the triangle is removed from list.
                for (j = 0; j < triangles.size(); j++) {
                    if (DelaunayUtils.InCircle(points.get(i), triangles.get(j).p1, triangles.get(j).p2, triangles.get(j).p3)) {
                        System.out.println("Точку_"+i+" содержит окружность, описывающая треугольник "+triangles.get(j));
                        System.out.println("-треугольник удален, ребра добавлены во множество ребер");

                        Edges.add(new Edge(triangles.get(j).p1, triangles.get(j).p2));
                        Edges.add(new Edge(triangles.get(j).p2, triangles.get(j).p3));
                        Edges.add(new Edge(triangles.get(j).p3, triangles.get(j).p1));

                        triangles.remove(j);

                        j--;

                    }
                }

                if (i >= nv) continue; //In case we the last duplicate point we removed was the last in the array


                // Remove duplicate edges
                // Note: if all triangles are specified anticlockwise then all
                // interior edges are opposite pointing in direction.
                System.out.println("просматриваем множество ребер дял удаления дублирующихся");

                for (j = Edges.size() - 2; j >= 0; j--) {

                    for (int k = Edges.size() - 1; k >= j + 1; k--) {

                        if (Edges.get(j).equals(Edges.get(k))) {
                            System.out.println("Дублирующееся ребро "+Edges.get(j)+" удалено ");

                            Edges.remove(k);
                            Edges.remove(j);
                            k--;
                            continue;

                        }

                    }

                }

                // Form new triangles for the current point
                // Skipping over any tagged edges.
                // All edges are arranged in clockwise order.
                System.out.println("просматриваем множество ребер...");
                for (j = 0; j < Edges.size(); j++) {

                    if (triangles.size() >= trimax) {
                        //	throw new ApplicationException("Exceeded maximum edges");
                    }

                    Triangle triangle = new Triangle(Edges.get(j).p1, Edges.get(j).p2, points.get(i));
                    triangles.add(triangle);
                    System.out.println("Треугольник  " + triangle + " сформирован из: точка "+ points.get(i)+" ребро "+Edges.get(j) );

                }

                Edges.clear();

            }


            // Remove triangles with supertriangle points
            // These are triangles which have a vertex number greater than nv


            System.out.println("Удаление треугольников, содержащих точки супертреугольника");
            for (i = triangles.size() - 1; i >= 0; i--) {
                if (triangles.get(i).p1.id >= nv || triangles.get(i).p2.id >= nv || triangles.get(i).p3.id >= nv) {
                    System.out.println("Треугольник  " + triangles.get(i) + "удален ");
                    triangles.remove(i);


                }
            }


            //Remove SuperTriangle points
            points.remove(points.size() - 1);
            points.remove(points.size() - 1);
            points.remove(points.size() - 1);

            this.triangles = triangles;
        }
    }

    @Override
    public boolean processMousePress(int x, int y) {
        points.add(new Point(x, y));
        calc();
        return true;
    }

    @Override
    public boolean processMouseRelease(int x, int y) {

        return false;
    }

    @Override
    public boolean processMouseMove(int x, int y) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean processMouseDoubleClick(int x, int y) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void draw(CanvaGraphics g) {
        for (Triangle tr : triangles) {
            Polygon p = new Polygon();
            p.setPointsFromTriang(tr);
            p.draw(g);
        }
    }

    @Override
    public boolean isComplete() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
