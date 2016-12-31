package ru.pravvich.tick_tack_toe;

import ru.pravvich.tick_tack_toe.Users.Bot;
import ru.pravvich.tick_tack_toe.Users.Input;
import ru.pravvich.tick_tack_toe.Users.Positioning;
import ru.pravvich.tick_tack_toe.Users.User;

import java.util.ArrayList;

/**
 * Determines game process.
 */
class Game {

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
    void firstMove() {
        this.desc.descSize();
        System.out.println("Кто ходит первым? Enter: Bot / I");
        String answer = new Input().getStrInput().toUpperCase();
        if (answer.equals("BOT")) {
            this.bot.setColor('X');
            this.user.setColor('O');
            this.gamers.add(this.user);
            this.gamers.add(this.bot);
            this.desc.initInfoDesc();
            this.move(this.bot);
        } else {
            Printer.printDesc(this.desc.getDesc());
            this.user.setColor('X');
            this.bot.setColor('O');
            this.gamers.add(this.bot);
            this.gamers.add(this.user);
            this.move(this.user);
        }
        Printer.printDesc(this.desc.getDesc());
        this.playLoop();
    }

    /**
     * Loop game process.
     */
    private void playLoop() {
        Positioning winner = null;
        while (!this.valid.valid(this.desc.getDesc()) &&
                this.valid.emptyCallExist(this.desc.getDesc())) {

            for (Positioning gamer : this.gamers) {
                if (this.move(gamer)) {
                    Printer.printDesc(this.desc.getDesc());
                } else {
                    this.mistakeMove(gamer);
                    Printer.printDesc(this.desc.getDesc());
                }
                winner = gamer;
            }
        }
        TickTack.getWinners().add(winner);
        System.out.println("Победитель: " + winner.getColor());
    }

    /**
     * Give more chance when player mistake.
     * @param gamer player which mistake.
     */
    private void mistakeMove(Positioning gamer) {
        while (!this.move(gamer)) {
            mistakeMove(gamer);
        }
    }

    /**
     * Print current statement desc.
     */
    private static class Printer {
        static void printDesc(char[][] desc) {
            System.out.print("y\\x");

            for (int i = 0; i < desc.length; i++) {
                System.out.print("  " + i + ":");
            }
            System.out.println();
            System.out.print("   ");

            for (char[] aDesc : desc) {
                System.out.print(" ---");
            }
            System.out.println();

            for (int i = 0; i < desc.length; i++) {
                System.out.print(i + ": | ");
                for (char[] aDesc : desc) {
                    System.out.print(aDesc[i]);
                    System.out.print(" | ");
                }
                System.out.println();
                System.out.print("   ");
                for (char[] aDesc : desc) {

                    System.out.print(" ---");
                }
                System.out.println();
            }
        }
    }
}
