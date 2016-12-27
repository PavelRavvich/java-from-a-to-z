package ru.pravvich.tick_tack_toe.Users;

public class User implements Positioning {
    private Posit posit;
    private Input input = new Input();

    @Override
    public Posit getPosit() {
        return this.posit;
    }

    public void setPosition() {
        this.posit.setX(this.input.getNumInput());
        this.posit.setY(this.input.getNumInput());
    }
}
