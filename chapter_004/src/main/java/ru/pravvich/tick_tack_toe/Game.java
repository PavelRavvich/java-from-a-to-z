package ru.pravvich.tick_tack_toe;

import ru.pravvich.tick_tack_toe.Users.Bot;
import ru.pravvich.tick_tack_toe.Users.Input;
import ru.pravvich.tick_tack_toe.Users.Positioning;
import ru.pravvich.tick_tack_toe.Users.User;

import static java.lang.String.*;

public class Game {
    private Input input;

    public String[][] getDesc() {
        return desc;
    }

    private String[][] desc = new String[3][3];
    private Positioning bot = new Bot();
    private Positioning user = new User();

    private void initNonstandardDesc() {
        System.out.println("Введите размер стороны:");
        int i = this.input.getNumInput();
        this.desc = new String[i][i];
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

    public boolean move(Positioning player, String symbol) {
        if (this.desc[player.getPosit().getY()][player.getPosit().getX()] == null) {
            this.desc[player.getPosit().getY()][player.getPosit().getX()] = symbol;
            return true;
        } else {
            System.err.println("Так ходить нельзя!");
            return false;
        }
    }

    public void firstMove() {
        System.out.println("");
        String answer = this.input.getStrInput();
        if (answer.equals("bot")) {
            this.move(this.bot, "X");
        } else {
            this.move(this.user, "0");
        }
    }























}
