package ru.pravvich;

import ru.pravvich.figures.*;

public class Board {
    public static Figure[][] desc = new Figure[8][8];

    private void add(Figure figure) {
        if (figure instanceof Officer) {
            desc[figure.getPosition().getY()][figure.getPosition().getX()] =
                    new Officer(figure.getPosition(), figure.getColor());
        }
    }
}
