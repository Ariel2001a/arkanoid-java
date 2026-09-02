

package Interfaces;

/**
 * The {@code HitNotifier} interface defines methods for managing {@code HitListener} objects.
 * Classes that implement this interface can notify registered listeners about hit events.
 */
public interface HitNotifier {

    /**
     * Adds a {@code HitListener} to the list of listeners to be notified of hit events.
     *
     * @param hl the {@code HitListener} to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a {@code HitListener} from the list of listeners to be notified of hit events.
     *
     * @param hl the {@code HitListener} to remove
     */
    void removeHitListener(HitListener hl);
}
