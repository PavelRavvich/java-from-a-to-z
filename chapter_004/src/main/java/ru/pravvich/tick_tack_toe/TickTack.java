package ru.pravvich.tick_tack_toe;

import ru.pravvich.tick_tack_toe.Users.Positioning;
import ru.pravvich.tick_tack_toe.Users.User;

import java.util.ArrayList;

/**
 * Determines Tick Tack Toe.
 */
class TickTack {

    /**
     * Contain gamers every time his win.
     */
    private ArrayList<Positioning> winners = new ArrayList<>();

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
     */
    void start() {
        for (Round game : this.games) {
            if (this.resultGames(winners).equals("")) {
                game.firstMove();
                addWinner(game);
            } else {
                System.out.println(String.format(
                        "Победитель: %s", this.resultGames(winners)));
                break;
            }
        }
    }

    /**
     * Add winner in list goals.
     * @param game held game.
     */
    private void addWinner(Round game) {
        if (game.getWinner() != null)
            this.winners.add(game.getWinner());
    }

    /**
     * Check wins to five points.
     * @param users list involved gamers.
     * @return "" if 5 points not success. if 5 points success name winner.
     */
    private String resultGames(ArrayList<Positioning> users) {
        int u = 0, b = 0;
        for (Positioning obj : users) {
            if (obj instanceof User) {
                u++;
                if (u == 5) return "user";
            } else {
                b++;
                if (b == 5) return "bot";
            }
        }
        return "";
    }
}
