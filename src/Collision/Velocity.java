
package Collision;

import Geometry.Point;

/**
 * The {@code Collision.Velocity} class represents a velocity in a 2D space, defined by its
 * horizontal (dx) and vertical (dy) components. It provides methods to manipulate
 * velocity and apply it to a point.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a new {@code Collision.Velocity} with the specified components.
     *
     * @param dx the horizontal velocity component
     * @param dy the vertical velocity component
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the horizontal velocity component.
     *
     * @return the dx component of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the vertical velocity component.
     *
     * @return the dy component of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the horizontal velocity component.
     *
     * @param dx the new dx value
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the vertical velocity component.
     *
     * @param dy the new dy value
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Applies this velocity to a given point and returns a new point
     * with updated coordinates.
     *
     * @param p the point to which the velocity is applied
     * @return a new {@code Geometry.Point} with updated coordinates
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Creates a {@code Collision.Velocity} instance from a given angle and speed.
     *
     * @param angle the angle of the velocity in degrees (measured counterclockwise from the positive x-axis)
     * @param speed the magnitude of the velocity
     * @return a {@code Collision.Velocity} object with calculated dx and dy components
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Returns a string representation of the {@code Collision.Velocity} object.
     *
     * @return a string representation of the velocity
     */
    @Override
    public String toString() {
        return "Collision.Velocity{"
                + "dx=" + dx
                + ", dy=" + dy
                + '}';
    }
}
