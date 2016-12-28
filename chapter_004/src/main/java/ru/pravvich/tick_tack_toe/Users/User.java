package ru.pravvich.tick_tack_toe.Users;

public class User implements Positioning {
    private Posit posit = new Posit();
    private Input input = new Input();
    private String color;

    @Override
    public Posit getPosit() {
        return this.posit;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public void setPosit() {
        this.posit.setX(this.input.getNumInput());
        this.posit.setY(this.input.getNumInput());
    }
}
