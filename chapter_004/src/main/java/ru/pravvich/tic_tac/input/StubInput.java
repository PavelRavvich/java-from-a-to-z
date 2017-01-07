package ru.pravvich.tic_tac.input;

/**
 * Emulator console in for tests.
 */
public class StubInput implements Input {
    /**
     * Counter for answers string values.
     */
    private int countStr;
    /**
     * Answers string values.
     */
    private String[] answersStr;
    /**
     * Counter for answers number values.
     */
    private int countNum;
    /**
     * Answers number int values.
     */
    private int[] answersNum;

    /**
     * Setter for answers string values.
     * @param answersStr answers string values.
     */
    public void setAnswersStr(String[] answersStr) {
        this.answersStr = answersStr;
    }

    /**
     * Setter for answers number values.
     * @param answersNum answers number values.
     */
    public void setAnswersNum(int[] answersNum) {
        this.answersNum = answersNum;
    }

    /**
     * Emulator number console in.
     * @return number fake console in.
     */
    @Override
    public int getNumber() {
        return this.answersNum[this.countNum++];
    }

    /**
     * Emulator string console in.
     * @return string fake console in.
     */
    @Override
    public String getString() {
        return this.answersStr[this.countStr++];
    }
}
