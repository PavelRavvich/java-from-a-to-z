package ru.pravvich.generic.store;

abstract class Base {
    String id;

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }
}
