

package GameCore;

import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;

/**
 * The {@code ScoreTrackingListener} class is responsible for tracking the score when a block is hit.
 * It implements the {@code HitListener} interface and updates the score when a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a {@code ScoreTrackingListener} instance with the given score counter.
     *
     * @param scoreCounter the counter that holds the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called when a collision (hit event) occurs between a ball and a block.
     * It increases the score by 5 points and removes this listener from the block.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}
