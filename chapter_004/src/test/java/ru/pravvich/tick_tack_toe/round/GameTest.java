package ru.pravvich.tick_tack_toe.round;

import org.junit.Test;
import ru.pravvich.tick_tack_toe.In;
import ru.pravvich.tick_tack_toe.Input;
import ru.pravvich.tick_tack_toe.StubInput;
import ru.pravvich.tick_tack_toe.desk.Board;
import ru.pravvich.tick_tack_toe.desk.Desc;
import ru.pravvich.tick_tack_toe.users.Bot;
import ru.pravvich.tick_tack_toe.users.Gamers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 *
 */
public class GameTest {

    @Test
    public void whenThen() {
        StubInput inGame = new StubInput();
        inGame.setAnswersStr(new String[]{"BOT"});
        Round game = new Game();
        game.setInput(inGame);

        StubInput inDesc = new StubInput();
        Board desc = new Desc(inDesc);
        inDesc.setAnswersStr(new String[]{"y"});
        desc.setInput(inDesc);

        game.setDesc(desc);

        inGame.setAnswersNum(new int[]{1,0,1,1,1,2});
        inDesc.setAnswersNum(new int[]{1,0,1,1,1,2});
        game.firstMove();

        Gamers bot = game.getGamers().get(0);
        Class usr = game.getGamers().get(1).getClass();
        assertThat(bot, instanceOf(Bot.class));
    }

}