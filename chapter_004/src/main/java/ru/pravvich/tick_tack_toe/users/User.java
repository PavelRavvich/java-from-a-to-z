package ru.pravvich.tick_tack_toe.users;

import ru.pravvich.tick_tack_toe.*;
import ru.pravvich.tick_tack_toe.users.position.*;

public class User implements Gamers {
    private Position posit = new Posit();
    private In input = new Input();
    private final char color;

    public User(char color) {
        this.color = color;
    }

    @Override
    public Position getPosit() {
        return this.posit;
    }

    @Override
    public char getColor() {
        return this.color;
    }

    @Override
    public void setPosit() {
        System.out.println("По вертикали: ");
        this.posit.setX(this.input.getNumInput());
        System.out.println("По горизонтали: ");
        this.posit.setY(this.input.getNumInput());
    }
}
