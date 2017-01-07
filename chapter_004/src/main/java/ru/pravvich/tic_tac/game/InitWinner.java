package ru.pravvich.tic_tac.game;

import ru.pravvich.tic_tac.users.Subject;

/**
 * Init winner current round.
 */
interface InitWinner {
    /**
     * Init winner current round.
     * @return subject which win.
     * If case is dead head then method return new User obj,
     * with color equal empty and name is nobody.
     */
    Subject initWinner();
}
