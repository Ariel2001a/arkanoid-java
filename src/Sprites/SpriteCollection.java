
package Sprites;

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Sprites.SpriteCollection} class manages a collection of {@link Sprite} objects.
 * It provides methods to add sprites to the collection, notify all sprites that time has passed,
 * and draw all sprites onto a given {@link DrawSurface}.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructs an empty {@code Sprites.SpriteCollection}.
     * Initializes the list that will hold all the sprites in the collection.
     */
    public SpriteCollection() {
        spriteList = new ArrayList<>();
    }


    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added to the collection
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to be removed from the collection
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     * This calls the {@link Sprite#timePassed()} method on each sprite in the collection.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<>(this.spriteList);
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given {@link DrawSurface}.
     * This calls the {@link Sprite#drawOn(DrawSurface)} method on each sprite in the collection.
     *
     * @param d the {@link DrawSurface} to draw all sprites on
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<>(this.spriteList);
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }

    @Override
    public String toString() {
        return "SpritspriteListeCollection{"
                + "=" + spriteList
                + '}';
    }
}