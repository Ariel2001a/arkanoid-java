
package Interfaces;

import Collision.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;

/**
 * The {@code Interfaces.Collidable} interface represents objects in the game
 * that can be collided with by other game elements, such as a ball.
 * Implementing classes must define collision boundaries and how they
 * respond to collisions.
 */
public interface Collidable {

    /**
     * Returns the collision rectangle of the object.
     * This rectangle represents the boundaries used for collision detection.
     *
     * @return the {@code Geometry.Rectangle} representing the collision boundaries
     */
    Rectangle getCollisionRectangle();

    /**
     * Handles the collision event with this object.
     * Determines the new velocity of an object (e.g., a ball) after it collides
     * with this {@code Collidable}.
     *
     * @param hitter the {@code Ball} object that is colliding with this object
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the colliding object
     * @return a new {@code Velocity} representing the updated velocity
     * after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}