package ru.pravvich;

/**
 * Flags for select action.
 */
public enum Flags {
    PLU("+"), MIN("-"), DIV("/"), MUL("*"), SIN("sin"), RES("c");

    private String flag;

    public String getFlag() {
        return this.flag;
    }

    Flags(String flag) {
        this.flag = flag;
    }
}
