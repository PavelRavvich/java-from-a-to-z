package ru.pravvich.tic_tac.input;

/**
 * Incalculable all cases when need console input.
 */
public class Dialog implements DialogAsk {
    /**
     * Provides console input.
     */
    private Input input = new ConsoleInput();

    /**
     * Default constructor.
     * @param input console in.
     */
    public Dialog(Input input) {
        this.input = input;
    }

    /**
     * Print question and get answer from console in.
     * @param question for print.
     * @return string is user's answer.
     */
    @Override
    public String askStr(String question) {
        System.out.println(question);
        return this.input.getString();
    }

    /**
     * Print question and get answer from console in.
     * @param question for print.
     * @return int number is user's answer.
     */
    @Override
    public int askNum(String question) {
        System.out.println(question);
        return this.input.getNumber();
    }
}
