package ru.pravvich;

public enum Help {
    ACTION("Выберите действие:"),
    FIRST("Введите первое число:"),
    SECOND("Введите второе число:"),
    NOEXIST("Такое действие не потдерживается."),
    HELP("Для сброса введите \"с\""),
    ERROR("Это не число. Попробуйте еще раз:"),
    NOEXITS("Такого действия не существует.");

    private String content;

    public String getContent() {
        return this.content;
    }

    Help(String content) {
        this.content = content;
    }
}
