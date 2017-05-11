package ru.pravvich.start;

public class StubInput implements Input {
    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        System.out.println(question);
        return this.answers[position++];
    }

    @Override
    public int askInt(String question) {
        String idS = this.answers[position++];
        if (!idS.contains(" ") && idS.matches("[0-9]+")) {
            return Integer.parseInt(idS);
        }
        return -1;
    }
}
