package ru.pravvich.tic_tac.location;

/**
 * Describe position on desk.
 */
public class Position implements Location {
    /**
     * X coordinate.
     */
    private int x;
    /**
     * Y coordinate.
     */
    private int y;

    /**
     * Getter for x coordinate.
     * @return x coordinate.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Getter for y coordinate.
     * @return y coordinate.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Default constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
