package ru.pravvich.jdbs;

public interface PropertyLoader {
    void load();
    String getUrlDB();
    String getUsername();
    String getPassword();
    String getRootDB();
    String getCreateDBScript();
    String getScriptForCreateTasks();
    String getScriptForCreateComments();
}
