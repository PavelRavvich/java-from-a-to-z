package ru.pravvich.tick_tack_toe.round;

import ru.pravvich.tick_tack_toe.*;
import ru.pravvich.tick_tack_toe.users.*;
import ru.pravvich.tick_tack_toe.desk.*;

import java.util.ArrayList;

import static java.lang.String.format;

/**
 * Determines game process.
 */
public class Game implements Round {

    /**
     * Desc for play.
     */
    private Board desc = new Desc(this.input);

    /**
     * winner.
     */
    private Gamers winner;

    /**
     * For input.
     */
    private In input = new Input();

    /**
     * Bot player.
     */
    private Gamers bot;

    /**
     * User player.
     */
    private Gamers user;



    /**
     * Use for test class StubInput.
     */
    @Override
    public void setDesc(Board desc) {
        this.desc = desc;
    }

    /**
     * Use for test class StubInput.
     *
     * @param input emulation console input stream.
     */
    @Override
    public void setInput(In input) {
        this.input = input;
    }

    /**
     * Use for test class StubInput.
     */
    @Override
    public ArrayList<Gamers> getGamers() {
        return this.gamers;
    }

    /**
     * List contain all gamer: bot and user.
     */
    private ArrayList<Gamers> gamers = new ArrayList<>();

    /**
     * Util class contain algorithm determining winner.
     */
    private Validation valid = new ValidationWinnerUtil();

    @Override
    public Gamers getWinner() {
        return this.winner;
    }

    /**
     * Check correct move.
     * @param player player which move.
     * @return true if move success. False if move fail
     */
    private boolean move(Gamers player) {
        player.setPosit();
        if (this.desc.getDesc()[player.getPosit().getY()][player.getPosit().getX()] == ' ') {
            this.desc.getDesc()[player.getPosit().getY()][player.getPosit().getX()] = player.getColor();
            return true;
        } else {
            System.err.println("Так ходить нельзя!");
            return false;
        }
    }

    /**
     * Determines fist move.
     */
    @Override
    public void firstMove() {
        this.desc.initDescSize( );
        System.out.println("Кто ходит первым? Enter: Bot / I");
        if (this.input.getStrInput().toUpperCase().equals("BOT")) {
            this.fstMoveBot();
        } else {
            this.fstMoveUsr();
        }
        Printer.printDesc(this.desc.getDesc());
        this.loopMoves();
    }

    /**
     * Configurable statement game if bot move first.
     */
    private void fstMoveBot() {
        this.gamers.add(this.bot = new Bot(Colors.X.getColor()));
        this.gamers.add(this.user = new User(Colors.O.getColor()));
    }

    /**
     * Configurable statement game if user choice move first.
     */
    private void fstMoveUsr() {
        this.gamers.add(this.user = new User(Colors.X.getColor()));
        this.gamers.add(this.bot = new Bot(Colors.O.getColor()));
    }

    /**
     * Loop game process.
     */
    private void loopMoves() {
        Gamers winner = null;
        while (this.valid.gameCanGoOn(this.desc.getDesc())) {
            for (Gamers gamer : this.gamers) {

                if (this.valid.gameCanGoOn(this.desc.getDesc()) &&
                        this.move(gamer)
                        ) {

                    Printer.printDesc(this.desc.getDesc());
                    winner = gamer;

                } else if (this.valid.gameCanGoOn(this.desc.getDesc())) {
                    this.mistakeMove(gamer);
                    Printer.printDesc(this.desc.getDesc());
                }
            }
        }

        this.initResultGame(winner);
    }

    /**
     * Give more chance when player which mistake - try move in busy cell.
     * @param gamer player which mistake.
     */
    private void mistakeMove(Gamers gamer) {
        while (!this.move(gamer)) {
            mistakeMove(gamer);
        }
    }

    /**
     * Init result game.
     * @see TickTack#winners
     * @param winner gamer for estimated award.
     */
    private void initResultGame(Gamers winner) {
        if (!this.valid.emptyCellExist(  this.desc.getDesc()) &&
                !this.valid.winnerDetermines(this.desc.getDesc())
                ) {

            System.out.println("Ничья.");
        } else if (this.valid.winnerDetermines(  this.desc.getDesc())) {
            this.winner = winner;
            System.out.println(format("Победитель: %s", winner.getColor()));
        }
    }
}
