package ru.pravvich.tick_tack_toe;

/**
 * Determines console input methods.
 */
public interface In {

    /**
     * Input console for get string.
     *
     * @return user's answer string.
     */
    String getStrInput();

    /**
     * Input console for get int.
     *
     * @return user's answer number.
     */
    int getNumInput();
}
