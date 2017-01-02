package ru.pravvich.tick_tack_toe.Users;

import ru.pravvich.tick_tack_toe.Users.position.Posit;
import ru.pravvich.tick_tack_toe.Users.position.Position;

public class User implements Gamers {
    private Position posit = new Posit();
    private In input = new Input();
    private char color;

    @Override
    public Position getPosit() {
        return this.posit;
    }

    @Override
    public void setColor(char color) {
        this.color = color;
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
