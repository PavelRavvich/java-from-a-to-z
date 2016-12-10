package ru.pravvich.lesson_6.server;

enum Paths {
    REPO("/Users/pavel/Desktop/test/server/");

    private String path;

    public String getPath() {
        return this.path;
    }

    Paths(String path) {
        this.path = path;
    }
}
