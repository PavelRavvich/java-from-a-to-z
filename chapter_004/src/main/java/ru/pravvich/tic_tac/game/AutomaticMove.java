package ru.pravvich.tic_tac.game;

import ru.pravvich.tic_tac.Cell;
import ru.pravvich.tic_tac.board.Desk;
import ru.pravvich.tic_tac.location.Position;

class AutomaticMove {
    Position generateMove(Desk board) {
        int size = board.getBoard().length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getBoard()[j][i].equals(Cell.EMPTY)) {
                    return new Position(j, i);
                }
            }
        }
        return null;
    }
}
