package ru.pravvich.tick_tack_toe.round;

import ru.pravvich.tick_tack_toe.desk.Board;
import ru.pravvich.tick_tack_toe.users.Gamers;

import java.util.ArrayList;

/**
 * Class determines method which need for use test with StubInput.
 */
public interface GameStubInput {
    ArrayList<Gamers> getGamers();
    void setDesc(Board desc);
}
