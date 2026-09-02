

package GameCore;

import biuoop.DrawSurface;

import java.awt.Color;

import Interfaces.Sprite;
import Geometry.Rectangle;

/**
 * The {@code ScoreIndicator} class represents a score indicator that displays the current score on the screen.
 * It implements the {@code Sprite} interface, allowing it to be drawn on the game surface.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter score;

    /**
     * Constructs a {@code ScoreIndicator} instance with the given rectangle and score counter.
     *
     * @param rect  the rectangle that defines the area of the score indicator
     * @param score the counter that holds the current score
     */
    public ScoreIndicator(Rectangle rect, Counter score) {
        this.rect = rect;
        this.score = score;
    }

    /**
     * Draws the score indicator on the given {@code DrawSurface}.
     * The score is displayed in the center of the rectangle with a label "Score: ".
     *
     * @param d the surface to draw the score indicator on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(((int) rect.getUpperLeft().getX()), ((int) rect.getUpperLeft().getY()),
                ((int) rect.getWidth()), ((int) rect.getHeight()));
        d.setColor(Color.BLACK);
        d.drawText((int) (rect.getUpperLeft().getX() + rect.getWidth() / 2),
                (int) (rect.getUpperLeft().getY() + rect.getHeight() * 9 / 10),
                String.format("Score: %d", score.getValue()), 20);
    }

    /**
     * This method is part of the {@code Sprite} interface, but it does nothing in this case.
     * It is called to notify that time has passed, but the score indicator does not change over time.
     */
    public void timePassed() {

    }

    /**
     * Adds the score indicator to the specified game as a sprite.
     * The score indicator will be drawn on the game surface.
     *
     * @param game the game to add the score indicator to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

}
