package ru.pravvich.figures;

public abstract class Figure {

    public int getId() {
        return this.id;
    }

    public String getColor() {
        return this.color;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int id;
    public String color;
    public int positionX;
    public int positionY;

    public abstract void move(int[] newPosition);

}
