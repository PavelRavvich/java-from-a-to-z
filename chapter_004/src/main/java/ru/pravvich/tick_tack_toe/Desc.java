package ru.pravvich.tick_tack_toe;

import ru.pravvich.tick_tack_toe.Users.In;
import ru.pravvich.tick_tack_toe.Users.Input;

import static java.lang.String.format;

public class Desc {
    private In input = new Input();
    private char[][] desc = new char[3][3];
    private static char[][] infoDesc;


    public static char[][] getInfoDesc() {
        return infoDesc;
    }

    public char[][] getDesc() {
        initInfoDesc();
        return this.desc;
    }

    void initInfoDesc() {
        infoDesc = this.desc;

    }

    private void initNonstandardDesc() {
        System.out.println("Введите размер сторон:");
        int i = this.input.getNumInput();
        this.desc = new char[i][i];
        System.out.println(format("Установлен размер поля: %s/%s", i, i));
    }

    private void initContentDesc() {
        for (int i = 0; i != this.desc.length; i++) {
            for (int j = 0; j != this.desc.length; j++) {
                this.desc[j][i] = ' ';
            }
        }
    }

    public void descSize() {
        System.out.println("Хотите использовать стандартный размер поля: y/n");
        String answer = this.input.getStrInput();
        if (answer.equals("y")) {
            System.out.println("Установлен стандартный размер поля: 3/3");
        } else if (answer.equals("n")) {
            this.initNonstandardDesc();
        }
        this.initContentDesc();
    }
}
