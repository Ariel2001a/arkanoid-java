
package Sprites;

import Collision.CollisionInfo;
import Collision.Velocity;
import GameCore.Game;
import GameCore.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import Interfaces.Collidable;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The {@code Sprites.Ball} class represents a ball object in a 2D space.
 * It includes attributes for position, radius, color, velocity, and a game environment for collision detection.
 * The ball can move, draw itself on a surface, and handle collisions with boundaries and obstacles.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a ball with the given center, radius, color, and game environment.
     *
     * @param center          the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment for collision detection
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Constructs a ball with the given center, radius, color, and collision rectangles.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Returns the x-coordinate of the ball's center.
     *
     * @return the x-coordinate as an integer
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the ball's center.
     *
     * @return the y-coordinate as an integer
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given surface.
     *
     * @param surface the surface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the velocity of the ball using a {@code Collision.Velocity} object.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball using horizontal and vertical components.
     *
     * @param dx the horizontal velocity
     * @param dy the vertical velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * Returns the current velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Ensures the ball remains within the specified frame boundaries by reversing velocity
     * if it touches the edges.
     *
     * @param leftBorderOfFrame  the left boundary of the frame
     * @param rightBorderOfFrame the right boundary of the frame
     * @param topBorderOfFrame   the top boundary of the frame
     * @param lowerBorderOfFrame the bottom boundary of the frame
     */
    public void checkInFrame(int leftBorderOfFrame, int rightBorderOfFrame, int topBorderOfFrame,
                             int lowerBorderOfFrame) {

        // Bounce off right border
        if (this.getX() - this.getSize() <= rightBorderOfFrame) {
            if (this.velocity.getDx() < 0) {
                this.velocity.setDx(-this.velocity.getDx());
            }
        }

        // Bounce off left border
        if (this.getX() + this.getSize() >= leftBorderOfFrame) {
            if (this.velocity.getDx() > 0) {
                this.velocity.setDx(-this.velocity.getDx());
            }
        }

        // Bounce off bottom border
        if (this.getY() - this.getSize() <= lowerBorderOfFrame) {
            if (this.velocity.getDy() < 0) {
                this.velocity.setDy(-this.velocity.getDy());
            }
        }

        // Bounce off top border
        if (this.getY() + this.getSize() >= topBorderOfFrame) {
            if (this.velocity.getDy() > 0) {
                this.velocity.setDy(-this.velocity.getDy());
            }
        }
    }

    /**
     * Moves the ball one step forward according to its current velocity.
     * Handles collisions with objects in the game environment.
     */
    public void moveOneStep() {
        Point nextPoint = this.velocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextPoint);

        // Check for collisions along the trajectory
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);
        if (collision == null) {
            //No collision, move normally
            this.center = nextPoint;
        } else {
            double epsilon = 0.01;
            Point collisionPoint = collision.collisionPoint();
            Collidable collisionObject = collision.collisionObject();

            // Adjust the ball's position slightly before the collision point
            this.center = new Point(collisionPoint.getX() - epsilon * this.velocity.getDx(),
                    collisionPoint.getY() - epsilon * this.velocity.getDy());

            // Update the velocity based on the collision
            this.setVelocity(collisionObject.hit(this, collisionPoint, this.velocity));
        }
    }

    /**
     * Notifies the ball that a unit of time has passed.
     * The ball moves one step forward.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the ball to the specified game.
     *
     * @param game the game to add the ball to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Removes the ball from the specified game.
     *
     * @param game the game to remove the ball from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * Returns a string representation of the ball's properties.
     *
     * @return a string containing the ball's center, radius, color, velocity, and game environment
     */
    @Override
    public String toString() {
        return "Sprites.Ball{"
                + "center=" + center
                + ", radius=" + radius
                + ", color=" + color
                + ", velocity=" + velocity
                + ", gameEnvironment=" + gameEnvironment
                + '}';
    }
}