
package Interfaces;

import biuoop.DrawSurface;

/**
 * The {@code Interfaces.Sprite} interface represents a drawable object that can be updated over time.
 * It defines methods for drawing the sprite on a {@link DrawSurface} and notifying the sprite that time has passed.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given {@link DrawSurface}.
     * The sprite should render itself visually on the screen using this method.
     *
     * @param d the {@link DrawSurface} object to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     * This method is used to update the sprite's state, such as movement, animation, or other time-based behavior.
     */
    void timePassed();
}
