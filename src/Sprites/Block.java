
package Sprites;

import Collision.Velocity;
import GameCore.Game;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Block} class represents a block in the game.
 * It implements the {@code Collidable}, {@code Sprite}, and {@code HitNotifier} interfaces.
 * A block can be hit by a ball, drawn on the screen, and notify listeners of hit events.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a {@code Block} with the specified rectangle and color.
     *
     * @param rectangle the rectangle representing the block's shape and position
     * @param color     the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * Adds a {@code HitListener} to the block.
     *
     * @param hl the {@code HitListener} to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a {@code HitListener} from the block.
     *
     * @param hl the {@code HitListener} to remove
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Returns the rectangle representing the block's shape and position.
     *
     * @return the block's rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Returns the color of the block.
     *
     * @return the block's color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Handles the collision of a ball with the block.
     * Updates the ball's velocity based on the collision point and notifies listeners.
     *
     * @param hitter          the ball that hit the block
     * @param collisionPoint  the point of collision
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Line[] edgesOfRect = this.getCollisionRectangle().calcEdges();
        if (!ballColorMatch(hitter)) {
            if (!this.hitListeners.isEmpty()) {
                hitter.setColor(this.color);
            }
            this.notifyHit(hitter);
        }

        // Check each edge of the rectangle to determine collision behavior
        for (Line line : edgesOfRect) {

            // Check for vertical edge collision (X-coordinate matches)
            if (line.start().getX() == line.end().getX() && collisionPoint.getX()
                    == line.start().getX()) {
                //System.out.println("dx before:" + dx);
                return new Velocity(-dx, dy);
            }

            // Check for horizontal edge collision (Y-coordinate matches)
            if (line.start().getY() == line.end().getY() && collisionPoint.getY()
                    == line.start().getY()) {
                // System.out.println("dy before:" + dy);
                return new Velocity(dx, -dy);
            }
        }
        // No collision detected
        return null;
    }

    /**
     * Notifies all registered {@code HitListener}s of a hit event.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Draws the block on the given {@code DrawSurface}.
     *
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        for (int j = 0; j < this.getCollisionRectangle().calcEdges().length; j++) {
            this.getCollisionRectangle().calcEdges()[j].drawOn(surface, Color.black);
        }
    }

    /**
     * Updates the block's state over time.
     */
    public void timePassed() {
    }

    /**
     * Adds the block to the game by registering it as a sprite and collidable.
     *
     * @param game the game to add the block to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Removes the block from the game by unregistering it as a sprite and collidable.
     *
     * @param game the game to remove the block from
     */
    public void removeFromGame(Game game) {
        System.out.println("removeFromGame");
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Checks if the ball's color matches the block's color.
     *
     * @param ball the ball to check
     * @return {@code true} if the colors match, {@code false} otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Returns a string representation of the block.
     *
     * @return a string describing the block
     */
    @Override
    public String toString() {
        return "Sprites.Block{"
                + "rectangle=" + rectangle
                + ", color=" + color
                + '}';
    }
}