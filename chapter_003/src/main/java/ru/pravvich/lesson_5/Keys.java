package ru.pravvich.lesson_5;

enum Keys {
    STOP("стоп"),CONTINUE("продолжить"),FINISH("закончить"),
    GREETING("Добро пожаловать в бессмымленный чат с ботом! \nВведите ваше сообщение:"),
    FILEPATH("https://www.mkyong.com/java/java-read-a-file-from-resources-folder/answer.txt");

    private String value;

    public String getValue() {
        return value;
    }

    Keys(String value) {
        this.value = value;
    }
}
