

import GameCore.Game;

/**
 * The Ass3Game class serves as the entry point for the game application.
 * This class creates an instance of the GameCore.Game, initializes it, and runs it.
 */
public class  ArkanoidGame {
    /**
     * The main method that starts the execution of the program.
     * It creates a new game instance, initializes the game, and runs it.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        // Create a new instance of the game
        Game game = new Game();
        // Initialize the game
        game.initialize();
        // Run the game loop or main logic
        game.run();
    }
}
