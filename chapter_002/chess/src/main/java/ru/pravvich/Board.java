package ru.pravvich;

import ru.pravvich.figures.*;

public class Board {
    public Figure[][] getDesk() {
        return this.desc;
    }

    private int forGenerateID = 0;

    public int generateID() {
        return this.forGenerateID++;
    }

    public static Figure[][] desc = new Figure[8][8];

    public static Figure[] deathFigures = new Figure[32];

    public static int indexDeath = 0;

    private void initBoard() {
      
    }
}