package ru.pravvich.figures;

import ru.pravvich.Cell;
import ru.pravvich.ImposableMoveException;
import ru.pravvich.algorithmsMove.DiagonalMovement;

public class Officer extends Figure {

    public Officer(Cell position, String color) {
        super(position, color);
    }

    private DiagonalMovement diagonal = new DiagonalMovement();

    @Override
    public Cell[] move(Cell position) {
        try {
            return this.diagonal.move(this, position);
        } catch (ImposableMoveException ex) {
            System.err.println(ex.getMessage());
        }
        return new Cell[0];
    }
}