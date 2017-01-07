package ru.pravvich.tic_tac.ticTacToe;

/**
 * Aggregator for TicTacToe interfaces.
 * Loop rounds until 5 wins for once player.
 */
public interface TicTacToeStart extends TicTacCreateGames, TicTacGetWinner {
    /**
     * Loop rounds until 5 wins for once player.
     */
    void start();
}
