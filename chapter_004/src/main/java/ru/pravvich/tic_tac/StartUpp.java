package ru.pravvich.tic_tac;

import ru.pravvich.tic_tac.input.ConsoleInput;
import ru.pravvich.tic_tac.input.Input;
import ru.pravvich.tic_tac.ticTacToe.*;

import static java.lang.String.format;

/**
 * Start app.
 */
public class StartUpp {
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        TicTacToeStart ticTac = new TicTacToe();
        ticTac.createGames(input);
        ticTac.start();
        System.out.println(format(
                "Первым 5 побед набрал : %s",
                ticTac.getWinner()));
    }
}
