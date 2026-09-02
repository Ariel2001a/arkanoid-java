

package GameCore;


/**
 * The {@code Counter} class is used to keep track of a numeric value that can be increased or decreased.
 * This class provides methods to modify and retrieve the current value of the counter.
 */
public class Counter {
    private int counter;

    /**
     * Constructs a {@code Counter} instance with an initial value.
     *
     * @param initialCount the initial value of the counter
     */
    public Counter(int initialCount) {
        this.counter = initialCount;
    }

    /**
     * Increases the counter by a specified number.
     *
     * @param number the number to increase the counter by
     */
    void increase(int number) {
        this.counter += number;
    }

    /**
     * Decreases the counter by a specified number.
     *
     * @param number the number to decrease the counter by
     */
    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current value of the counter
     */
    int getValue() {
        return this.counter;
    }
}
