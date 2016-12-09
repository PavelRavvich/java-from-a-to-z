package ru.pravvich.lesson_6.server;

enum Paths {
    REPO("/Users/pavel/Desktop/test/server");


    public String getPath() {
        return path;
    }

    private String path;
    Paths(String path) {
        this.path = path;
    }
}
