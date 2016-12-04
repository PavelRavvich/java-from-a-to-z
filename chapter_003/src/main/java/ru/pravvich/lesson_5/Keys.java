package ru.pravvich.lesson_5;

enum Keys {
    STOP("стоп"),CONTINUE("продолжить"),FINISH("закончить");

    private String value;

    public String getValue() {
        return value;
    }

    Keys(String value) {
        this.value = value;
    }
}
