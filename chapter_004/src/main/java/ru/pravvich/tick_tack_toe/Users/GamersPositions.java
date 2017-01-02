package ru.pravvich.tick_tack_toe.Users;

import ru.pravvich.tick_tack_toe.Users.position.Position;

/**
 * Determines actions which positions.
 */
interface GamersPositions {

    /**
     * Getter position.
     * @return Position on field.
     */
    Position getPosit();

    /**
     * Recording new value in Position on field.
     */
    void setPosit();
}
