package ru.pravvich.generic.store;

public abstract class Base {
    private final String id;

    Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
