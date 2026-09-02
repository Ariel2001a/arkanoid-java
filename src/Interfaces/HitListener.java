
package Interfaces;

import Sprites.Ball;
import Sprites.Block;

/**
 * The {@code HitListener} interface defines a listener that responds to hit events.
 * Implementing classes will handle actions triggered when a block is hit by a ball.
 */
public interface HitListener {

    /**
     * This method is called whenever the specified block is hit by a ball.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
