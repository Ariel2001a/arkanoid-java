
package Geometry;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Represents a point in a 2D Cartesian coordinate system.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point to which the distance is calculated.
     * @return the distance between this point and the other point.
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Compares this point to another point for equality.
     *
     * @param other the other point to compare to.
     * @return {@code true} if the points have the same coordinates; {@code false} otherwise.
     */
    public boolean equals(Point other) {
        return (this.x - other.x == 0) && (this.y - other.y == 0);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Draws this point on the provided DrawSurface with a specified color.
     * The point is drawn as a filled circle with a radius of 3 pixels.
     *
     * @param surface the surface to draw on.
     * @param c       the color of the point.
     */
    public void drawPoint(DrawSurface surface, Color c) {
        surface.setColor(c);
        surface.fillCircle((int) this.x, (int) this.y, 3);
    }
}
