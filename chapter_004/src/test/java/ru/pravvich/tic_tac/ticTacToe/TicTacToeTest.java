package ru.pravvich.tic_tac.ticTacToe;

import org.junit.Test;
import ru.pravvich.tic_tac.input.StubInput;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TicTacToeTest {
    @Test
    public void whenBotMoveFirstAndManWinFiveGamesThenManWinnerAll() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[]{
                "bot", "bot", "bot", "bot", "bot"});

        input.setAnswersNum(new int[]{
                3, 0, 1, 1, 1, 2, 1,
                3, 0, 1, 1, 1, 2, 1,
                3, 0, 1, 1, 1, 2, 1,
                3, 0, 1, 1, 1, 2, 1,
                3, 0, 1, 1, 1, 2, 1,});

        TicTacToeStart ticTacToe = new TicTacToe();
        ticTacToe.setInput(input);
        ticTacToe.start();
        assertThat(ticTacToe.getWinner(), is("user"));
    }

    @Test
    public void whenManMoveFirstAndManWinFiveGamesThenManWinnerAll() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[]{
                "user", "user", "user", "user", "user"});

        input.setAnswersNum(new int[]{
                3, 0, 1, 1, 1, 2, 1,
                3, 0, 1, 1, 1, 2, 1,
                3, 0, 1, 1, 1, 2, 1,
                3, 0, 1, 1, 1, 2, 1,
                3, 0, 1, 1, 1, 2, 1,});

        TicTacToeStart ticTacToe = new TicTacToe();
        ticTacToe.setInput(input);
        ticTacToe.start();
        assertThat(ticTacToe.getWinner(), is("user"));
    }

    @Test
    public void whenManMoveFirstAndBotWinFiveGamesThenBotWinnerAll() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[]{
                "user", "user", "user", "user", "user"});

        input.setAnswersNum(new int[]{
                3, 1, 0, 1, 1, 2, 1,
                3, 1, 0, 1, 1, 2, 1,
                3, 1, 0, 1, 1, 2, 1,
                3, 1, 0, 1, 1, 2, 1,
                3, 1, 0, 1, 1, 2, 1,});

        TicTacToeStart ticTacToe = new TicTacToe();
        ticTacToe.setInput(input);
        ticTacToe.start();
        assertThat(ticTacToe.getWinner(), is("bot"));
    }

    @Test
    public void whenBotMoveFirstAndBotWinFiveGamesThenBotWinnerAll() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[]{
                "bot", "bot", "bot", "bot", "bot"});

        input.setAnswersNum(new int[]{
                3, 1, 0, 1, 1,
                3, 1, 0, 1, 1,
                3, 1, 0, 1, 1,
                3, 1, 0, 1, 1,
                3, 1, 0, 1, 1,});

        TicTacToeStart ticTacToe = new TicTacToe();
        ticTacToe.setInput(input);
        ticTacToe.start();
        assertThat(ticTacToe.getWinner(), is("bot"));
    }
}