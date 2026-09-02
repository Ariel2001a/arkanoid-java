
package Geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The {@code Geometry.Rectangle} class represents a rectangle in a 2D space.
 * It is defined by its upper-left corner, width, and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line[] edges;

    /**
     * Constructs a new rectangle with the specified upper-left corner, width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.edges = calcEdges();
    }

    /**
     * Calculates the four edges of the rectangle.
     * The edges are represented as {@code Geometry.Line} objects.
     *
     * @return an array of the four edges of the rectangle
     */
    public Line[] calcEdges() {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Line upHorizontal = new Line(upperLeft, upperRight);
        Line rightVertical = new Line(upperRight, bottomRight);
        Line bottomHorizontal = new Line(bottomRight, bottomLeft);
        Line leftVertical = new Line(bottomLeft, upperLeft);
        return new Line[]{upHorizontal, rightVertical, bottomHorizontal, leftVertical};
    }

    /**
     * Returns a list of intersection points between this rectangle and the specified line.
     * The method checks for intersections with each of the rectangle's edges.
     *
     * @param line the line to check for intersections with
     * @return a list of intersection points, or an empty list if no intersections are found
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> pointArrayList = new ArrayList<>();
        for (int i = 0; i < this.edges.length; i++) {
            if (this.edges[i].intersectionWith(line) != null) {
                pointArrayList.add(this.edges[i].intersectionWith(line));
            }
        }
        return pointArrayList;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns a string representation of the {@code Geometry.Rectangle} object.
     * This includes the upper-left corner, width, height, and edges of the rectangle.
     *
     * @return a string representation of the rectangle
     */
    @Override
    public String toString() {
        return "Geometry.Rectangle{"
                + "upperLeft=" + upperLeft
                + ", width=" + width
                + ", height=" + height
                + ", edges=" + Arrays.toString(edges)
                + '}';
    }
}
