package ru.pravvich.figures;

import ru.pravvich.Cell;
import ru.pravvich.ImposableMoveException;

public class Place extends Figure {
    protected Place(Cell position, String color) {
        super(position, color);
    }

    @Override
    Cell[] move(Cell position) throws ImposableMoveException {
        return new Cell[0];
    }
}
