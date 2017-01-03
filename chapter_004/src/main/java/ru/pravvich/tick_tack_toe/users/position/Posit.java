package ru.pravvich.tick_tack_toe.users.position;

/**
 * Determines position on 2d coordinate axis.
 */
public class Posit implements Position {
    private int x;
    private int y;

    /**
     * Constructor for create position in gamers fields.
     */
    public Posit() {
    }

    /**
     * Constructor for bot. Where bot generate move he use this constructor.
     * @param x - x.
     * @param y - y.
     */
    public Posit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
