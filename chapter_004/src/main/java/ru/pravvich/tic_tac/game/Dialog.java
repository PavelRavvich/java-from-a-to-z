package ru.pravvich.tic_tac.game;

import ru.pravvich.tic_tac.input.ConsoleInput;
import ru.pravvich.tic_tac.input.Input;

public class Dialog implements DialogAsk, DialogSetInput {
    private Input input = new ConsoleInput();

    @Override
    public void setInput(Input input) {
        this.input = input;
    }

    @Override
    public String askStr(String question) {
        System.out.println(question);
        return this.input.getString();
    }

    @Override
    public int askNum(String question) {
        System.out.println(question);
        return this.input.getNumber();
    }
}
