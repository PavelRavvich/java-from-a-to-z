package ru.pravvich;

enum Help {
    ACTION("Выберите действие:"),
    FIRST("Введите первое число:"),
    SECOND("Введите второе число:"),
    NOEXIST("Такое действие не потдерживается."),
    HELP("Для сброса введите \"C\""),
    ERROR("Это не число. Попробуйте еще раз:");

    private String content;

    public String getContent() {
        return this.content;
    }

    Help(String content) {
        this.content = content;
    }
}
