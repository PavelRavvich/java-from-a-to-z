package ru.pravvich;

public abstract class Figure {
    final Cell position;

    final String color;

    protected Figure(Cell position, String color) {
        this.position = position;
        this.color = color;
    }

    abstract void move(Cell position);
}
