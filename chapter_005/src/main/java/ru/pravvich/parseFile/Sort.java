package ru.pravvich.parseFile;

import java.util.Collection;

interface Sort {
    Collection<Order> getAllOrders();
    Collection<Order> mergeEachBook(Collection<Collection<Order>> orders);
    Collection<Collection<Order>> getCollectionOfCollectionsByBook(Collection<Order> orders);
}
