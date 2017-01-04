package ru.pravvich.tic_tac.game;

import org.junit.Test;
import ru.pravvich.tic_tac.Cell;
import ru.pravvich.tic_tac.input.StubInput;
import ru.pravvich.tic_tac.users.Subject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void whenUserMoveFirstThenUserAddInArrGamersFirst() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"I"});
        input.setAnswersNum(new int[]{3});
        Game game = new Game(input);
        game.choiceSide();

        assertThat(game.getGamers()[0].getName(), is("user"));
        assertThat(game.getGamers()[1].getName(), is("bot"));
    }

    @Test
    public void whenBotMoveFirstThenUserAddInArrFirst() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"bot"});
        input.setAnswersNum(new int[] {3});
        Game game = new Game(input);

        game.choiceSide();
        assertThat(game.getGamers()[0].getName(), is("bot"));
        assertThat(game.getGamers()[1].getName(), is("user"));
    }

    @Test
    public void whenBotMoveFirstAndWinByHorizontalThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"bot"});
        input.setAnswersNum(new int[] {3, 2, 0, 2, 1});
        Game game = new Game(input);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("bot"));
        assertThat(subject.getColor(), is(Cell.X));
    }

    @Test
    public void whenManMoveFirstAndWinByHorizontalThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"I"});
        input.setAnswersNum(new int[] {3, 0, 1, 1, 1, 2, 1});
        Game game = new Game(input);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("user"));
        assertThat(subject.getColor(), is(Cell.X));
    }

    @Test
    public void whenManMoveFirstAndWinByVerticalThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"I"});
        input.setAnswersNum(new int[] {3, 0, 0, 1, 0, 2, 0});
        Game game = new Game(input);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("user"));
        assertThat(subject.getColor(), is(Cell.X));
    }

    @Test
    public void whenBotMoveSecondAndWinByVerticalThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"I"});
        input.setAnswersNum(new int[] {3, 0, 1, 0, 2, 1, 1, 2, 2});
        Game game = new Game(input);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("bot"));
        assertThat(subject.getColor(), is(Cell.O));
    }

    @Test
    public void whenManMoveFirstAndWinByDiagonalLeftUpToRightDownThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"I"});
        input.setAnswersNum(new int[] {3, 0, 0, 1, 1, 2, 2});
        Game game = new Game(input);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("user"));
        assertThat(subject.getColor(), is(Cell.X));
    }

    @Test
    public void whenManMoveSecondAndWinByDiagonalRightUpToLeftDownThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"bot"});
        input.setAnswersNum(new int[] {3, 0, 2, 1, 1, 2, 0});
        Game game = new Game(input);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("user"));
        assertThat(subject.getColor(), is(Cell.O));
    }
}