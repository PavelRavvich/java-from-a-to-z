package ru.pravvich.tick_tack_toe;

import ru.pravvich.tick_tack_toe.users.*;
import ru.pravvich.tick_tack_toe.round.*;

import java.util.ArrayList;

/**
 * Determines Tick Tack Toe.
 */
class TickTack {

    /**
     * Contain gamers every time his win.
     */
    private ArrayList<Gamers> winners = new ArrayList<>();

    /**
     * Contain all games.
     */
    private ArrayList<Round> games = new ArrayList<>();

    /**
     * Default constructor.
     */
    TickTack() {
        this.initGames();
    }

    /**
     * Init all games.
     */
    private void initGames() {
        for (int i = 0; i < 10; i++) {
            this.games.add(new Game());
        }
    }

    /**
     * Loop games to 5 points.
     * After 5 wins one of players - break.
     */
    void start() {
        for (Round game : this.games) {
            if (this.resultAllGames(winners).equals("")) {
                game.firstMove();
                addWinner(game);
            } else {
                System.out.println(String.format(
                        "Победитель: %s", this.resultAllGames(winners)));
                break;
            }
        }
    }

    /**
     * Add winner in list goals.
     *
     * @param game held game.
     */
    private void addWinner(Round game) {
        if (game.getWinner() != null)
            this.winners.add(game.getWinner());
    }

    /**
     * Check wins to five points.
     *
     * @param users list involved gamers.
     * @return "" if 5 points not success. if 5 points success name winner.
     */
    private String resultAllGames(ArrayList<Gamers> users) {
        int u = 0, b = 0;
        for (Gamers obj : users) {
            if (obj instanceof User) {
                u++;
                if (u == 5)
                    return "user";
            } else {
                b++;
                if (b == 5)
                    return "bot";
            }
        }
        return "";
    }
}
