package ru.pravvich.tick_tack_toe.round;

import ru.pravvich.tick_tack_toe.StubInputInterface;

/**
 * Aggregate game interfaces.
 */
public interface Round extends FirstMove, Winner, StubInputInterface, GameStubInput {
}
