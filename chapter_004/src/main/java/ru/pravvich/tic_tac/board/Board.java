package ru.pravvich.tic_tac.board;

import ru.pravvich.tic_tac.Cell;
import ru.pravvich.tic_tac.IllegalMove;
import ru.pravvich.tic_tac.location.Location;

public class Board implements Desk {
    private Cell[][] board;
    private int size;

    @Override
    public Cell[][] getBoard() {
        return this.board;
    }

    public Board(int size) {
        board = new Cell[size][size];
        this.size = size;
        this.initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = Cell.EMPTY;
            }
        }
    }

    @Override
    public void move(Cell color, Location position) throws IllegalMove {
        if (!this.board[position.getX()][position.getY()].equals(Cell.EMPTY)) {
            throw new IllegalMove("IllegalMove " + color.toString() +" " +position.getY() + " " + position.getX());
        }
        this.board[position.getX()][position.getY()] = color;
    }
}
