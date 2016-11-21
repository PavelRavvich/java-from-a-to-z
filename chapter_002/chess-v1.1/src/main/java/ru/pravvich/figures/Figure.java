package ru.pravvich.figures;

import ru.pravvich.Cell;
import ru.pravvich.ImposableMoveException;

public abstract class Figure {
    final Cell position;

    public Cell getPosition() {
        return this.position;
    }

    public String getColor() {
        return color;
    }

    final String color;

    protected Figure(Cell position, String color) {
        this.position = position;
        this.color = color;
    }

    abstract Cell[] move(Cell position) throws ImposableMoveException;
}
