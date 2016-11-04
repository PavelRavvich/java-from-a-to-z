package ru.pravvich.models;

public class Task extends Item {
    public Task(String header ) {
        this.header = header;

    }

    public String calculatePrise() {
        return "1 000 000";
    }
}
