package ru.pravvich.servlets;

/**
 * All paths of jsp files.
 */
public enum Paths {
    ADMIN_MENU("/WEB-INF/views/AdminMenu.jsp"),
    USER_MENU("/WEB-INF/views/UserMenu.jsp"),
    LOGIN("/WEB-INF/views/login.jsp"),
    ADD("/WEB-INF/views/addition.jsp"),
    ALL_USERS("/WEB-INF/views/all.jsp"),
    ANSWER("/WEB-INF/views/answer.jsp"),
    DELETE("/WEB-INF/views/delete.jsp"),
    EDITION("/WEB-INF/views/edition.jsp"),
    ERROR_ACCESS("/WEB-INF/views/ErrorAccess.jsp"),
    EDIT_PROFILE("/WEB-INF/views/EditProfile.jsp"),
    FIND("/WEB-INF/views/find.jsp"),
    USER("/WEB-INF/views/user.jsp");

    private String value;

    public String get() {
        return value;
    }

    Paths(String value) {
        this.value = value;
    }
}
