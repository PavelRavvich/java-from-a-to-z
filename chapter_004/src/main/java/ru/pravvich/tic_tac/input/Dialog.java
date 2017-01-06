package ru.pravvich.tic_tac.input;

// инкапсулирует все случаи когда нужен ввод с консоли
public class Dialog implements DialogAsk {
    private Input input = new ConsoleInput();

    public Dialog(Input input) {
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
