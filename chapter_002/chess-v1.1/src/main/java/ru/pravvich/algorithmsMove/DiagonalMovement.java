package ru.pravvich.algorithmsMove;

import ru.pravvich.*;
import ru.pravvich.figures.*;

public class DiagonalMovement extends Movement {

    private boolean checkRoad = true;

    // проверяет идет ли фигура ровно по диаганали
    private boolean checkDiagonal(Figure figure, Cell position) {
        boolean result = false;
        int checkX = position.getX() - figure.getPosition().getX();
        int checkY = position.getY() - figure.getPosition().getY();
        if (checkX < 0) checkX *= -1;
        if (checkY < 0) checkY *= -1;
        if (checkX == checkY) result = true;
        return result;
    }

    // проверяет свободены ли ячейки до цели(не включая ячейку самой цели) и наполняет массив пути
    private Cell[] checkRoad(Figure figure, Cell position) {
        Cell[] cell = new Cell[position.getY() - figure.getPosition().getY() - 1];
        int index = 0;
        for (int i = figure.getPosition().getY() + 1, j = figure.getPosition().getX() + 1;
             i < position.getY() && j < position.getX(); i++, j++) {
            cell[index++] = Board.desc[i][j].getPosition();
            if (!(Board.desc[i][j] instanceof Place)) {
                this.checkRoad = false;
            }
        }
        return cell;
    }

    @Override
    public Cell[] move(Figure figure, Cell position) throws ImposableMoveException {
        if (this.checkDiagonal(figure, position) &&
                figure.getPosition().getX() < position.getX() &&
                figure.getPosition().getY() < position.getY()) {
            return this.moveRightDown(figure,position);
        } else {
            throw new ImposableMoveException("Invalid move");
        }
    }

    private Cell[] moveRightDown(Figure figure, Cell position) throws ImposableMoveException {
        Cell[] cell = this.checkRoad(figure,position); //!!!
        if (this.checkRoad && (
                Board.desc[position.getY()][position.getX()] instanceof Place ||
                !figure.getColor().equals(Board.desc[position.getY()][position.getX()].getColor()))
                ) {
            return cell;
        } else {
            throw new ImposableMoveException("Invalid move!");
        }
    }
}