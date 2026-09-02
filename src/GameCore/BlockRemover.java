

package GameCore;

import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;

/**
 * The {@code BlockRemover} class is responsible for removing blocks from the game
 * when they are hit by a ball. This class implements the {@link HitListener} interface
 * to handle hit events.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a {@code BlockRemover} instance.
     *
     * @param game            the game instance where blocks are being removed
     * @param remainingBlocks a counter tracking the number of blocks left in the game
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the event of a block being hit by a ball.
     * The block is removed from the game, and the remaining block counter is decreased.
     * Additionally, this listener is removed from the block to prevent redundant notifications.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
        System.out.println(this.remainingBlocks.getValue());
    }
}
