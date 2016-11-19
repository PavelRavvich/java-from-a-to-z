package ru.pravvich;

import ru.pravvich.figures.*;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    public Figure[][] getDesk() {
        return this.desc;
    }

    public int generateID() {
        int result = new Random(System.currentTimeMillis()).nextInt();
        if (result < 0) result *= (-1);
        return result;
    }

    private Random random = new Random();

    public static Figure[][] desc = new Figure[8][8];

    public static ArrayList<Figure> deathFigures = new ArrayList<>();

    public static int indexDeath = 0;

    private void initBoard() {
       
    }
}