package ru.pravvich.parseFile;

import java.util.List;

interface Sort {
    List<Order> getCollectionOfOrdersWith(String book, String operation);
}
