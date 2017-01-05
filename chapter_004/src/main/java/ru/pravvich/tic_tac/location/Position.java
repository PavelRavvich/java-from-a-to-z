package ru.pravvich.tic_tac.location;
// описывает коотрдинаты на доске
public class Position implements Location {
    private int x;
    private int y;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
