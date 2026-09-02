

package GameCore;
import Interfaces.HitListener;
import Sprites.Block;
import Sprites.Ball;

/**
 * The {@code BallRemover} class is responsible for removing balls from the game
 * when they hit a designated "death region" block. This class implements the
 * {@link HitListener} interface to handle hit events.
 */
public class BallRemover implements HitListener {

    private Game game;
    private Counter remainBalls;

    /**
     * Constructs a {@code BallRemover} instance.
     *
     * @param game        the game instance where balls are being removed
     * @param remainBalls a counter tracking the number of balls left in the game
     */
    public BallRemover(Game game, Counter remainBalls) {
        this.game = game;
        this.remainBalls = remainBalls;
    }

    /**
     * Handles the event of a ball hitting the designated "death region" block.
     * The ball is removed from the game, and the remaining ball counter is decreased.
     *
     * @param deathRegion the block that acts as the "death region"
     * @param hitter      the ball that hit the "death region"
     */
    public void hitEvent(Block deathRegion, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainBalls.decrease(1);
        System.out.println(this.remainBalls.getValue());
    }
}

