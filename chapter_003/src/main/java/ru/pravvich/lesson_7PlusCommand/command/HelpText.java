package ru.pravvich.lesson_7PlusCommand.command;

public enum HelpText {

    HELP("Помощь:  \"h\""),
    RECORDRESULT("Результат записан в "),
    ACTIONNOEXSIST("Действие не возможно."),
    DORDONTEXIST("Такой директории не существует."),
    ENTERREGEXP("Введите часть имени файла для поиска:"),
    FILEFORSEARCH("Введите полное имя файла для поиска:"),
    ENTERFILENAME("Введите имя файла в формате \"file.extension\":"),
    DIRFORSEARCH("Введите полное имя директории для поиска в формате \"absolute path.../dir\":"),
    NAMEDIRFORSAVE("Введите полное имя директории куда сохранить файл в формате \"absolute path.../dir/\":"),
    MANUAL("Искать файл по полному имени: \"-f\"\nИскать файл по регулярному выражению: \"-r\"\nЗаписать файл: \"-o\"");

    private String text;

    public String getText() {
        return text;
    }

    HelpText(String text) {
        this.text = text;
    }
}
