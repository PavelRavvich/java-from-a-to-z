package ru.pravvich.tick_tack_toe.users;

import ru.pravvich.tick_tack_toe.users.position.*;
import ru.pravvich.tick_tack_toe.desk.Desc;

public class Bot implements Gamers {

    /**
     * Side for battle.
     */
    private final char color;
    /**
     * Current position.
     */
    private Position posit;

    /**
     * Default constructor.
     *
     * @param color side battle. Init only once in begin game.
     */
    public Bot(char color) {
        this.color = color;
    }

    /**
     * Getter gor color.
     *
     * @return color. May be 'X' or 'O'.
     */
    @Override
    public char getColor() {
        return this.color;
    }

    /**
     * Getter for current position.
     *
     * @return current position.
     */
    @Override
    public Position getPosit() {
        return this.posit;
    }

    /**
     * Setter for position.
     */
    @Override
    public void setPosit() {
        this.posit = this.generatePos();
    }

    /**
     * Generator new positions.
     *
     * @return position.
     */
    private Position generatePos() {
        char[][] desc = Desc.getInfoDesc();
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc.length; j++) {
                if (desc[j][i] == ' ') {
                    return new Posit(i, j);
                }
            }
        }
        System.err.println("Что-то пошло не так");
        return new Posit();
    }
}
