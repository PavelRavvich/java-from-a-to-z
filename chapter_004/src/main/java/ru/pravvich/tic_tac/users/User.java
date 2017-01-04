package ru.pravvich.tic_tac.users;

import ru.pravvich.tic_tac.Cell;

public class User implements Subject {
    private final Cell color;
    private final String name;

    @Override
    public Cell getColor() {
        return color;
    }

    public User(Cell color, String name) {
        this.color = color;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
