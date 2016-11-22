package ru.pravvich.figures;

import ru.pravvich.Cell;
import ru.pravvich.ImposableMoveException;

public abstract class Figure {
    private final Cell position;

    public Cell getPosition() {
        return this.position;
    }

    public String getColor() {
        return color;
    }

    private final String color;

    Figure(Cell position, String color) {
        this.position = position;
        this.color = color;
    }

    public abstract Cell[] move(Cell position) throws ImposableMoveException;
}
