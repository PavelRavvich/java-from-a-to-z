package ru.pravvich.algorithmsMove;

import ru.pravvich.Cell;
import ru.pravvich.ImposableMoveException;
import ru.pravvich.figures.Figure;

public class DiagonalMovement extends Movement {

    @Override
    public Cell[] move(Figure figure, Cell position) throws ImposableMoveException {
        if (this.checkDiagonal(figure, position) &&
                figure.getPosition().getX() < position.getX() &&
                figure.getPosition().getY() < position.getY()) {
            return this.createRoadRightDown(figure, position);
        } else {
            throw new ImposableMoveException("Invalid move");
        }
    }

    // проверяет идет ли фигура ровно по диаганали.
    private boolean checkDiagonal(Figure figure, Cell position) {
        boolean result = false;
        int checkX = position.getX() - figure.getPosition().getX();
        int checkY = position.getY() - figure.getPosition().getY();
        if (checkX < 0) checkX *= -1;
        if (checkY < 0) checkY *= -1;
        if (checkX == checkY) result = true;
        return result;
    }

    // наполняет массив пути новыми Cell
    private Cell[] createRoadRightDown(Figure figure, Cell position) {
        Cell[] cell = new Cell[position.getY() - figure.getPosition().getY() - 1];
        int index = 0;
        for (int i = figure.getPosition().getY() + 1, j = figure.getPosition().getX() + 1;
             i < position.getY() && j < position.getX(); i++, j++) {
            cell[index++] = new Cell(i, j);
        }
        return cell;
    }
}