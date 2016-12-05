package ru.pravvich.lesson_5;

enum Constants {
    STOP("стоп"),CONTINUE("продолжить"),FINISH("закончить"),
    GREETING("Добро пожаловать в бессмымленный чат с ботом! \nВведите ваше сообщение:"),
    FILEPATH("/answer.txt");

    private String value;

    public String getValue() {
        return value;
    }

    Constants(String value) {
        this.value = value;
    }
}
