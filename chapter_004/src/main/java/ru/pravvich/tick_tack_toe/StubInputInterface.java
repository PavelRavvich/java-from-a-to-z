package ru.pravvich.tick_tack_toe;

/**
 * For test classes which need input from user.
 * StubInput substitution Input for tests.
 *
 * @see ru.pravvich.tick_tack_toe.desk.Desc;
 */
public interface StubInputInterface {

    /**
     * Use for test class StubInput.
     *
     * @param input emulation console input stream.
     */
    void setInput(In input);
}
