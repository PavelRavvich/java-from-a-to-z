package ru.pravvich.parseFile;

import java.util.List;

public interface Print {
    void start();
    List<Order> getDataFrom(String path);
}
