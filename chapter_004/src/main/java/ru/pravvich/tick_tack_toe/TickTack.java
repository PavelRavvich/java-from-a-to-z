package ru.pravvich.tick_tack_toe;

import java.util.ArrayList;

public class TickTack {

    private ArrayList<Game> games = new ArrayList<>();

    private TickTack() {
        this.initGames();
    }

    private void initGames() {
        for (int i = 0; i < 5; i++) {
            this.games.add(new Game());
        }
    }

    private void start() {
        for (Game game : this.games) {
            game.firstMove();
        }
    }

    public static void main(String[] args) {
        new TickTack().start();
    }
}
