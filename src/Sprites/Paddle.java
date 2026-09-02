
package Sprites;


import Collision.Velocity;
import GameCore.Game;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Collidable;
import Interfaces.Sprite;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The {@code Sprites.Paddle} class represents a paddle in a 2D game, typically used in games like Breakout.
 * The paddle can move left and right based on user input, and it interacts with balls that collide with it.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rect;
    private Color color;
    private KeyboardSensor keyboard;
    private int screenWidth;
    private double speed;

    /**
     * Constructs a new paddle with the specified rectangle, color, keyboard sensor, screen width, and speed.
     *
     * @param rect        the rectangle that represents the paddle's shape and position
     * @param color       the color of the paddle
     * @param keyboard    the keyboard sensor to detect key presses
     * @param screenWidth the width of the screen, used to wrap the paddle around
     * @param speed       the speed at which the paddle moves
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard, int screenWidth, double speed) {
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
        this.screenWidth = screenWidth;
        this.speed = speed;
    }

    /**
     * Moves the paddle left by the speed defined.
     * If the paddle moves past the left edge, it wraps around to the right side of the screen.
     */
    public void moveLeft() {
        double newX = this.rect.getUpperLeft().getX() - speed;
        if (newX < 0) {

            // Wrap around to the right side
            newX = screenWidth;
            this.rect = new Rectangle(new Point(newX, this.rect.getUpperLeft().getY()), this.rect.getWidth(),
                    this.rect.getHeight());
        } else {
            this.rect = new Rectangle(new Point(
                    this.rect.getUpperLeft().getX() - speed, this.rect.getUpperLeft().getY()), this.rect.getWidth(),
                    this.rect.getHeight());
        }
    }

    /**
     * Moves the paddle right by the speed defined.
     * If the paddle moves past the right edge, it wraps around to the left side of the screen.
     */
    public void moveRight() {

        double newX = this.rect.getUpperLeft().getX() + speed;
        if (newX > screenWidth) {

            // Wrap around to the left side
            newX = -this.rect.getWidth();
            this.rect = new Rectangle(new Point(newX, this.rect.getUpperLeft().getY()), this.rect.getWidth(),
                    this.rect.getHeight());
        } else {
            this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX() + speed,
                    this.rect.getUpperLeft().getY()), this.rect.getWidth(), this.rect.getHeight());
        }
    }

    /**
     * Updates the paddle's position based on user input.
     * If the left key is pressed, the paddle moves left; if the right key is pressed, the paddle moves right.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the specified drawing surface.
     *
     * @param d the drawing surface on which to draw the paddle
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the rectangle representing the paddle's collision boundaries
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Handles the collision of the paddle with a ball. The paddle can deflect the ball based on where it hits the
     * paddle.
     * The paddle is divided into 5 regions, each of which deflects the ball at different angles.
     *
     * @param hitter           the ball that collides with the paddle (this is the object that is hit)
     * @param collisionPoint   the point where the ball collides with the paddle
     * @param currentVelocity  the current velocity of the ball at the moment of collision
     * @return the new velocity of the ball after the collision, based on the region it hit on the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleStartX = this.rect.getUpperLeft().getX();
        double paddleWidth = this.rect.getWidth();
        double regionWidth = paddleWidth / 5;
        double collisionX = collisionPoint.getX();
        int region = (int) ((collisionX - paddleStartX) / regionWidth) + 1;
        double speed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                + currentVelocity.getDy() * currentVelocity.getDy());
        Line[] edgesOfRect = this.getCollisionRectangle().calcEdges();
        double angle;

        // Check if the collision happened on a vertical or horizontal edge of the paddle
        for (int i = 0; i < edgesOfRect.length; i++) {

            // If the collision is on a vertical edge, reverse the X velocity
            if (edgesOfRect[i].start().getX() == edgesOfRect[i].end().getX() && collisionPoint.getX()
                    == edgesOfRect[i].start().getX()) {
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            }
            // If the collision is on a horizontal edge, adjust the ball's angle based on the region
            if (edgesOfRect[i].start().getY() == edgesOfRect[i].end().getY() && collisionPoint.getY()
                    == edgesOfRect[i].start().getY()) {
                switch (region) {
                    case 1:
                        angle = 240;
                        break;
                    case 2:
                        angle = 210;
                        break;
                    case 3:
                        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                    case 4:
                        angle = 330;
                        break;
                    case 5:
                        angle = 300;
                        break;
                    default:
                        return currentVelocity;
                }
                return Velocity.fromAngleAndSpeed(angle, speed);
            }
        }
        return null;
    }

    /**
     * Adds the paddle to the game by registering it as a sprite and a collidable.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns a string representation of the {@code Sprites.Paddle} object, including its rectangle, color,
     * keyboard sensor, screen width, and speed.
     *
     * @return a string representation of the paddle's properties
     */
    @Override
    public String toString() {
        return "Sprites.Paddle{"
                + "rect=" + rect
                + ", color=" + color
                + ", keyboard=" + keyboard
                + ", screenWidth=" + screenWidth
                + ", speed=" + speed
                + '}';
    }
}