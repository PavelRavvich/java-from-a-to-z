package ru.pravvich.tic_tac.users;

import ru.pravvich.tic_tac.Cell;

/**
 * Determines players for game.
 */
public class User implements Subject {
    /**
     * Color is 'x' or 'o'.
     */
    private final Cell color;
    /**
     * Gamer's name7 May be user or bot.
     */
    private final String name;

    /**
     * Getter for color.
     * @return color 'x' or 'y'.
     */
    @Override
    public Cell getColor() {
        return color;
    }

    /**
     * Default constructor.
     * @param color side in battle.
     * @param name gamer.
     */
    public User(Cell color, String name) {
        this.color = color;
        this.name = name;
    }

    /**
     * Getter for Gamer's name.
     * @return this name.
     */
    @Override
    public String getName() {
        return name;
    }
}
