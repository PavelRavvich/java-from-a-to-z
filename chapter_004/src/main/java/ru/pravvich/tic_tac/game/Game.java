package ru.pravvich.tic_tac.game;

import ru.pravvich.tic_tac.*;
import ru.pravvich.tic_tac.board.*;
import ru.pravvich.tic_tac.input.*;
import ru.pravvich.tic_tac.location.Position;
import ru.pravvich.tic_tac.users.*;
import ru.pravvich.tic_tac.validation.*;

import static java.lang.String.format;

/**
 * Determines round:
 * Init size desk.
 * Init array with gamers in order.
 * Loop moves from gamers.
 * Get new positions for moves.
 * Determines winner.
 */
public class Game implements Play {
    /**
     * Determines validation winner and exist empty cell.
     */
    private Validation validation = new ValidationWinnerUtil();
    /**
     * Array contain players.
     */
    private Subject[] gamers = new Subject[2];
    /**
     * Winner current round.
     */
    private Subject win;
    /**
     * Desk for round.
     */
    private Desk board;
    /**
     * Provide input stream for user console input.
     */
    private DialogAsk dialogs;

    /**
     * Default constructor.
     * @param dialogs user's console input stream.
     */
    public Game(DialogAsk dialogs) {
        this.dialogs = dialogs;
    }

    /**
     * Init desk size.
     */
    private void initBoard() {
        String answer = this.dialogs.askStr("Хотите использовать стандартный размер доски 3х3 (y/n)?");
        if (answer.equals("n")) {
            this.board = new Board(this.dialogs.askNum("Введите размер доски :"));
        } else if (answer.equals("y")) {
            this.board = new Board(3);
        } else {
            initBoard();
        }
    }

    /**
     * Getter for tests by gamers's order.
     * @return array with gamers.
     */
    Subject[] getGamers() {
        return gamers;
    }

    /**
     * Getter for test correct init board size.
     * @return board for current round.
     */
    Desk getBoard() {
        return board;
    }

    /**
     * Init user's array.
     * Player which move first, is playing for the 'x'.
     */
    @Override
    public void choiceSide() {
        String answer = this.dialogs.askStr("Кто ходит первый: (Bot / I) :");
        if (answer.toLowerCase().equals("i")) {
            this.gamers[0] = new User(Cell.X, "user");
            this.gamers[1] = new User(Cell.O, "bot");
        } else if (answer.equals("bot")){
            this.gamers[0] = new User(Cell.X, "bot");
            this.gamers[1] = new User(Cell.O, "user");
        } else {
            choiceSide();
        }
    }

    /**
     * Loop moves order.
     */
    @Override
    public void loopMove() {
        this.initBoard();
        PrinterBoard.printDesc(board.getBoard());
        while (validation.gameCanGoOn(board.getBoard())) {
            for (Subject subject : this.gamers) {
                try {
                    if (validation.gameCanGoOn(board.getBoard())
                            ) {

                        board.move(subject.getColor(), getNewPosition(subject));
                        PrinterBoard.printDesc(board.getBoard());
                        this.win = subject;
                    }
                } catch (IllegalMove illegalMove) {
                    illegalMove.printStackTrace();
                }
            }
        }
    }

    /**
     * Get new position for move.
     * When player's name is "user" then method treats to Dialogs obj,
     * for get console input.
     * When player's name equal "bot" then method treats to AutomaticMove,
     * for get generating bot's move.
     * @param subject which move now.
     * @return position rof move.
     */
    private Position getNewPosition(Subject subject) {
        if (subject.getName().equals("user")) {
            return new Position(this.dialogs.askNum("По горизонтали:")
                    , this.dialogs.askNum("По вертикали:"));
        } else {
            return new AutomaticMove().generateMove(this.board);
        }
    }

    /**
     * Init winner current round.
     * @return subject which win.
     * If case is dead head then method return new User obj,
     * with color equal empty and name is nobody.
     */
    @Override
    public Subject initWinner() {
        if (!this.validation.emptyCellExist(board.getBoard()) &&
                !this.validation.winnerDetermines(board.getBoard())) {
            System.out.println("Ничья");
            return new User(Cell.EMPTY, "nobody");
        } else {
            System.out.println(format("Победитель: %s", this.win.getColor()));
            return this.win;
        }
    }
}
