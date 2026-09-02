
package Geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * The {@code Geometry.Line} class represents a line segment in a 2D space, defined by two points: start and end.
 * It includes methods to calculate the line's length, midpoint, and determine if it intersects with other lines.
 */
public class Line {
    private Point start;
    private Point end;


    /**
     * Constructs a line segment using two given points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment using the coordinates of two points.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start().distance(this.end());
    }

    /**
     * Calculates the midpoint of the line segment.
     *
     * @return the midpoint of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the starting point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return the ending point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Determines if this line intersects with another line.
     *
     * @param other the other line to check
     * @return {@code true} if the lines intersect, {@code false} otherwise
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * Determines if this line intersects with two other lines.
     *
     * @param other1 the first line to check
     * @param other2 the second line to check
     * @return {@code true} if the line intersects with both lines, {@code false} otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return (this.isIntersecting(other1) && this.isIntersecting(other2));
    }

    /**
     * Calculates the slope (incline) of the line.
     *
     * @return the slope of the line, or {@code Double.POSITIVE_INFINITY} if the line is vertical
     */
    public double incline() {
        if (this.start.getX() == this.end.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * Calculates the y-intercept of the line.
     *
     * @return the y-intercept of the line, or {@code Double.POSITIVE_INFINITY} if the line is vertical
     */
    public double intersectingWithYAxis() {
        if (this.incline() == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        return this.start.getY() - this.start.getX() * this.incline();
    }

    /**
     * Checks if a point lies between the endpoints of two lines.
     *
     * @param line1             the first line
     * @param line2             the second line
     * @param intersectionPoint the point to check
     * @return {@code true} if the point is between the endpoints of both lines, {@code false} otherwise
     */
    public Boolean isBetween(Line line1, Line line2, Point intersectionPoint) {
        if ((intersectionPoint.distance(line1.start) <= line1.length())
                && (intersectionPoint.distance(line1.end) <= line1.length())
                && (intersectionPoint.distance(line2.start) <= line2.length())
                && (intersectionPoint.distance(line2.end) <= line2.length())) {
            return true;
        }
        return false;
    }

    /**
     * Returns the intersection point if the lines intersect, and {@code null} otherwise.
     *
     * @param other the other line to check
     * @return the intersection point or {@code null} if no intersection exists
     */
    public Point intersectionWith(Line other) {
        double x;
        double y;

        // Check if the slopes of both lines or both lines are equal (parallel lines or the same line)
        if (this.incline() == other.incline() || equals(other)) {
            return null;
        }

        // If this line is vertical (slope is infinite)
        if (this.incline() == Double.POSITIVE_INFINITY) {
            x = this.start.getX();
            y = x * other.incline() + other.intersectingWithYAxis();

            // If the other line is vertical
        } else if (other.incline() == Double.POSITIVE_INFINITY) {
            x = other.start.getX();
            y = x * this.incline() + this.intersectingWithYAxis();

            // General case: both lines are not vertical
        } else {
            x = (other.intersectingWithYAxis() - this.intersectingWithYAxis()) / (this.incline() - other.incline());
            y = x * this.incline() + this.intersectingWithYAxis();
        }

        // Check if the intersection point lies on both line segments
        Point intersectionPoint = new Point(x, y);
        if (isBetween(this, other, intersectionPoint)) {
            return intersectionPoint;
        }
        return null;
    }

    /**
     * Checks if two lines are equal.
     *
     * @param other the line to compare with
     * @return {@code true} if the lines are equal, {@code false} otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * Finds the closest intersection point of this line with a rectangle.
     *
     * @param rect the rectangle to check for intersections
     * @return the closest intersection point, or {@code null} if no intersection exists
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        double minDistance = Double.MAX_VALUE;
        Point closestPoint = null;
        for (int i = 0; i < intersectionPoints.size(); i++) {
            double distance = this.start.distance(intersectionPoints.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = intersectionPoints.get(i);
            }
        }
        return closestPoint;
    }

    /**
     * Draws the line on a given surface.
     *
     * @param surface the drawing surface
     * @param color   the color of the line
     */
    public void drawOn(DrawSurface surface, Color color) {
        Point sPoint = this.start;
        Point ePoint = this.end;
        surface.setColor(color);
        surface.drawLine((int) sPoint.getX(), (int) sPoint.getY(), (int) ePoint.getX(), (int) ePoint.getY());
    }

    /**
     * Returns a string representation of the line.
     *
     * @return a string describing the line with its start and end points
     */
    @Override
    public String toString() {
        return "Geometry.Line{"
                + "start=" + start
                + ", end=" + end
                + '}';
    }
}