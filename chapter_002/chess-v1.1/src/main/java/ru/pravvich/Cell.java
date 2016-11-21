package ru.pravvich;

public class Cell {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;

    public Cell(int y, int x) {
        this.x = x;
        this.y = y;
    }
}
