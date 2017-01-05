package ru.pravvich.tic_tac.input;

// для тестов эмулирует ввод с консоли
public class StubInput implements Input {
    private int countStr;
    private String[] answersStr;

    private int countNum;
    private int[] answersNum;

    public void setAnswersStr(String[] answersStr) {
        this.answersStr = answersStr;
    }

    public void setAnswersNum(int[] answersNum) {
        this.answersNum = answersNum;
    }

    // эмулирует ввод чисел
    @Override
    public int getNumber() {
        return this.answersNum[this.countNum++];
    }

    // эмулирует ввод строк
    @Override
    public String getString() {
        return this.answersStr[this.countStr++];
    }
}
