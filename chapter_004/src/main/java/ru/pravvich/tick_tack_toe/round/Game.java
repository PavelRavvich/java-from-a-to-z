package ru.pravvich.tick_tack_toe.round;

import ru.pravvich.tick_tack_toe.*;
import ru.pravvich.tick_tack_toe.Users.*;
import ru.pravvich.tick_tack_toe.desk.Desc;
import ru.pravvich.tick_tack_toe.desk.Printer;
import ru.pravvich.tick_tack_toe.desk.ValidationWinnerUtil;

import java.util.ArrayList;

import static java.lang.String.format;

/**
 * Determines game process.
 */
public class Game implements Round {

    /**
     * winner.
     */
    private Positioning winner;

    /**
     * For input.
     */
    private In input = new Input();
    /**
     * Bot player.
     */
    private Positioning bot = new Bot();

    /**
     * User player.
     */
    private Positioning user = new User();

    /**
     * Desc for play.
     */
    private Desc desc = new Desc();

    /**
     * List contain all gamer: bot and user.
     */
    private ArrayList<Positioning> gamers = new ArrayList<>();

    /**
     * Util class contain algorithm determining winner.
     */
    private ValidationWinnerUtil valid = new ValidationWinnerUtil();

    @Override
    public Positioning getWinner() {
        return this.winner;
    }

    /**
     * Check correct move.
     * @param player player which move.
     * @return true if cell is empty(== ' '). False if cell contain symbol.
     */
    private boolean move(Positioning player) {
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
        this.desc.descSize( );
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
     * Configurable statement game if bot move second.
     */
    private void fstMoveBot() {
        this.bot.setColor('X');
        this.user.setColor('O');
        this.gamers.add(this.user);
        this.gamers.add(this.bot);
        this.desc.initInfoDesc();
        this.move(this.bot);
    }

    /**
     * Configurable statement game if user choice move first.
     */
    private void fstMoveUsr() {
        this.user.setColor('X');
        this.bot.setColor('O' );
        this.gamers.add( this.bot);
        this.gamers.add(this.user);
        Printer.printDesc(this.desc.getDesc());
        this.move(this.user);
    }

    /**
     * Loop game process.
     */
    private void loopMoves() {
        Positioning winner = null;
        while (this.valid.gameCanGoOn(this.desc.getDesc())) {
            for (Positioning gamer : this.gamers) {

                if (this.valid.gameCanGoOn(this.desc.getDesc())
                        && this.move(gamer)
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
     * Init result game.
     * @see TickTack#winners
     * @param winner gamer for estimated award.
     */
    private void initResultGame(Positioning winner) {
        if (!this.valid.emptyCallExist(  this.desc.getDesc()) &&
                !this.valid.winnerDetermines(this.desc.getDesc())
                ) {

            System.out.println("Ничья.");
        } else if (this.valid.winnerDetermines(  this.desc.getDesc())) {
            this.winner = winner;
            System.out.println(format("Победитель: %s", winner.getColor()));
        }
    }

    /**
     * Give more chance when player which mistake - try move in busy cell.
     * @param gamer player which mistake.
     */
    private void mistakeMove(Positioning gamer) {
        while (!this.move(gamer)) {
            mistakeMove(gamer);
        }
    }
}
