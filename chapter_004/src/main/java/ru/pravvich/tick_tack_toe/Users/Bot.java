package ru.pravvich.tick_tack_toe.Users;

import ru.pravvich.tick_tack_toe.Desc;

public class Bot implements Positioning {
    private Posit posit = new Posit();
    private char color;
    private int x = -1;
    private int y = -1;

    @Override
    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public char getColor() {
        return this.color;
    }

    @Override
    public Posit getPosit() {
        return this.posit;
    }

    @Override
    public void setPosit() {
        this.posit = this.generatePos();
    }

    private Posit generatePos() {
        char[][] desc = Desc.getInfoDesc();
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc.length; j++) {
                if (desc[j][i] == ' ') {
                    return new Posit(i, j);
                }
            }
        }
        System.err.println("Что-то пошло не так");
        return new Posit();
    }
}
