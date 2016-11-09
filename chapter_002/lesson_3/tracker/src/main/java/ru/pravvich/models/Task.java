package ru.pravvich.models;

public class Task extends Item {
    public Task(String header ) {
        this.header = header;
    }

    public Task(String header, String description) {
        this.header = header;
        this.description = description;
    }
}
