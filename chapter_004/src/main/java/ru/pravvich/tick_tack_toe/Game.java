package ru.pravvich.tick_tack_toe;

import ru.pravvich.tick_tack_toe.Users.Bot;
import ru.pravvich.tick_tack_toe.Users.Input;
import ru.pravvich.tick_tack_toe.Users.Positioning;
import ru.pravvich.tick_tack_toe.Users.User;

import java.util.ArrayList;

class Game {
    private static char win; // сделать лист и добавлять в него чары и каждый раз проверять нет ли пяти О или Х
    private Positioning bot = new Bot();
    private Positioning user = new User();
    private Desc desc = new Desc();
    private ArrayList<Positioning> gamers = new ArrayList<>();
    private ValidationWinnerUtil valid = new ValidationWinnerUtil();

    public char getWin() {
        return win;
    }

    private boolean move(Positioning player, char symbol) {
        player.setPosit();
        if (this.desc.getDesc()[player.getPosit().getY()][player.getPosit().getX()] == ' ') {
            this.desc.getDesc()[player.getPosit().getY()][player.getPosit().getX()] = symbol;
            win = symbol;
            return true;
        } else {
            System.err.println("Так ходить нельзя!");
            return false;
        }
    }

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
            this.move(this.bot, this.bot.getColor());
        } else {
            Printer.printDesc(this.desc.getDesc());
            this.user.setColor('X');
            this.bot.setColor('O');
            this.gamers.add(this.bot);
            this.gamers.add(this.user);
            this.move(this.user, this.user.getColor());
        }
        Printer.printDesc(this.desc.getDesc());
        this.playLoop();
    }

    private void playLoop() {
        while (!this.valid.valid(this.desc.getDesc()) &&
                this.valid.emptyCallExist(this.desc.getDesc())) {

            for (Positioning gamer : this.gamers) {
                if (this.move(gamer, gamer.getColor())) {
                    Printer.printDesc(this.desc.getDesc());
                } else {
                    this.mistakeMove(gamer);
                    Printer.printDesc(this.desc.getDesc());
                }
            }
        }
        System.out.println("Победитель: " + win);
    }

    private void mistakeMove(Positioning gamer) {
        while (!this.move(gamer, gamer.getColor())) {
            mistakeMove(gamer);
        }
    }

    private static class Printer {
        static void printDesc(char[][] desc) {
            int d = 0;
            System.out.println("y\\x  0:  1:  2:");
            System.out.println("    -----------");
            for (int i = 0; i < desc.length; i++) {
                System.out.print(d + ": | ");
                for (int j = 0; j < desc.length; j++) {
                    System.out.print(desc[j][i]);
                    System.out.print(" | ");
                }
                System.out.println();
                System.out.println("    -----------");
                d++;
            }
        }
    }
}
