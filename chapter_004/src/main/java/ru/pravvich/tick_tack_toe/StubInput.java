package ru.pravvich.tick_tack_toe;

/**
 * For tests emulation input.
 */
public class StubInput implements In {

    /**
     * Contain answers required string type.
     */
    private String[] answersStr;

    /**
     * Counter for get next value from answersStr.
     */
    private int countStr = 0;

    /**
     * Contain answers required int type.
     */
    private int[] answersNum;

    /**
     * Counter for get next value from answersNum.
     */
    private int countNum = 0;

    /**
     * Setter for array with string answers.
     *
     * @param answersStr array with answers.
     */
    public void setAnswersStr(String[] answersStr) {
        this.answersStr = answersStr;
    }

    /**
     * Setter for array with int answers.
     *
     * @param answersNum array with answers.
     */
    public void setAnswersNum(int[] answersNum) {
        this.answersNum = answersNum;
    }

    /**
     * Emulator string input stream.
     *
     * @return string answer.
     */
    @Override
    public String getStrInput() {
        return this.answersStr[this.countStr++];
    }

    /**
     * Emulator int input stream.
     *
     * @return int answer.
     */
    @Override
    public int getNumInput() {
        return this.answersNum[this.countNum++];
    }
}
