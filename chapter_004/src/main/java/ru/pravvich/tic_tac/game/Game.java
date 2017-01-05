package ru.pravvich.tic_tac.game;

import ru.pravvich.tic_tac.*;
import ru.pravvich.tic_tac.board.*;
import ru.pravvich.tic_tac.input.Input;
import ru.pravvich.tic_tac.location.Position;
import ru.pravvich.tic_tac.users.*;
import ru.pravvich.tic_tac.validation.Validation;
import ru.pravvich.tic_tac.validation.ValidationWinnerUtil;

import static java.lang.String.format;
// описывает партию
public class Game implements Play {
    // объект утилитного класса в который вынесена проверка наличия победителя
    private Validation validation = new ValidationWinnerUtil();
    // игроки
    private Subject[] gamers = new Subject[2];
    // ввод с консоли
    private Input input;
    // побелитель данной партии
    private Subject win;
    // доска для игры
    private Desk board;

    public Game(Input input) {
        this.input = input;
    }

    // устанавливает размер доски
    private void initBoard() {
        System.out.println("Размер доски :");
        this.board = new Board(this.input.getNumber());
    }

    // for test
    Subject[] getGamers() {
        return gamers;
    }

    // инициализирует массив с игроками. порядок меняется в зависимости от того кто
    // ходит первым. кто первый тот играет крестиками.
    @Override
    public void choiceSide() {
        this.initBoard();
        System.out.println("Кто ходит первый: (bot / I) :");
        if (!input.getString().toLowerCase().equals("bot")) {
            this.gamers[0] = new User(Cell.X, "user");
            this.gamers[1] = new User(Cell.O, "bot");
        } else {
            this.gamers[0] = new User(Cell.X, "bot");
            this.gamers[1] = new User(Cell.O, "user");
        }
    }

    // зацикливает очередность ходов игроков
    @Override
    public void loopMove() {
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

    // дает новую позицию для хода если бот то генерирует в классе AutomaticMove
    // если пользователь то с консоли принимает
    private Position getNewPosition(Subject subject) {
        if (subject.getName().equals("user")) {
            System.out.println("По вертикали:");
            int y = this.input.getNumber();
            System.out.println("По горизонтали:");
            return new Position(this.input.getNumber(), y);
        } else {
            return new AutomaticMove().generateMove(this.board);
        }
    }

    // в момент когда игра продолжаться не может смотрит есть ли победитель если да то записывает его в поле win
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
