package ru.pravvich.tic_tac.ticTacToe;

import org.junit.Test;
import ru.pravvich.tic_tac.input.*;
import ru.pravvich.tic_tac.game.*;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TicTacToeTest {
    @Test
    public void whenBotMoveFirstAndUserWinFiveGamesThenUserWinnerAll() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {
                "bot", "y",
                "bot", "y",
                "bot", "y",
                "bot", "y",
                "bot", "y"});

        input.setAnswersNum(new int[] {
                2, 0, 1, 1, 0, 2,
                2, 0, 1, 1, 0, 2,
                2, 0, 1, 1, 0, 2,
                2, 0, 1, 1, 0, 2,
                2, 0, 1, 1, 0, 2});

        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);

        ArrayList<Play> games = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            games.add(game);
        }

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.setGames(games);

        ticTacToe.start();
        assertThat(ticTacToe.getWinner(), is("user"));
    }

    @Test
    public void whenBotMoveFirstAndWinFiveGamesThenBotWinnerAll() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {
                "bot", "y",
                "bot", "y",
                "bot", "y",
                "bot", "y",
                "bot", "y"});

        input.setAnswersNum(new int[] {
                0, 1, 1, 1,
                0, 1, 1, 1,
                0, 1, 1, 1,
                0, 1, 1, 1,
                0, 1, 1, 1});

        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);

        ArrayList<Play> games = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            games.add(game);
        }

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.setGames(games);

        ticTacToe.start();
        assertThat(ticTacToe.getWinner(), is("bot"));
    }

    @Test
    public void whenManMoveFirstAndWinFiveGamesThenManWinnerAll() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {
                "I", "y",
                "I", "y",
                "I", "y",
                "I", "y",
                "I", "y"});

        input.setAnswersNum(new int[] {
                0, 1, 1, 1, 2, 1,
                0, 1, 1, 1, 2, 1,
                0, 1, 1, 1, 2, 1,
                0, 1, 1, 1, 2, 1,
                0, 1, 1, 1, 2, 1});

        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);

        ArrayList<Play> games = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            games.add(game);
        }

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.setGames(games);

        ticTacToe.start();
        assertThat(ticTacToe.getWinner(), is("user"));
    }

    @Test
    public void whenManMoveFirstAndBotWinFiveGamesThenBotWinnerAll() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {
                "I", "y",
                "I", "y",
                "I", "y",
                "I", "y",
                "I", "y"});

        input.setAnswersNum(new int[] {
                1, 0, 2, 0, 1, 1, 2, 2,
                1, 0, 2, 0, 1, 1, 2, 2,
                1, 0, 2, 0, 1, 1, 2, 2,
                1, 0, 2, 0, 1, 1, 2, 2,
                1, 0, 2, 0, 1, 1, 2, 2});

        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);

        ArrayList<Play> games = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            games.add(game);
        }

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.setGames(games);

        ticTacToe.start();
        assertThat(ticTacToe.getWinner(), is("bot"));
    }

    @Test
    public void whenThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {
                "I", "y",
                "I", "y",
                "I", "y",
                "I", "y",
                "I", "y"});

        input.setAnswersNum(new int[] {
                1, 0, 2, 0, 1, 1, 2, 2,
                1, 0, 2, 0, 1, 1, 2, 2,
                1, 0, 2, 0, 1, 1, 2, 2,
                1, 0, 2, 0, 1, 1, 2, 2,
                1, 0, 2, 0, 1, 1, 2, 2});


    }
}