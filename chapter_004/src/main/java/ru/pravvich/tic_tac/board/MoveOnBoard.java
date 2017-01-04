package ru.pravvich.tic_tac.board;

import ru.pravvich.tic_tac.Cell;
import ru.pravvich.tic_tac.IllegalMove;
import ru.pravvich.tic_tac.location.Location;
import ru.pravvich.tic_tac.location.Position;

interface MoveOnBoard {
    void move(Cell color, Location position) throws IllegalMove;
}
