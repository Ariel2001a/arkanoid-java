
package GameCore;

import Collision.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Sprite;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;
import Sprites.SpriteCollection;
import Interfaces.Collidable;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The {@code Game} class represents the main game logic.
 * It initializes the game environment, creates game objects such as balls,
 * blocks, and a paddle, and runs the animation loop.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreTracking;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    private static final Color[] BLOCK_COLORS = {
            Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.PINK, Color.ORANGE
    };

    /**
     * Constructs a new {@code Game} instance.
     * Initializes the sprite collection, game environment, counters, and GUI.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.scoreTracking = new Counter(0);
        this.gui = new GUI("Arknoid", SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    /**
     * Adds a {@code Interfaces.Collidable} object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a {@code Interfaces.Sprite} object to the sprite collection.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a {@code Collidable} object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        System.out.println("remove collidable");
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a {@code Sprite} object from the sprite collection.
     *
     * @param s the sprite object to remove
     */
    public void removeSprite(Sprite s) {
        System.out.println("remove sprite");
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes the game by creating the GUI, blocks, balls, paddle,
     * and adding them to the game environment and sprite collection.
     */
    public void initialize() {
        initWalls();
        initBlocks();
        initBalls();
        initPaddle();
        initDeathRegion();
        initScoreBoard();
    }

    /**
     * Initializes the wall blocks around the game area.
     */
    private void initWalls() {
        List<Block> walls = new ArrayList<>();
        walls.add(new Block(new Rectangle(new Point(0, 20), 800, 30), Color.GRAY));
        walls.add(new Block(new Rectangle(new Point(770, 50), 30, 550), Color.GRAY));
        walls.add(new Block(new Rectangle(new Point(0, 50), 30, 550), Color.GRAY));
        for (Block wall : walls) {
            wall.addToGame(this);
        }
    }

    /**
     * Initializes the death region at the bottom of the screen.
     * This region removes balls that fall below it.
     */
    private void initDeathRegion() {
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        Block deathBlock = new Block(new Rectangle(new Point(30, 600), 740, 30), Color.BLACK);
        deathBlock.addHitListener(ballRemover);
        deathBlock.addToGame(this);
    }

    /**
     * Initializes the blocks in the game, organized in rows with different colors.
     */
    private void initBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (int row = 0; row < BLOCK_COLORS.length; row++) {
            createBlockRow(blocks, 10 - row, 370 + row * 40,
                    130 + row * 20, BLOCK_COLORS[row]);
        }
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreTracking);
        // Add blocks to the GameCore.GameEnvironment
        for (Block block : blocks) {
            if (!(block.getColor().equals(Color.GRAY)) && !(block.getColor().equals(Color.BLACK))) {
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
            }
            block.addToGame(this);
        }

    }

    /**
     * Initializes the score indicator at the top of the screen.
     */
    private void initScoreBoard() {
        ScoreIndicator scoreIndicator =
                new ScoreIndicator(new Rectangle(new Point(0, 0), gui.getDrawSurface().getWidth(), 20), scoreTracking);
        scoreIndicator.addToGame(this);
    }

    /**
     * Initializes the balls in the game and sets their initial velocities.
     */
    private void initBalls() {
        // Step 4.1: Create the first Sprites.Ball
        Ball ball1 = new Ball(new Point(400, 300), 4, Color.white, environment);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(120, 10));
        ball1.addToGame(this);
        ballCounter.increase(1);

        //Step 4.2: Create the second Sprites.Ball
        Sprites.Ball ball2 = new Sprites.Ball(new Geometry.Point(200, 200), 4, Color.white, environment);
        ball2.setVelocity(Collision.Velocity.fromAngleAndSpeed(-50, 10));
        ball2.addToGame(this);
        ballCounter.increase(1);

        Sprites.Ball ball3 = new Sprites.Ball(new Geometry.Point(70, 75), 4, Color.white, environment);
        ball3.setVelocity(Collision.Velocity.fromAngleAndSpeed(-30, 10));
        ball3.addToGame(this);
        ballCounter.increase(1);
    }

    /**
     * Initializes the paddle and adds it to the game.
     */
    private void initPaddle() {
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        //Step 5: Create the Sprites.Paddle
        Rectangle paddleRect = new Rectangle(new Point(350, 550), 120, 20);
        Paddle paddle = new Paddle(paddleRect, Color.GREEN, keyboard, SCREEN_WIDTH, 10);
        paddle.addToGame(this);
    }

    /**
     * Creates a row of blocks and adds them to the list.
     *
     * @param blocks    the list of blocks to add to
     * @param numBlocks the number of blocks in the row
     * @param xStart    the starting x-coordinate of the row
     * @param yStart    the starting y-coordinate of the row
     * @param color     the color of the blocks in the row
     */
    private void createBlockRow(List<Block> blocks, int numBlocks, int xStart, int yStart, Color color) {
        for (int i = 0; i < numBlocks; i++) {
            blocks.add(new Block(new Rectangle(
                    new Point(xStart + i * 40, yStart), 40, 20), color));
            blockCounter.increase(1);
        }
    }

    /**
     * Runs the game animation loop.
     * This method repeatedly draws all sprites, updates their state, and checks for game-over conditions.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 50;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            // Clear the screen
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

            // Draw all sprites and update their state
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // Check for game-over conditions
            if (blockCounter.getValue() == 0) {
                scoreTracking.increase(100);
                break;
            } else if (ballCounter.getValue() == 0) {
                break;
            }

            // Sleep for the remaining time in the frame
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

        // Close the GUI when the game ends
        gui.close();
    }
}