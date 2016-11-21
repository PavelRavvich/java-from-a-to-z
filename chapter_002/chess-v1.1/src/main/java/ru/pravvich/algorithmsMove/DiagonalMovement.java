package ru.pravvich.algorithmsMove;

import ru.pravvich.*;
import ru.pravvich.figures.*;

public class DiagonalMovement extends Movement {

    // проверяет идет ли фигура ровно по диаганали
    public boolean checkDiagonal(Figure figure, Cell position) {
        boolean result = false;
        int checkX = position.getX() - figure.getPosition().getX();
        int checkY = position.getY() - figure.getPosition().getY();
        if (checkX < 0) checkX *= -1;
        if (checkY < 0) checkY *= -1;
        if (checkX == checkY) result = true;
        return result;
    }

    @Override
    public void move(Figure figure, Cell position) throws ImposableMoveException {
        if (this.checkDiagonal(figure, position) &&
                figure.getPosition().getX() < position.getX() &&
                figure.getPosition().getY() < position.getY()) {
            this.moveRightDown(figure,position);
        } // и так далее... потом можно будет использовать для всех кто ходит подиаганали
    }

    private void moveRightDown(Figure figure, Cell position) throws ImposableMoveException {
        //check road
        boolean checkRoad = true;
        for (int i = figure.getPosition().getY() + 1, j = figure.getPosition().getX() + 1;
             i < position.getY() && j < position.getX(); i++, j++) {
            if (Board.desc[i][j] != null) {
                checkRoad = false;
            }
        }

        if (checkRoad && Board.desc[position.getY()][position.getX()] == null) {
            Board.desc[position.getY()][position.getX()] = new Officer(
                    new Cell(position.getY(), position.getX()),figure.getColor());
            Board.desc[figure.getPosition().getY()][figure.getPosition().getX()] = null;
        } else if (Board.desc[position.getY()][position.getX()] != null &&
                !(figure.getColor().equals(Board.desc[position.getY()][position.getX()].getColor()))) {
            Board.desc[position.getY()][position.getX()] = new Officer(
                    new Cell(position.getY(), position.getX()),figure.getColor());
            Board.desc[figure.getPosition().getY()][figure.getPosition().getX()] = null;
        } else {
            throw new ImposableMoveException("Invalid move!");
        }
    }
}