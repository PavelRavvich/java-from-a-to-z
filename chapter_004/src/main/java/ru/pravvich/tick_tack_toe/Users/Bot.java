package ru.pravvich.tick_tack_toe.Users;

public class Bot implements Positioning {
    private Posit posit = new Posit();
    private String color;
    private int descSize = 3;

    public void setDescSize(int descSize) {
        this.descSize = descSize;
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
    public Posit getPosit() {
        return this.posit;
    }

    @Override
    public void setPosit() {
        this.posit.setX(this.generatePos());
        this.posit.setY(this.generatePos());
    }

    private int generatePos() {
        int maximum = (this.descSize - 1);
        return (int) (Math.random() * (++maximum));
    }
}
