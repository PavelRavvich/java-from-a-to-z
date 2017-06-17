package ru.pravvich.servlets;

/**
 * Created by pavel on 16.06.17.
 */
public enum Messages {
    ERROR_EDIT("Такого пользователя не существует или пара login, password занята."),
    EDIT_SUCCESS("Пользователь успешно отредактирован."),
    FIND_ERROR("Такого пользователя не существует"),
    DELETE("Пользователь успешно удален"),
    ERR_UNIQUE_L_P("Такая пара логин пароль занята, выберете другие.");

    private final String value;

    public String get() {
        return value;
    }

    Messages(String value) {
        this.value = value;
    }
}
