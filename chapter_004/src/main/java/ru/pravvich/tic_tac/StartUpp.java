package ru.pravvich.tic_tac;

import ru.pravvich.tic_tac.ticTacToe.*;

import static java.lang.String.format;

public class StartUpp {
    public static void main(String[] args) {
        TicTacToeStart ticTac = new TicTacToe();
        ticTac.createGames();
        ticTac.start();
        System.out.println(format(
                "Первым 5 побед набрал : %s",
                ticTac.getWinner()));
    }
}
