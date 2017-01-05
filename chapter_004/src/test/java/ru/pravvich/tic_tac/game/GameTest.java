package ru.pravvich.tic_tac.game;

import org.junit.Test;
import ru.pravvich.tic_tac.Cell;
import ru.pravvich.tic_tac.input.*;
import ru.pravvich.tic_tac.users.Subject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void whenUserMoveFirstThenUserAddInArrGamersFirst() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"i"});
        input.setAnswersNum(new int[]{3});
        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);
        game.choiceSide();

        assertThat(game.getGamers()[0].getName(), is("user"));
        assertThat(game.getGamers()[1].getName(), is("bot"));
    }

    @Test
    public void whenBotMoveFirstThenUserAddInArrFirst() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"bot"});
        input.setAnswersNum(new int[] {3});
        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);

        game.choiceSide();
        assertThat(game.getGamers()[0].getName(), is("bot"));
        assertThat(game.getGamers()[1].getName(), is("user"));
    }

    @Test
    public void whenBotMoveFirstAndWinByHorizontalThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"bot", "y"});
        input.setAnswersNum(new int[] {0, 1, 1, 1});
        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("bot"));
        assertThat(subject.getColor(), is(Cell.X));
    }

    @Test
    public void whenManMoveFirstAndWinByHorizontalThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"I", "y"});
        input.setAnswersNum(new int[] {0, 1, 1, 1, 2, 1});
        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("user"));
        assertThat(subject.getColor(), is(Cell.X));
    }

    @Test
    public void whenManMoveFirstAndWinByVerticalThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"I", "y"});
        input.setAnswersNum(new int[] {0, 0, 0, 1, 0, 2});
        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("user"));
        assertThat(subject.getColor(), is(Cell.X));
    }

    @Test
    public void whenBotMoveSecondAndWinByVerticalThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"I", "y"});
        input.setAnswersNum(new int[] {1, 0, 2, 0, 1, 1, 2, 2});
        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("bot"));
        assertThat(subject.getColor(), is(Cell.O));
    }

    @Test
    public void whenManMoveFirstAndWinByDiagonalLeftUpToRightDownThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"I", "y"});
        input.setAnswersNum(new int[] {0, 0, 1, 1, 2, 2});
        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("user"));
        assertThat(subject.getColor(), is(Cell.X));
    }

    @Test
    public void whenManMoveSecondAndWinByDiagonalRightUpToLeftDownThen() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"bot", "y"});
        input.setAnswersNum(new int[] {2, 0, 1, 1, 0, 2});
        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);
        game.choiceSide();

        game.loopMove();
        Subject subject = game.initWinner();
        assertThat(subject.getName(), is("user"));
        assertThat(subject.getColor(), is(Cell.O));
    }

    @Test
    public void whenUseNonstandardSizeDescThenSizeItIsChoiceUser() {
        StubInput input = new StubInput();
        input.setAnswersStr(new String[] {"bot", "n"});
        input.setAnswersNum(new int[] {4, 0, 1, 1, 1, 2, 1, 3, 1});
        Dialog dialog = new Dialog();
        dialog.setInput(input);
        Game game = new Game();
        game.setDialogs(dialog);
        game.choiceSide();

        game.loopMove();
        assertThat(game.getBoard().getBoard().length, is(4));
    }
}