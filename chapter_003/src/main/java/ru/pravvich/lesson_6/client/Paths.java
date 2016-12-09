package ru.pravvich.lesson_6.client;

enum Paths {
    UPLOAD("/Users/pavel/Desktop/test/client");

    private String path;

    public String getPath() {
        return path;
    }

    Paths(String path) {
        this.path = path;
    }
}
