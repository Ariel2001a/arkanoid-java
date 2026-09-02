

package Collision;

import Geometry.Point;
import Interfaces.Collidable;

/**
 * The {@code Collision.CollisionInfo} class holds information about a collision event.
 * It includes the point where the collision occurred and the collidable object
 * that was involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a {@code Collision.CollisionInfo} object with the specified collision point
     * and collidable object.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point where the collision occurred.
     *
     * @return the {@code Geometry.Point} representing the collision location
     */
    public Point collisionPoint() {
        return this.collisionPoint;

    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the {@code Interfaces.Collidable} object that was collided with
     */
    public Collidable collisionObject() {
        return this.collisionObject;

    }
}
