package ru.pravvich.tick_tack_toe.Users;

public class User implements Positioning {
    private Posit posit = new Posit();
    private Input input = new Input();
    private char color;

    @Override
    public Posit getPosit() {
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
