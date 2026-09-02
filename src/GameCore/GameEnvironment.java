

package GameCore;


import Collision.CollisionInfo;
import Geometry.Line;
import Geometry.Point;
import Interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code GameCore.GameEnvironment} class manages all collidable objects in the game.
 * It provides methods to add collidables and find the closest collision for a given trajectory.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructs a new {@code GameCore.GameEnvironment} instance.
     * Initializes an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        System.out.println("remove call envi");
        this.collidables.remove(c);
    }

    /**
     * Finds the closest collision between a given trajectory (line) and the collidables in the game environment.
     *
     * @param trajectory the trajectory of a moving object, represented by a line
     * @return a {@code Collision.CollisionInfo} object containing the closest collision point and the collidable
     * object,
     * or {@code null} if no collision is detected
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestCollidable = null;
        double minDistance = Double.MAX_VALUE;

        List<Collidable> copyCollidables = new ArrayList<>(this.collidables);
        // Iterate through all collidables in the game environment
        for (Collidable copyCollidable : copyCollidables) {

            // Get the closest intersection point between the trajectory and the collidable's rectangle
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(
                    copyCollidable.getCollisionRectangle());

            // If a collision point is found, calculate the distance from the trajectory's start point
            if (collisionPoint != null) {
                double distance = trajectory.start().distance(collisionPoint);

                // Update the closest collision if the current one is closer
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCollidable = copyCollidable;
                    closestPoint = collisionPoint;
                }
            }
        }

        // If no collision was found, return null
        if (closestCollidable == null) {
            return null;
        }

        // Return the collision information (point and collidable)
        return new CollisionInfo(closestPoint, closestCollidable);
    }
}
