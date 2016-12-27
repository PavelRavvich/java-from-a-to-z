package ru.pravvich.tick_tack_toe.Users;

public class Bot implements Positioning {
    private Posit posit = new Posit();

    @Override
    public Posit getPosit() {
        this.setPosit();
        return this.posit;
    }

    private void setPosit() {
        this.posit.setX(this.generatePos());
        this.posit.setY(this.generatePos());
    }

    private int generatePos() {
        // TODO: 27.12.2016
        return 1;
    }
}
