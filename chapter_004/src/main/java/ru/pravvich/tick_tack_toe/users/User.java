package ru.pravvich.tick_tack_toe.users;

import ru.pravvich.tick_tack_toe.*;
import ru.pravvich.tick_tack_toe.users.position.*;

/**
 * Determines user.
 */
public class User implements Gamers, StubInputInterface {

    /**
     * Position for move.
     */
    private Position posit = new Posit();

    /**
     * Console input for get data from user.
     */
    private In input = new Input();

    /**
     * Side on current game. May be 'X' or 'O'.
     */
    private final char color;

    /**
     * Default constructor.
     * @param color 'X' or 'O'.
     */
    public User(char color) {
        this.color = color;
    }

    /**
     * Getter for position.
     * @return position.
     */
    @Override
    public Position getPosit() {
        return this.posit;
    }

    /**
     * Getter for color.
     * @return color.
     */
    @Override
    public char getColor() {
        return this.color;
    }

    /**
     * For get data about move position from user by console input.
     */
    @Override
    public void setPosit() {
        System.out.println("По вертикали: ");
        this.posit.setX(this.input.getNumInput());
        System.out.println("По горизонтали: ");
        this.posit.setY(this.input.getNumInput());
    }

    /**
     * This setter for tests. He need for use StubInput class.
     * @param input emulation console input.
     */
    @Override
    public void setInput(In input) {
        this.input = input;
    }
}
