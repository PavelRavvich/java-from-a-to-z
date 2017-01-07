package ru.pravvich.tic_tac.ticTacToe;

import ru.pravvich.tic_tac.game.*;
import ru.pravvich.tic_tac.input.*;
import ru.pravvich.tic_tac.users.Subject;

import java.util.ArrayList;

/**
 * Determines global games.
 */
public class TicTacToe implements TicTacToeStart {
    /**
     * Contain all round which may by played.
     */
    private ArrayList<Play> games = new ArrayList<>();
    /**
     * Contain winners subject.
     */
    private ArrayList<Subject> winners = new ArrayList<>();
    /**
     * Subject's name which get 5 wins.
     */
    private String winner;

    /**
     * Use for tests.
     * @param games all round which may by played.
     */
    void setGames(ArrayList games) {
        this.games = games;
    }

    /**
     * Init all round which may by played.
     * @param input stream for console in.
     */
    public void createGames(Input input) {
        for (int i = 0; i < 100; i++) {
            this.games.add(
                    new Game(
                            new Dialog(input)));
        }
    }

    /**
     * Getter for name winner which get 5 wins.
     * @return result game winner.
     */
    @Override
    public String getWinner() {
        return this.winner;
    }

    /**
     * Loop rounds until 5 wins for once player.
     */
    @Override
    public void start() {
        int count = 0;
        while (checkWinner().equals("")) {
            Play game = this.games.get(count);
            game.choiceSide();
            game.loopMove();
            Subject winner = game.initWinner();
            if (!winner.getName().equals("nobody")) {
                this.winners.add(winner);
            }
            count++;
        }
        this.winner = checkWinner();
    }

    /**
     * Check exist global winner.
     * @return name global winner or "" when global winner don't determines.
     */
    private String checkWinner() {
        int user = 0, bot = 0;
        for (Subject winner : winners) {
            if ("user".equals(winner.getName())) {
                user++;
                if (user == 5) return "user";
            }

            if ("bot".equals(winner.getName())) {
                bot++;
                if (bot == 5) return "bot";
            }
        }
        return "";
    }
}
