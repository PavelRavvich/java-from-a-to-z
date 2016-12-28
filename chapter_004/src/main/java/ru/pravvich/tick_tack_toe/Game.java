package ru.pravvich.tick_tack_toe;

import ru.pravvich.tick_tack_toe.Users.Bot;
import ru.pravvich.tick_tack_toe.Users.Input;
import ru.pravvich.tick_tack_toe.Users.Positioning;
import ru.pravvich.tick_tack_toe.Users.User;

import java.util.ArrayList;

import static java.lang.String.*;

class Game {
    private static String win;
    private Input input = new Input();
    private ArrayList<Positioning> gamers = new ArrayList<>();
    private String[][] desc = new String[3][3];
    private Bot bot = new Bot();
    private User user = new User();

    public String getWin() {
        return win;
    }

    public String[][] getDesc() {
        return desc;
    }


    private void initNonstandardDesc() {
        System.out.println("Введите размер сторон:");
        int i = this.input.getNumInput();
        this.desc = new String[i][i];
        this.bot.setDescSize(i);
        System.out.println(format("Установлен размер поля: %s/%s", i, i));
    }

    private void descSize() {
        System.out.println("Хотите использовать стандартный размер поля: y/n");
        String answer = this.input.getStrInput();
        if (answer.equals("y")) {
            System.out.println("Установлен стандартный размер поля: 3/3");
        } else if (answer.equals("n")) {
            this.initNonstandardDesc();
        }
    }

    private boolean move(Positioning player, String symbol) {
        player.setPosit();
        if (this.desc[player.getPosit().getY()][player.getPosit().getX()] == null) {
            this.desc[player.getPosit().getY()][player.getPosit().getX()] = symbol;
            win = symbol;
            return true;
        } else {
            System.err.println("Так ходить нельзя!");
            return false;
        }
    }

    void firstMove() {
        descSize();
        System.out.println("Кто ходит первым? Enter: Bot / I");
        String answer = this.input.getStrInput();
        if (answer.equals("bot")) {
            this.bot.setColor("X");
            this.user.setColor("O");
            this.gamers.add(this.bot);
            this.gamers.add(this.user);
            this.move(this.bot, this.bot.getColor());
        } else {
            this.user.setColor("X");
            this.bot.setColor("O");
            this.gamers.add(this.user);
            this.gamers.add(this.bot);
            this.move(this.user, this.user.getColor());
        }
        Utils.printDesc(this.desc);
        this.playLoop();
    }

    private void playLoop() {
        while (!Utils.checkWinner(this.desc)) {
            for (Positioning gamer : this.gamers) {
                if (this.move(gamer, gamer.getColor())) {
                    Utils.printDesc(this.desc);
                }
            }
        }
        System.out.println("Победитель: " + win);
    }


    private static class Utils {

        static void printDesc(String[][] desc) {
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

        private static boolean checkWinner(String[][] desc) {
            return checkWinnerHorizontal(desc) ||
                    checkWinnerVertical(desc) ||
                    checkWinnerDiagonals(desc);
        }

        private static boolean checkWinnerHorizontal(String[][] desc) {
            return desc[0][0].equals(desc[1][0]) && desc[0][0].equals(desc[2][0]) ||
                    desc[0][1].equals(desc[1][1]) && desc[0][1].equals(desc[2][1]) ||
                    desc[0][2].equals(desc[1][2]) && desc[0][2].equals(desc[2][2]);
        }

        private static boolean checkWinnerVertical(String[][] desc) {
            return desc[0][0].equals(desc[0][1]) && desc[0][0].equals(desc[0][2]) ||
                    desc[1][0].equals(desc[1][1]) && desc[1][0].equals(desc[1][2]) ||
                    desc[2][0].equals(desc[2][1]) && desc[2][0].equals(desc[2][2]);
        }

        private static boolean checkWinnerDiagonals(String[][] desc) {
            return desc[0][0].equals(desc[1][1]) && desc[0][0].equals(desc[2][2]) ||
                    desc[2][0].equals(desc[1][1]) && desc[2][0].equals(desc[0][2]);
        }
    }
}
