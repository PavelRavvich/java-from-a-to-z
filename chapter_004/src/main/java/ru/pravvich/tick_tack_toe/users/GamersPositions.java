package ru.pravvich.tick_tack_toe.users;

import ru.pravvich.tick_tack_toe.users.position.Position;

/**
 * Determines actions which positions.
 */
interface GamersPositions {

    /**
     * Getter position.
     *
     * @return Position on field.
     */
    Position getPosit();

    /**
     * Recording new value in Position on field.
     */
    void setPosit();
}
